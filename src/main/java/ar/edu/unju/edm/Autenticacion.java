package ar.edu.unju.edm;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

@Component
public class Autenticacion implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> autorizaciones = authentication.getAuthorities();

        for (GrantedAuthority grantedAuthority : autorizaciones) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                redirectStrategy.sendRedirect(request, response, "/admin/principal");
                return;
            } else if (grantedAuthority.getAuthority().equals("ROLE_USUARIO")) {
                redirectStrategy.sendRedirect(request, response, "/usuario/principal");
                return;
            }
        }

        redirectStrategy.sendRedirect(request, response, "/");
    }

}
