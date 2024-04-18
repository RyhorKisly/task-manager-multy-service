package by.itacademy.userservice.endponts.filter;

import by.itacademy.sharedresource.core.enums.UserRole;
import by.itacademy.userservice.config.properites.JWTProperty;
import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.endponts.utils.JwtTokenHandler;
import by.itacademy.userservice.service.api.IUserService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenHandler jwtHandler;
    private final IUserService userService;
    private final JWTProperty property;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @NonNull FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwtHandler.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        String userName = jwtHandler.getUsername(token);
        UsernamePasswordAuthenticationToken authentication;

        if (userName.equals(property.getSystem())) {
            UserDetails userDetails = User.builder()
                    .username(userName)
                    .password(property.getSystem())
                    .roles(UserRole.SYSTEM.name())
                    .build();
            authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null,
                    userDetails.getAuthorities()
            );
        } else {
            UserDTO userDTO = userService.get(userName);
            authentication = new UsernamePasswordAuthenticationToken(
                    userDTO, null,
                    userDTO.getRole() == null ? List.of() : List.of(new SimpleGrantedAuthority("ROLE_" + userDTO.getRole().name()))
            );
        }

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }
    }
