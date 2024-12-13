package ingress.flightms.config;

import ingress.common.config.JwtSessionData;
import ingress.common.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationService extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final JwtSessionData jwtSessionData;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Jws<Claims> claimsJws = jwtService.parseToken(token, response);
            Authentication authentication = getAuthentication(claimsJws.getPayload(), response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            jwtSessionData.setUserId(0L);
            jwtSessionData.setUsername("eli.cabbarov2003@gmail.com");
            jwtSessionData.setRole("anonymous");
        }
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(Claims claims, HttpServletResponse response) {
        log.info("Will be mapped this jwt claims data {}", claims);

        try {

            String role = claims.get("role", String.class);
            jwtSessionData.setUserId(claims.get("userId", Long.class));
            jwtSessionData.setUsername(claims.get("username", String.class));
            jwtSessionData.setRole(role);
            return new UsernamePasswordAuthenticationToken(null, claims.get("username", String.class), List.of(new SimpleGrantedAuthority(role)));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException("Invalid token");
        }
    }
}
