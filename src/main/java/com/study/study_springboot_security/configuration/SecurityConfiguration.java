package com.study.study_springboot_security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // None using csrf protection
        httpSecurity.csrf().disable();
        // 권한에 대한 부분 : url & roles : user url & roles
        httpSecurity.authorizeRequests()
                // .antMatchers("/").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                // .antMatchers("/").authenticated() // 로그인 여부만 판단함.
                .antMatchers("/admin").authenticated()
                // 로그인 & 권한까지 확인하는 것.
                .anyRequest().permitAll(); // 설정한 URL 이외에는 접근 가능.

        // 로그인에 대한 부분
        httpSecurity.formLogin().loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/");
        // formLogin 들어가면--> loginPage 지정하기

        return httpSecurity.build();
    }

}
