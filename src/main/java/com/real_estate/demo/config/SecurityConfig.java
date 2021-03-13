package com.real_estate.demo.config;

import com.real_estate.demo.domain.accounts.AccountsRepository;
import com.real_estate.demo.filter.JwtAuthenticationFilter;
import com.real_estate.demo.filter.JwtAuthorizationFilter;
import com.real_estate.demo.service.AccountsPrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AccountsPrincipalDetailService accountPrincipalDetailService;
    private final AccountsRepository accountsRepository;

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
                .antMatchers("/", "/h2-console/**", "/api/account/signup", "/api/account/login", "/api/email/userVerification/**").permitAll();

        http
                .addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),this.accountsRepository));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //db의 비밀번호 암호화/복호화
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.accountPrincipalDetailService);
        return daoAuthenticationProvider;
    }

    protected JwtAuthenticationFilter getAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        try {
            authenticationFilter.setFilterProcessesUrl("/api/account/login");
            authenticationFilter.setUsernameParameter("email");
            //authenticationFilter.setAuthenticationSuccessHandler(new CustomAuthorizationHandler());
            //authenticationFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationFilter;
    }
}
