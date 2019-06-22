package com.mmadu.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@ConditionalOnProperty(name = "mmadu.domain.api-security-enabled", havingValue = "true", matchIfMissing = true)
public @interface EnabledWebSecurityConfiguration {
}
