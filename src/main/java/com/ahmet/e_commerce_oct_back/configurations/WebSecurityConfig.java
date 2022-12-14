package com.ahmet.e_commerce_oct_back.configurations;

import com.ahmet.e_commerce_oct_back.JWT.JwtFilter;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
@AllArgsConstructor
public class WebSecurityConfig {

    private AppUserService userService;

    private PasswordEncoder passwordEncoder;

    private JwtFilter jwtFilter;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(
                ((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            authException.getMessage());
                }
                ));

        http
//                .cors().and()
                .cors(Customizer.withDefaults())
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/api/v1/carts/user/**").hasAuthority("User")
                .antMatchers("/api/v1/categories/admin/**").hasAuthority("Admin")
                .antMatchers("/api/v1/products/admin/**").hasAuthority("Admin")
                .antMatchers("/api/v1/users/admin/**").hasAuthority("Admin")
                .antMatchers("/", "index", "/image/png/**", "/image/jpeg/**", "/css/**", "/js/**").permitAll()
                .antMatchers("/api/v1/auth/**", "/api/v1/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/categories/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/products/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/users/user/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }


    //Last
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//
//        //configuration.setAllowedOrigins(Arrays.asList("https://my-ecom-back.herokuapp.com"));
//        configuration.setAllowedOrigins(Collections.singletonList("*"));  //set access from all domains
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("Accept", "X-Requested-With", "Cache-Control", "Authorization", "Content-Type", "apikey", "tenantId"));
//
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH",
                "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type",
                "x-auth-token", "Accept", "Origin", "apikey", "tenantId", "X-Requested-With", "X-Frame-Options", "Cache-Control"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

//    @Component
//    public class CorsFilter implements Filter {
//
//        @Override
//        public void init(FilterConfig filterConfig) throws ServletException {
//
//        }
//
//        @Override
//        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//            HttpServletResponse response = (HttpServletResponse) res;
//            response.setHeader("Access-Control-Allow-Credentials","true");
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, " +
//                    "X-Requested-With, Content-Type, Accept,Referer,sec-ch-ua,sec-ch-ua-mobile,sec-ch-ua-platform,User-Agent");
//
//            chain.doFilter(req, res);
//        }
//
//        @Override
//        public void destroy() {
//
//        }
//    }


}
