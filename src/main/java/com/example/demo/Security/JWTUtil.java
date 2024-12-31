package com.example.demo.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;

public class JWTUtil {

    private static byte [] SECRET = "your-very-secure-key-with-length-64-bytes-add-more-of-this-here!"
            .getBytes();
    private static final long EXP_TIME_10_DAYS = 864_000_000;

    public static String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXP_TIME_10_DAYS)) //10days
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    //another method to extract username
}
