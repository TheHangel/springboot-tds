package edu.spring.btp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.boot.autoconfigure.security.servlet.PathRequest

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorizeHttpRequests ->
            authorizeHttpRequests.requestMatchers("/css/**", "/assets/**", "/login/**").permitAll() // (1)
            authorizeHttpRequests.requestMatchers(PathRequest.toH2Console()).permitAll() // (2)
            authorizeHttpRequests.requestMatchers("/admin/**").hasRole("ROLE_ADMIN") // (3)
            authorizeHttpRequests.anyRequest().authenticated() // (4)
        }
        http
            .headers().frameOptions().sameOrigin()
            .and()
            .csrf().disable()
            .formLogin().loginPage("forms/login").successForwardUrl("/")
        return http.build()
    }

}