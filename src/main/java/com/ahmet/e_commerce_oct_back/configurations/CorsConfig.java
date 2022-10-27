//package com.ahmet.e_commerce_oct_back.configurations;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//public class CorsConfig {
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource(){
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        // Below config will allow only following origines from web browser
//        corsConfiguration.setAllowedOrigins(Collections.singletonList("https://my-ecom-back.herokuapp.com"));
//        // Whether user credentials are supported. By default, do not support
//        // If you want to allow credentials then set it true
//        corsConfiguration.setAllowCredentials(true);
//
//        // below will not allow DELETE methods, if you want then add DELETE also
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET","DELETE", "POST", "PUT", "PATCH", "OPTION"));
//
//        // Below will set allowed headers list, Other headers will not be supportedOrigin, " +
//        ////                    "X-Requested-With, Content-Type, Accept
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Accept","X-Requested-With", "Authorization","Content-Type", "apikey", "tenantId"));
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
//    public FilterRegistrationBean corsFilterRegistrationBean(CorsConfigurationSource corsConfigurationSource){
//        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        filterRegistrationBean.setFilter(corsFilter);
//        return filterRegistrationBean;
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests().anyRequest().permitAll().and().cors();
////    }
//
//}
