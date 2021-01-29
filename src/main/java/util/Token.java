/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 *
 * @author Terance Wijesuriya
 */
public class Token {

    /**
     * Generate the HMAC256 Token
     *
     * @param secret Secret to generate the token
     * @return Token as a String
     * @throws UnsupportedEncodingException UTF-8 encoding not supported
     * @throws JWTVerificationException Invalid Signing configuration / Couldn't
     * convert Claims.
     */
    public String issueTokenHMAC256(String secret, String username, long expMins) throws UnsupportedEncodingException, JWTCreationException {

        String token = "";
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(expMins).toInstant());
            token = JWT.create()
                    .withExpiresAt(expirationDate)
                    .withClaim("username", username)
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            // Invalid signing configuration / Couldn't convert claims
            e.printStackTrace();
        }

        return token;
    }

    /**
     * Validate a HMAC256 Token
     *
     * @param token Token you need to validate
     * @param secret Secret used to generate the token
     * @return Returns `true` if token is valid.
     * @throws UnsupportedEncodingException UTF-8 encoding not supported
     * @throws JWTVerificationException Invalid Signing configuration / Couldn't
     * convert Claims.
     */
    public boolean validateTokenHMAC256(String token, String secret) throws UnsupportedEncodingException, JWTVerificationException {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            Claim usernameClaim = jwt.getClaim("username");
            String username = usernameClaim.asString();
            System.out.println("USERNAME : "+username);
            return true;

        } catch (JWTVerificationException e) {
            // Invalid signature/claims
            e.printStackTrace();
            return false;
        }

        
    }
    
    public String readClaim(String token, String claimName)
    {
        DecodedJWT jwt = JWT.decode(token);
        Claim claimNameStr = jwt.getClaim(claimName);
            String username = claimNameStr.asString();
            System.out.println(username);
        
        return claimNameStr.asString();
    }
}
