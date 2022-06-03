package com.mood.mood.filter;

import com.mood.mood.exceptions.CustomUserDetailsService;
import com.mood.mood.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cette classe contient le filtrage des utilisateurs authentifiés/non-authentifiés
 * elle hérite de la classe "OncePerRequestFilter" ainsi nous pourrons exéctuer
 * le filtre une fois pour une même requête HTTP et un même utilisateur authentifié
 * c'est-à-dire que si une requête survient plusieurs fois, le filtre n'opérera qu'une
 * tant que l'utilisateur est authentifé
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService service;

    /**
     * Cette méthode fait office de filtre ; elle vérifie si le token est valide
     * S'il l'est, elle authentifie l'utilisateur
     * et l'utilisateur est donc authorisé à accéder à l'endpoint
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        String email = null;

        // Si la requête HTTP contient le parmarètre "Authorization" et qu'il commence par "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Le token (sans le "Bearer ")
            email = jwtUtil.extractEmail(token);
        }

        // Si l'on a bien réussi à extraire l'email du token et
        // si l'utilisateur n'est pas déjà authentifié
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = service.loadUserByUsername(email);

            // Si le token est valide
            if (jwtUtil.validateToken(token, userDetails)) {
                // On crée un objet d'authentification
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                // On l'insert dans le SecurityContext. Donc on authentifie notre utilisateur
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // On execute
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
