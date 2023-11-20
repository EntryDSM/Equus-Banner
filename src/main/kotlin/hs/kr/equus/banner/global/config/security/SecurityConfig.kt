package hs.kr.equus.banner.global.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.banner.global.config.filter.FilterConfig
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
            .antMatchers("/")
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
            .apply(hs.kr.equus.banner.global.config.filter.FilterConfig(objectMapper))

        return http.build()
    }
}
