package com.lin.util;

import com.lin.pojo.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

/**
 * @author 林炳昌
 * @date 2023年02月21日 22:40
 */
@Component
public class tokenUtil {

    private final String signature = "user";

    public String getToken(User user) {
        JwtBuilder jwtBuilder = Jwts.builder();
        return jwtBuilder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("username", user.getUsername())
                .claim("password", user.getPassword())
                .claim("money", user.getMoney())
                .claim("isCertified", user.isCertified())
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
    }

    public Claims parseToken(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        return claimsJws.getBody();
    }

}
