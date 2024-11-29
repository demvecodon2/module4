package com.example.demo.sercive;

import com.example.demo.model.AppUser;
import com.example.demo.model.UserRole;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
 @Autowired
 private AppUserRepository appUserRepository;
@Autowired
private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findUserByUserName(username);
    if(appUser == null) {
logger.error("User not found! Username: {}", username);
        throw new UsernameNotFoundException("User " + username + " was not found in the database");
    }
    logger.info("Found User : {}",appUser.getUserName());
        List<UserRole> userRoles =this.userRoleRepository.findByAppUser(appUser);
        List<GrantedAuthority> grantList = new ArrayList<>();
        if(userRoles == null || !userRoles.isEmpty()) {
            for(UserRole userRole : userRoles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getAppRole().getRoleName());
               grantList.add(authority);

            }
        }else{
            logger.warn("No roles found for user: {}", username);
        }
        return new User(appUser.getUserName(),appUser.getEncryptedPassword(),grantList);
    }
}
