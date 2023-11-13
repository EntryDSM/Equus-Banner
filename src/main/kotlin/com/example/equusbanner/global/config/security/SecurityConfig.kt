package com.example.equusbanner.global.config.security

import com.example.equusbanner.global.config.filter.FilterConfig
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsUtils

@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper
) {
    companion object {
        const val ADMIN_ROLE = "ADMIN"
    }

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
            .cors().and()
            .formLogin().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .requestMatchers(CorsUtils::isCorsRequest)
            .permitAll()
            .antMatchers("/banner/**")
            .hasRole(ADMIN_ROLE)
            .antMatchers(HttpMethod.POST, "/banner/**")
            .hasRole(ADMIN_ROLE)
            .antMatchers(HttpMethod.PATCH, "/banner/**")
            .hasRole(ADMIN_ROLE)
            .antMatchers(HttpMethod.DELETE, "/banner/**")
            .hasRole(ADMIN_ROLE)
            .anyRequest()
            .authenticated()

        http
            .apply(FilterConfig(objectMapper))

        return http.build()
    }
}
