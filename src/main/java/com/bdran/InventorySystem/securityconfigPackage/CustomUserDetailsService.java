//package com.bdran.InventorySystem.securityconfigPackage;
//
//import com.bdran.InventorySystem.model.AppUser;
//import com.bdran.InventorySystem.repository.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AppUser user = userRepo.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("username not found"));
//        return new User(user.getUserName(),user.getPassword(), (Collection<? extends GrantedAuthority>) user.getRoles());
//
//
//    }
//}
