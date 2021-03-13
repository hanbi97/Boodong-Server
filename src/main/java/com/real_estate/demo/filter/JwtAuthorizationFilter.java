package com.real_estate.demo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.real_estate.demo.config.JwtProperties;
import com.real_estate.demo.domain.accounts.Accounts;
import com.real_estate.demo.domain.accounts.AccountsRepository;
import com.real_estate.demo.service.AccountsPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private AccountsRepository accountsRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AccountsRepository accountsRepository) {
        super(authenticationManager);
        this.accountsRepository=accountsRepository;
    }

    //얻은 JWT가 제대로 된건지 확인
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header in JWT
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        // Check if header contain BEARER or is null
        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)){
            // rest of the spring pipeline
            chain.doFilter(request, response);
            return;
        }

        // If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter execution
        chain.doFilter(request, response);
    }

    /** Password Authentication **/
    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        //Get JWT token
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        // parse the token and validate it (decode)
        final String username = JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        if (Objects.isNull(username)) {
            log.info("Decoding the token has a problem");
            return null;
        }
        // Search in the DB if we find the user by token subject (username)
        // If so, then grab user details and create spring auth token using username, pass, authorities/roles
        Accounts account = accountsRepository.findByEmail(username);
        if (Objects.isNull(account)) {
            log.info("username is not in db");
            return null;
        }
        AccountsPrincipal accountPrincipal = new AccountsPrincipal(account);
        return new UsernamePasswordAuthenticationToken(username, null, accountPrincipal.getAuthorities());
    }

}