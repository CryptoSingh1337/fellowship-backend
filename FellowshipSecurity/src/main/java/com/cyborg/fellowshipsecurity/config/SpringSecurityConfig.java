package com.cyborg.fellowshipsecurity.config;

import com.cyborg.fellowshipsecurity.filter.CustomAuthenticationFilter;
import com.cyborg.fellowshipsecurity.filter.CustomAuthorizationFilter;
import com.cyborg.fellowshipservice.user.UserService;
import com.cyborg.utilities.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.Objects;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * @author saranshk04
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment env;
    private final ObjectMapper mapper;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS).and()
                .authorizeRequests().antMatchers("/**").permitAll().and()
                .addFilter(authenticationFilter())
                .addFilterBefore(new CustomAuthorizationFilter(mapper, jwtUtils, env, urlsToSkip()),
                        UsernamePasswordAuthenticationFilter.class);
    }

    private CustomAuthenticationFilter authenticationFilter() throws Exception {
        CustomAuthenticationFilter authenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean(), mapper, jwtUtils);
        authenticationFilter.setFilterProcessesUrl(env.getProperty("auth.login.path"));
        return authenticationFilter;
    }

    public List<String> urlsToSkip() {
        return List.of(Objects.requireNonNull(env.getProperty("skipUrls")).split(","));
    }
}
