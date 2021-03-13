package com.real_estate.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class JwtProperties {
    public static String SECRET;
    public static int EXPIRATION_TIME; //token validation 기간
    public static String TOKEN_PREFIX;//header prefix
    public static String HEADER_STRING; //authorization header로 전달

    public JwtProperties(@Value("${secret-key}") String secret, @Value("${expiration-time}") int time, @Value("${token-prefix}") String prefix){
        SECRET = secret;
        EXPIRATION_TIME = time;
        TOKEN_PREFIX = prefix;
        HEADER_STRING = "Authorization";
    }
}
