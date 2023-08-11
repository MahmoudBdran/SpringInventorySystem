package com.bdran.InventorySystem.service;


import com.bdran.InventorySystem.model.AppUser;
import com.bdran.InventorySystem.repository.UserRepo;
import com.bdran.InventorySystem.security.AppUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public List<AppUser> findAll(){
        return userRepo.findAll();
    }
    public AppUser findById(Long id){
        return userRepo.findById(id).get();
    }
    public AppUser insert(AppUser appUser){
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepo.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser= userRepo.findByUserName(username);
        if(!appUser.isPresent()){
            throw new UsernameNotFoundException("This user is not found with selected username: "+username);
        }
        return new AppUserDetail(appUser.get());
    }







//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       Optional<AppUser> appUser= userRepo.findByUserName(username);
//       if(!appUser.isPresent()){
//           throw new UsernameNotFoundException("This user is not found with selected uesrname: "+username);
//       }
//        return new User(appUser.get().getUserName(), appUser.get().getPassword(),getAuthorities(appUser.get()));
//    }

//    private  List<GrantedAuthority> getAuthorities(AppUser user){
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if(!user.getRoles().isEmpty()){
//            user.getRoles().forEach(role -> {
//                authorities.add(new SimpleGrantedAuthority(role.getName()));
//            });
//        }
//        return authorities;
//    }
}
