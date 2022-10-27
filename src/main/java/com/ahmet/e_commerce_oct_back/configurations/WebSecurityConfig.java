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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(
                ((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            authException.getMessage());
                }
                ));

        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/", "index", "/image/png/**", "/image/jpeg/**", "/css/**", "/js/**").permitAll()
                .antMatchers("/api/v1/auth/**", "/api/v1/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/categories/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/products/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/users/user/**").permitAll()
                .antMatchers("/api/v1/categories/admin/**").hasAuthority("Admin")
                .antMatchers("/api/v1/products/admin/**").hasAuthority("Admin")
                .antMatchers("/api/v1/users/admin/**").hasAuthority("Admin")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);



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

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//
//        //configuration.setAllowedOrigins(Arrays.asList("https://my-ecom-back.herokuapp.com")); // www - obligatory
//       configuration.setAllowedOrigins(Collections.singletonList("*"));  //set access from all domains
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("Accept", "X-Requested-With","Cache-Control", "Authorization", "Content-Type", "apikey", "tenantId"));
//
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }


//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        // Below config will allow only following origines from web browser
//        corsConfiguration.setAllowedOrigins(Collections.singletonList("https://my-ecom-back.herokuapp.com"));
//        // Whether user credentials are supported. By default, do not support
//        // If you want to allow credentials then set it true
//        corsConfiguration.setAllowCredentials(true);
//
//        // below will not allow DELETE methods, if you want then add DELETE also
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "DELETE", "POST", "PUT", "PATCH", "OPTION"));
//
//        // Below will set allowed headers list, Other headers will not be supportedOrigin, " +
//        ////                    "X-Requested-With, Content-Type, Accept
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Accept", "X-Requested-With", "Authorization", "Content-Type", "apikey", "tenantId"));
//
//        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
//
//        // This will register above configurations on all resources from the root
//        // If you want different rules for different resources then create separate configuration
//        // and register on separate resource path uri
//        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return corsConfigurationSource;
//    }
//
//    @Bean
//    public FilterRegistrationBean corsFilterRegistrationBean(CorsConfigurationSource corsConfigurationSource) {
//        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        filterRegistrationBean.setFilter(corsFilter);
//        return filterRegistrationBean;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll().and().cors();
//    }

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
