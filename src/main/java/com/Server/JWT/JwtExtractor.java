package com.Server.JWT;

import com.sun.net.httpserver.HttpExchange;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class JwtExtractor {
    private static final Key key = Keys.hmacShaKeyFor("iliaAsadiBahramEmami12345678987654321".getBytes(StandardCharsets.UTF_8));
    public static String ExtractToken(HttpExchange exchange) {
        try {
            String token = exchange.getRequestHeaders().get("JWT").get(0);
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
