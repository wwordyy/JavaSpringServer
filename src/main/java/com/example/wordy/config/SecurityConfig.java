package com.example.wordy.config;

import com.example.wordy.security.PersonDetails;
import com.example.wordy.security.PeopleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PeopleDetailsService peopleDetailsService;


    @Autowired
    public SecurityConfig(PeopleDetailsService peopleDetailsService) {
        this.peopleDetailsService = peopleDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests()
                .antMatchers("/CSS/**", "/images/**").permitAll()
                .antMatchers("/admin", "/baskets", "/checks", "/countries", "/manufacturers",
                        "models","/customers", "/orderStatuses", "/personalDate",
                        "/shop", "/typeOfProducts").hasRole("ADMIN")
                .antMatchers("/auth/login", "/api/registration", "error",
                        "/home", "/process_login", "/auth/registration" ).permitAll()
                .anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {

                        Object principal = authentication.getPrincipal();

                        String idUser = "";

                        if (principal instanceof PersonDetails) {
                            idUser = Integer.toString(((PersonDetails) principal).getPersonId());
                        }


                        response.setStatus(HttpServletResponse.SC_OK);
                        response.setContentType("application/json");

                        String json = "{ \"idCustomer\": \"" + idUser + "\", \"redirectUrl\": \"http://localhost:5500/HTML/main.html\" }";

                        response.getWriter().write(json);
                        response.getWriter().flush();
                    }



                })
                .failureHandler(new AuthenticationFailureHandler() {

                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("{\"error\":\"" + exception.getLocalizedMessage() + "\"}");
                        response.getWriter().flush();
                    }
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login")
                .and()
                .exceptionHandling().accessDeniedPage("/auth/access-denied");
    }

    // Настраиваем аунтификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(peopleDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:5500"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "X-Requested-With", "X-XSRF-TOKEN", "Origin", "Accept"));
        configuration.setExposedHeaders(List.of("Authorization", "X-XSRF-TOKEN", "Location"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
