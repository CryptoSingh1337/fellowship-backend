package com.cyborg.fellowshipservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * @author saranshk04
 */
@Slf4j
@Aspect
@Component
@ConditionalOnExpression("${logging.enable:true}")
public class LoggingAspect {

    @Pointcut("execution(public * com.cyborg.fellowshipservice.user.impl.*.*(..))")
    private void userServicePointcut() {}

    @Before("userServicePointcut()")
    public void log() {
        log.info("Inside the user service");
    }
}
