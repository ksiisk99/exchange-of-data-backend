package com.ay.exchange.jwt;

import com.ay.exchange.jwt.authority.Authority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private final String secretKey;
    @Value("${jwt.access-expire-time}")
    private final Long ACCESS_EXPIRE_TIME;
    @Value("${jwt.refresh-expire-time}")
    private final Long REFRESH_EXPIRE_TIME;
    private Key key;

    @PostConstruct
    public void initKey(){
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        key= Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String id, Authority authority){
        Claims claims= Jwts.claims();
        claims.setSubject(id);
        claims.put("authority",authority);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime() + ACCESS_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            return !Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
        }catch(JwtException e){ //유효하지 않은 토큰
            return false;
        }catch(IllegalArgumentException e){ //NULL 토큰
            return false;
        }
    }

    public Authority getAuthority(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("authority", Authority.class);
        }catch(JwtException e){ //유효하지 않은 토큰
            return null;
        }catch (IllegalArgumentException e){ //NULL 토큰
            return null;
        }
    }
}
