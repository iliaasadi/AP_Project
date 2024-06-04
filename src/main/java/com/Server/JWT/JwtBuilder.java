package com.Server.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtBuilder {
    private static final Key key = Keys.hmacShaKeyFor("iliaAsadiBahramEmami12345678987654321".getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    private static final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

    public static String MakeToken(String id) {
        String token = Jwts.builder()
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, algorithm)
                .compact();
        return token;
    }
}
