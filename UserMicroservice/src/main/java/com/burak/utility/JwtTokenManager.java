package com.burak.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JwtTokenManager {

    private final String SECRETKEY = "H1kBLV_qSDh1sceTCMOyDdhqKbIv402jrtND4OCOZkT5uKXbFLHJ1Ud1awNFG8iYwWL998sM6EVEy1XWXtdrDA";
    private final String ISSUER = "Quiksy";
    private final Long EXDATE = 1000L*30;

    public Optional<String> createToken(Long authId){
        String token;
        try{
            token = JWT.create()
                    .withAudience()
                    .withClaim("authId",authId)
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+EXDATE))
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);
        }catch (Exception exception){
            return Optional.empty();
        }
    }

    public boolean validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm)
                                      .withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT==null)
                return false;
        }catch (Exception exception){
            return false;
        }
        return  true;
    }

    public Optional<Long> getIdByToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null)
                return Optional.empty();
            Long authId = decodedJWT.getClaim("authId").asLong();
            return Optional.of(authId);
        }catch (Exception exception){
            return  Optional.empty();
        }
    }
}
