package lf.todolist.security.domain;

import lf.todolist.intf.enums.RoleEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

public class AuthUser implements UserDetails, CredentialsContainer {

    private static final Log logger = LogFactory.getLog(User.class);

    private final UUID uuid;

    private final String countryCode;

    private final String mobile;

    private final String email;

    private String password;

    private final String username;

    private final GrantedAuthority authority;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     */
    public AuthUser(UUID uuid, String email, String password, GrantedAuthority authority) {
        this(uuid, email, null, null, null, password, true, true, true, true, authority);
    }

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     */
    public AuthUser(UUID uuid, String countryCode, String mobile, String password, GrantedAuthority authority) {
        this(uuid, null, countryCode, mobile, null, password, true, true, true, true, authority);
    }


    /**
     * Construct the <code>User</code> with the details required by
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
     *
     * @param username              the username presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param password              the password that should be presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param enabled               set to <code>true</code> if the user is enabled
     * @param accountNonExpired     set to <code>true</code> if the account has not expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials have not
     *                              expired
     * @param accountNonLocked      set to <code>true</code> if the account is not locked
     * @param authority             the authorities that should be granted to the caller if they
     *                              presented the correct username and password and the user is enabled. Not null.
     * @throws IllegalArgumentException if a <code>null</code> value was passed either as
     *                                  a parameter or as an element in the <code>GrantedAuthority</code> collection
     */
    public AuthUser(UUID uuid, String email, String countryCode, String mobile, String username, String password, boolean enabled,
                    boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                    GrantedAuthority authority) {
        Assert.isTrue(username != null && !"".equals(username) && password != null,
                "Cannot pass null or empty values to constructor");
        this.uuid = uuid;
        this.email = email;
        this.countryCode = countryCode;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authority = authority;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getCountryCode() {
        return this.countryCode;
    }

    @Override
    public String getMobile() {
        return this.mobile;
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
        return List.of(this.authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AuthUser user) {
            if (Objects.nonNull(this.email) && Objects.nonNull(user.email))
                return this.email.equals(user.email);
            else if (Objects.nonNull(this.countryCode) && Objects.nonNull(user.countryCode)
                    && Objects.nonNull(this.mobile) && Objects.nonNull(user.mobile))
                return this.countryCode.equals(user.countryCode)
                        && this.mobile.equals(user.mobile);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public String toString() {
        String sb = getClass().getName() + " [" +
                "Email=" + this.email + ", " +
                "CountryCode=" + this.countryCode + ", " +
                "Mobile=" + this.mobile + ", " +
                "Username=" + this.username + ", " +
                "Password=[PROTECTED], " +
                "Enabled=" + this.enabled + ", " +
                "AccountNonExpired=" + this.accountNonExpired + ", " +
                "credentialsNonExpired=" + this.credentialsNonExpired + ", " +
                "AccountNonLocked=" + this.accountNonLocked + ", " +
                "Granted Authorities=" + this.authority + "]";
        return sb;
    }

    public static AuthUserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static AuthUserBuilder builder() {
        return new AuthUserBuilder();
    }

    // TODO Remove
    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to
            // the set. If the authority is null, it is a custom authority and should
            // precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }

    }

    public static final class AuthUserBuilder {

        private UUID uuid;

        private String countryCode;

        private String mobile;

        private String email;

        private String username;

        private String password;

        private GrantedAuthority authority;

        private boolean accountExpired;

        private boolean accountLocked;

        private boolean credentialsExpired;

        private boolean disabled;

        private Function<String, String> passwordEncoder = (password) -> password;

        private AuthUserBuilder() {
        }

        public AuthUserBuilder uuid(UUID uuid) {
            Assert.notNull(uuid, "uuid cannot be null");
            this.uuid = uuid;
            return this;
        }

        public AuthUserBuilder email(String email) {
            Assert.notNull(email, "email cannot be null");
            this.email = email;
            return this;
        }

        public AuthUserBuilder countryCode(String countryCode) {
            Assert.notNull(countryCode, "countryCode cannot be null");
            this.countryCode = countryCode;
            return this;
        }

        public AuthUserBuilder mobile(String mobile) {
            Assert.notNull(mobile, "mobile cannot be null");
            this.mobile = mobile;
            return this;
        }

        public AuthUserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public AuthUserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public AuthUserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public AuthUserBuilder authority(RoleEnum... roles) {
            return authority(new SimpleGrantedAuthority(Arrays.toString(roles)));
        }

        public AuthUserBuilder authority(GrantedAuthority authority) {
            this.authority = authority;
            return this;
        }

        public AuthUserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public AuthUserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public AuthUserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public AuthUserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails build() {
            String encodedPassword = this.passwordEncoder.apply(this.password);
            return new AuthUser(this.uuid, this.email, this.countryCode, this.mobile, this.username, encodedPassword,
                    !this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authority);
        }
    }
}
