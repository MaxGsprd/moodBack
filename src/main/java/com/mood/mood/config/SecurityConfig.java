package com.mood.mood.config;

import com.mood.mood.filter.JwtFilter;
import com.mood.mood.service.CustomUserDetailsService;
import com.mood.mood.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe de configuration de la sécurité globale de l'API
 *
 * @Configuration permet de déclarer des méthodes @Bean
 * @EnableWebSecurity couplé à @Configuration nous permet de configurer
 * @EnableGlobalMethodSecurity permet d'utiliser la vérification par ROLE dans les Controller(ou autre)
 * "securedEnable=true" permet d'utiliser l'annotation @Secured
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Crée le gestionaire d'authentification
     * "userDetailsService" contient les credentials + role
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Pas d'encodeur de mot de passe
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /*@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    /**
     * Permet au controller d'authentifier l'utilisateur
     *
     * @return
     * @throws Exception
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configure la sécurité au niveau des requêtes HTTP
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // .authorizeRequests() permet d'utiliser des restrictions
        // Grâce au combo .antMatchers("/authentication/login").permitAll()
        // on désactive toute sécurité (notemment"doit être authentifié") au niveau de l'URL "/authentication/login"
        // Grâce au combo .anyRequest().authenticated()
        // On réactive la sécurité "doit être authentifié" au niveau de toutes les autres URLs
        // Grâce au combo .and().exceptionHandling()
        // .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        // on active le mécanisme d'authification "stateless" car notre filtre JWT suit ce mécanisme
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/localisation").permitAll()
                .antMatchers("/authentication/register").permitAll()
                .antMatchers("/authentication/login").permitAll()
                .antMatchers("/establishments").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // On ajoute notre filtre
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}