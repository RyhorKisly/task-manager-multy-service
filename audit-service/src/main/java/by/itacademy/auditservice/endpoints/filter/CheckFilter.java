package by.itacademy.auditservice.endpoints.filter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
@RequiredArgsConstructor
public class CheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @NonNull FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader("X-Checker");
        if (!isEmpty(header) && header.equals("check")) {
            chain.doFilter(request, response);
        }
    }
}
