package lf.todolist.security.domain;

import java.util.UUID;

public interface UserDetails extends org.springframework.security.core.userdetails.UserDetails {

    /**
     * Returns the email used to authenticate the user.
     *
     * @return the email
     */
    String getEmail();

    /**
     * Returns the countryCode used to authenticate the user.
     *
     * @return the countryCode
     */
    String getCountryCode();

    /**
     * Returns the mobile used to authenticate the user.
     *
     * @return the mobile
     */
    String getMobile();

    /**
     * Returns the id used to authenticate the user.
     *
     * @return the id
     */
    UUID getUuid();
}
