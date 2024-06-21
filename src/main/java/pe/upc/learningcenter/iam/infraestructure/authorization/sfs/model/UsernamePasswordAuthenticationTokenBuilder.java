package pe.upc.learningcenter.iam.infraestructure.authorization.sfs.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationTokenBuilder {

    public static UsernamePasswordAuthenticationToken build(UserDetails principal, HttpServletRequest request) {

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return usernamePasswordAuthenticationToken;
    }


}

