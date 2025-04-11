package es.luis.canyoningApp.boot.config;

import es.luis.canyoningApp.boot.config.boot.CustomUserDetailsService;
import es.luis.canyoningApp.domain.security.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(/*debug = true*/)
public class SecurityConfiguration {

    String[] freeGetEnpoints = {
            "/validateUser",
            "/canyons",
            "/canyons/map",
            "/messages/{activityType}/activity/{activityId}",
            "images/{image}/{dir}",
            "articles",
            "/articles/{articleId}"
    };

    String[] freePostEndpoints = {
            "/createUser", "/updatePassword", "/login",
    };

    String[] freePutEndpoints = {"/updatePassword"};

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        .requestMatchers(HttpMethod.GET, freeGetEnpoints)
                                        .permitAll().requestMatchers(HttpMethod.POST, freePostEndpoints).permitAll().requestMatchers(HttpMethod.PUT, freePutEndpoints).permitAll()
                                        .anyRequest()
                                        .hasAuthority(RoleUtils.ROLE_AUTHENTICATED))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .cors(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
