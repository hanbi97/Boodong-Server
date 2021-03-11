package com.real_estate.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing
@EnableWebSecurity //Spring Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean //Security에서 제공하는 비밀번호 암호화 객체
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //static file 무시
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    //http 관련 인증
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//rest: stateless, cookie에 세션 저장 x
                .and()
                .authorizeRequests()
                .antMatchers("/", "/login/**", "/h2-console/**", "/api/v1/auth/users", "/api/v1/auth/login*", "/api/v1/email/userVerification/**", "/api/v1/products/**", "/api/v1/fund/**", "/api/v1/fund/products/*", "/api/v1/fund/carts/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 성공시 home으로
                .invalidateHttpSession(true);

    }
}
