package org.iesvdm.proyecto.config;

import org.iesvdm.proyecto.security.AuthEntryPointToken;
import org.iesvdm.proyecto.security.AuthTokenFilter;
import org.iesvdm.proyecto.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointToken unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(unauthorizedHandler) )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        //PARA LAS PETICIONES preflight OPTIONS DEL NAVEGADOR :p
                        //Ver https://stackoverflow.com/questions/76682586/allow-cors-with-spring-security-6-1-1-with-authenticated-requests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/v1/api/usuarios/cambiarContrasenia").authenticated()
                        .requestMatchers("/v1/api/asignaturas/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers("/v1/api/cursos/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers("/v1/api/clases/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers("/v1/api/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET,"/v1/api/profesores/**").hasAnyAuthority("PROFESOR","ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET,"/v1/api/estudiantes/**").hasAnyAuthority("PROFESOR","ADMINISTRADOR")
                        .requestMatchers("/v1/api/profesores/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers("/v1/api/estudiantes/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers("/v1/api/aulas/**").hasAnyAuthority("PROFESOR")
                        .requestMatchers("/v1/api/temas/**").hasAnyAuthority("PROFESOR")
                        .requestMatchers("/v1/api/tareas/**").hasAnyAuthority("PROFESOR")
                        .requestMatchers("/v1/api/**").permitAll()
                        .anyRequest().authenticated());
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //https://stackoverflow.com/questions/59302026/spring-security-why-adding-the-jwt-filter-before-usernamepasswordauthenticatio
        //http.addFilterAfter(authenticationJwtTokenFilter(), ExceptionTranslationFilter.class);
        return http.build();
    }
}