package by.itacademy.userservice.endponts.filter;

import by.itacademy.sharedresource.core.enums.UserRole;
import by.itacademy.userservice.config.properites.JWTProperty;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.endponts.utils.JwtTokenHandler;
import by.itacademy.userservice.service.api.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenHandler jwtHandler;
    private final IUserService userService;
    private final JWTProperty property;

    public JwtFilter(
            JwtTokenHandler jwtHandler,
            IUserService userService,
            JWTProperty property
    ) {
        this.jwtHandler = jwtHandler;
        this.userService = userService;
        this.property = property;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtHandler.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        String userName = jwtHandler.getUsername(token);
        UserDetails userDetails;

        if(userName.equals(property.getSystem())) {
            userDetails = User.builder()
                    .username(userName)
                    .password(property.getSystem())
                    .roles(UserRole.SYSTEM.name())
                    .build();
        } else {
            UserEntity userEntity = userService.get(userName);
            userDetails = User.builder()
                    .username(userEntity.getMail())
                    .password(userEntity.getPassword())
                    .roles(userEntity.getRole().name())
                    .build();
        }




        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}