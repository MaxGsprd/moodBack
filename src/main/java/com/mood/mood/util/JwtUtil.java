package com.mood.mood.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Cette classe contient la gestion du token
 */
@Service
public class JwtUtil {
    private String secret = "mySecretKey";

    /**
     * Extrait l'email et le retourne
     * @param token
     * @return
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    /**
     * Extrait la date d'expiration du token et la retourne
     * @param token
     * @return
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrait une portion du token en fonction de ce qui est passé en paramètre
     * (subject, expiration, etc.)
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Découpe le token et retourne toutes les portions
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Vérifie si la date d'expiration du token est inférieur à la date courante ou non
     * Retourne "true" le token est expiré, sinon, "false"
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Récupère l'email de l'utilisateur qui tente de s'authentifier,
     * email envoyé par le Controller
     * Crée le token via la méthode createToken()
     * @param email
     * @return
     */
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    /**
     * Crée le token
     * @param claims
     * @param subject
     * @return
     */
    private String createToken(Map<String, Object> claims, String subject) {
        // .setClaims définit une Map (vide)
        // .setSubject() définit l'email
        // .setIssuedAt définit la date de création du token
        // .setExpiration déinit la date d'expiration du token (paramétrée pour une durée de 10h)
        // .signWith() définit la signature du token qui est notre clé secret cryptée en suivant
        // l'algorithme HS256
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * Valide le token
     * Extrait l'email de l'objet userDetails
     * puis le compare à celui en base
     * Si c'est bon et que le token n'est pas expiré, on retourne "true"
     * sinon, "false"
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
