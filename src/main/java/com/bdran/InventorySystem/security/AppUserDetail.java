//package com.bdran.InventorySystem.security;
//
//import com.bdran.InventorySystem.model.AppUser;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class AppUserDetail implements UserDetails {
//    private Long id;
//    private String fullName;
//    private String userName;
//    private String password;
//    private List<GrantedAuthority> authorities;
//    public AppUserDetail() {
//    }
//    public AppUserDetail(AppUser appUser) {
//        this.id=appUser.getId();
//        this.fullName=appUser.getFullName();
//        this.userName=appUser.getUserName();
//        this.password=appUser.getPassword();
//
//            authorities = new ArrayList<>();
//            if(!appUser.getRoles().isEmpty()){
//                appUser.getRoles().forEach(role -> {
//                    authorities.add(new SimpleGrantedAuthority(role.getName()));
//                });
//            }
//
//
//    }
//
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
