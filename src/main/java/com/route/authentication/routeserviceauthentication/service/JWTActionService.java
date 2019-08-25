package com.route.authentication.routeserviceauthentication.service;

import com.route.authentication.routeserviceauthentication.controller.AuthenticationController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;


/*
 * The definition of the internal claims of the token, like Issuer, Subject, Expiration, and ID.
 * Create the static value of Signature
 */
@Service
public class JWTActionService {

    final static Logger log = LoggerFactory.getLogger(JWTActionService.class);

     private static final int tokenduration=100000;

    /* Public Key */
    @Value("${encodedPublicKey}")
    private String encodedPublicKey;

    private PublicKey publicKey;

    /* Private key */
    @Value("${encodedPrivateKey}")
    private String encodedPrivateKey;

    private PrivateKey privateKey;

    @PostConstruct
    public void setPublicKey() {

            try {
                KeyFactory kfc = KeyFactory.getInstance("RSA");
                X509EncodedKeySpec spce = new X509EncodedKeySpec(java.util.Base64.getDecoder().decode(this.encodedPublicKey));
                this.publicKey = (RSAPublicKey) kfc.generatePublic(spce);
            }
            catch(NoSuchAlgorithmException noSuchAlgo){
                System.out.print(noSuchAlgo);
            }
            catch(InvalidKeySpecException invliadkey){

            }catch (IllegalStateException illegalstate){

            }
    } //end of setPublicKey


    @PostConstruct
    public void setPrivateKey() {

        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            byte[] decodedPrivateKey = java.util.Base64.getDecoder().decode(this.encodedPrivateKey);
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(decodedPrivateKey);
            this.privateKey = kf.generatePrivate(keySpecPKCS8);
        }
        catch (NoSuchAlgorithmException noSuchAlgo){

        }
        catch(InvalidKeySpecException invliadkey){

        }

    } //end of function serPrivatekey()


    public String createToken(String username,char gender) {


        Date now = new Date();
        Date exp = new Date(now.getTime() + (tokenduration));

        String token = Jwts.builder()
                .setIssuer("dk")
                .setSubject("token")
                .claim("login", username)
                .claim("gener", gender)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(
                        SignatureAlgorithm.RS256,
                        this.privateKey
                )
                .compact();


        this.validateGeneratedToken(token);
        return token;

    } //end of createToken()


    public void validateGeneratedToken(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.privateKey).parseClaimsJws(token).getBody();
        log.info("Subject = "+claims.getSubject());
        log.info("Issue="+claims.getIssuer());
        log.info("Exp date="+ claims.getExpiration());
        //log.info();
    }



} //end of JWTAction
