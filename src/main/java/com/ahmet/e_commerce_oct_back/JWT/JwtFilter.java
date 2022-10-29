package com.ahmet.e_commerce_oct_back.JWT;

import com.ahmet.e_commerce_oct_back.configurations.AppUserService;
import com.ahmet.e_commerce_oct_back.entities.AppUser;
import lombok.AllArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private AppUserService appUserService;
    private JwtCreate jwtCreate;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        //System.out.println(header);

        if (!StringUtils.hasText(header) || StringUtils.hasText(header) && !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.split(" ")[1].trim();
        //System.out.println(token);
        if (!jwtCreate.isValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String email = jwtCreate.getSubject(token);

        AppUser userDetails = (AppUser) appUserService.loadUserByUsername(email);
        //CustomUserDetails userDetails = new CustomUserDetails(user);
        //System.out.println(userDetails);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);


    }
}
