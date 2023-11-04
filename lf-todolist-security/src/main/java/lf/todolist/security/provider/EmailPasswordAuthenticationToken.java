package lf.todolist.security.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.UUID;

public class EmailPasswordAuthenticationToken extends AbstractAuthenticationToken {

    // As email
    private Object principal;
    // As password
    private Object credentials;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public EmailPasswordAuthenticationToken(Object principal, Object credentials, UUID id, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        // Use user ID for details
        this.setDetails(id);
        this.setAuthenticated(true);
    }

    public EmailPasswordAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean implies(Subject subject) {
        return super.implies(subject);
    }

    // Used for strategy type
    @Override
    public String getName() {
        return "EMAIL";
    }
}
