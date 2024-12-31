package com.example.demo.Security;


import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import java.util.Date;

public class JWTUtil {
    private static final SecretKey KEY = Jwts.SIG.HS512.key().build();  // Generate a secure key
    private static final long EXP_TIME_10_DAYS = 864_000_000;

    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username)  // changed from setSubject
                .expiration(new Date(System.currentTimeMillis() + EXP_TIME_10_DAYS))  // changed from setExpiration
                .signWith(KEY, Jwts.SIG.HS512)  // changed signature style
                .compact();
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(KEY)    // changed from setSigningKey
                .build()
                .parseSignedClaims(token)  // changed from parseClaimsJws
                .getPayload()
                .getSubject();
    }

    public static Boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(KEY)    // changed from setSigningKey
                    .build()
                    .parseSignedClaims(token);  // changed from parseClaimsJws
            return true;
        } catch (Exception ex) {
            System.out.println("Invalid token: " + ex.getMessage());
            return false;
        }
    }
}
