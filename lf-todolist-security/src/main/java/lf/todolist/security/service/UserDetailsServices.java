package lf.todolist.security.service;

import lf.todolist.security.domain.UserDetails;
import lf.todolist.security.exception.EmailNotFoundException;
import lf.todolist.security.exception.MobileNotFoundException;

public interface UserDetailsServices {

    UserDetails loadUserDetailsByEmail(String email) throws EmailNotFoundException;

    UserDetails loadUserDetailsByMobile(String countryCode, String mobile) throws MobileNotFoundException;

}
