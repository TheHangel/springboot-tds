package edu.spring.dogs.config

import edu.spring.dogs.services.DbUserService
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorizeHttpRequests ->
            authorizeHttpRequests.requestMatchers(PathRequest.toH2Console()).permitAll()
            authorizeHttpRequests.requestMatchers("/login").permitAll()
            authorizeHttpRequests.requestMatchers("/init/**", "/masters/**", "/dogs/**", "/css/**", "/images/**",    "/h2-console/**", "/login/**").permitAll()
            authorizeHttpRequests.requestMatchers("/master/**").hasAuthority("USER")
            authorizeHttpRequests.anyRequest().authenticated()
        }
        http
            .csrf().disable()
            .formLogin().loginPage("/login")
            .successForwardUrl("/")
            .and()
            .headers().frameOptions().sameOrigin()
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService? {
        return DbUserService()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }

}