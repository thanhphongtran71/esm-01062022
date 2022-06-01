package com.stdio.esm.config.provider;

import com.stdio.esm.config.ApplicationProperties;
import com.stdio.esm.config.service.EsmUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */

@Service
public class JwtTokenProvider implements Serializable {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";
    @Autowired
    private ApplicationProperties properties;


    /**
     * Generate access token from EsmUserDetail information
     *
     * @param esmUserDetail {@link EsmUserDetail}
     * @return {@link String}
     */
    public String generatedAccessJwt(EsmUserDetail esmUserDetail) {
        Date date = new Date();
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        return Jwts.builder()
                .setSubject(esmUserDetail.getUsername())
                .claim("roles", esmUserDetail.getAuthorities())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + properties.getSecurity().getDurationAccessToken()))
                .signWith(SignatureAlgorithm.HS512, encodeSecret)
                .compact();
    }

    /**
     * Generate refresh token from EsmUserDetail information
     *
     * @param esmUserDetail {@link EsmUserDetail}
     * @return {@link String}
     */
    public String generatedRefreshJwt(EsmUserDetail esmUserDetail){
        Date date = new Date();
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        return Jwts.builder()
                .setSubject(esmUserDetail.getUsername())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+properties.getSecurity().getDurationRefreshToken()))
                .signWith(SignatureAlgorithm.HS512,encodeSecret)
                .compact();

    }

    /**
     * Generate Login response including: id, expiredAt, accessToken, refreshToken from EsmUserDetail
     *
     * @param esmUserDetail {@link EsmUserDetail}
     * @return {@link Map<String,Object>}
     */
    public Map<String,Object> generatedLoginResponse(EsmUserDetail esmUserDetail){
        Map<String,Object> responseData = new HashMap<>();
        responseData.put("id", esmUserDetail.getId());
        responseData.put("expiredAt",Instant.now().plusMillis(properties.getSecurity().getDurationRefreshToken()));
        responseData.put("accessToken", generatedAccessJwt(esmUserDetail));
        responseData.put("refreshToken", generatedRefreshJwt(esmUserDetail));
        return responseData;
    }

    /**
     * Check the expiration date of the access token
     *
     * @param accessToken {@link EsmUserDetail}
     * @return {@link Boolean}
     */

    public boolean verifyExpirationAccessJwt(String accessToken){
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        Date expiredDate = Jwts.parser().setSigningKey(encodeSecret).parseClaimsJws(accessToken).getBody().getExpiration();
        if(expiredDate.compareTo(Date.from(Instant.now()))<0){
            return false;
        }
        return true;
    }

    /**
     * Generate new access token from refresh token when the access token has expired
     *
     * @param refreshToken  {@link String}
     * @return {@link String}
     */
    public String generatedAccessJwtFromRefreshJwt(String refreshToken){
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        Claims encodeInformation = Jwts.parser().setSigningKey(encodeSecret).parseClaimsJws(refreshToken).getBody();
        Date date = new Date();
        return Jwts.builder()
                .setSubject(encodeInformation.getSubject())
                .claim("roles", encodeInformation.get("roles"))
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + properties.getSecurity().getDurationAccessToken()))
                .signWith(SignatureAlgorithm.HS512, encodeSecret)
                .compact();
    }

    /**
     * Get username of user from user's token
     *
     * @param token {@link String}
     * @return {@link String}
     */
    public String getUsername(String token) {
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        return Jwts.parser()
                .setSigningKey(encodeSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * validate token
     *
     * @param token {@link String}
     * @return {@link Boolean}
     */
    public boolean validateToken(String token) {
        try {
            String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
            Jwts.parser().setSigningKey(encodeSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Get token from header <b>Authorization</b> of request
     *
     * @param request {@link HttpServletRequest}
     * @return {@link String}
     */
    public String getToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX + " ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
