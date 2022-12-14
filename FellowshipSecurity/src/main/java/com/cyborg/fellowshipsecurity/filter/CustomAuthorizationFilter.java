package com.cyborg.fellowshipsecurity.filter;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cyborg.fellowshipdataaccess.entity.Role;
import com.cyborg.utilities.error.AppErrorCode;
import com.cyborg.utilities.jwt.JwtUtils;
import com.cyborg.utilities.response.ApiResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final ObjectMapper mapper;
    private final JwtUtils jwtUtils;
    private final Environment env;
    private final List<String> urls;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestPath = req.getServletPath();
        if (requestPath.equals(env.getProperty("auth.login.path")) ||
                requestPath.equals(env.getProperty("auth.register.path")))
            filterChain.doFilter(req, res);
        else {
            String authToken = req.getHeader(AUTHORIZATION);
            String token = jwtUtils.extractAuthorizationToken(authToken);
            if (token != null) {
                JWTVerifier verifier = jwtUtils.getTokenVerifier();
                DecodedJWT decodedJWT;
                try {
                    decodedJWT = verifier.verify(token);
                } catch (TokenExpiredException e) {
                    res.setStatus(HttpStatus.UNAUTHORIZED.value());
                    res.setContentType("application/json");
                    mapper.writeValue(res.getWriter(),
                            ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_AUTH_003));
                    return;
                }
                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                Collection<Role> authorities = new ArrayList<>();
                Arrays.stream(roles)
                        .map(role -> Arrays.stream(Role.values())
                                .filter(r -> r.getAuthority().equals(role))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Role not found")))
                        .forEach(authorities::add);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(req, res);
            } else {
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
                res.setContentType("application/json");
                mapper.writeValue(res.getWriter(),
                        ApiResponseUtil.createApiErrorResponse(AppErrorCode.APP_AUTH_001));
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) {
        String requestPath = req.getServletPath();
        return urls.stream()
                .anyMatch(p -> pathMatcher.match(p, requestPath) && (req.getMethod().equals("GET") ||
                        req.getMethod().equals("POST")));
    }
}
