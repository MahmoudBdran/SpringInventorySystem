//package com.bdran.InventorySystem.controller;
//import com.bdran.InventorySystem.dto.AuthResponseDTO;
//import com.bdran.InventorySystem.dto.LoginDto;
//import com.bdran.InventorySystem.repository.RoleRepo;
//import com.bdran.InventorySystem.repository.UserRepo;
//import com.bdran.InventorySystem.securityconfigPackage.JWTGenerator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//public class AuthController {
//
//    @RestController
//    @RequestMapping("/api/auth")
//    public class AuthController {
//
//        private AuthenticationManager authenticationManager;
//        private UserRepo userRepository;
//        private RoleRepo roleRepository;
//        private PasswordEncoder passwordEncoder;
//        private JWTGenerator jwtGenerator;
//
//        @Autowired
//        public AuthController(AuthenticationManager authenticationManager, UserRepo userRepository,
//                              RoleRepo roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
//            this.authenticationManager = authenticationManager;
//            this.userRepository = userRepository;
//            this.roleRepository = roleRepository;
//            this.passwordEncoder = passwordEncoder;
//            this.jwtGenerator = jwtGenerator;
//        }
//
//        @PostMapping("login")
//        public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginDto.getUsername(),
//                            loginDto.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String token = jwtGenerator.generateToken(authentication);
//            return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
//        }
//
//        @PostMapping("register")
//        public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
//            if (userRepository.findByUserName(registerDto.getUsername())) {
//                return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
//            }
//
//            UserEntity user = new UserEntity();
//            user.setUsername(registerDto.getUsername());
//            user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
//
//            Role roles = roleRepository.findByName("USER").get();
//            user.setRoles(Collections.singletonList(roles));
//
//            userRepository.save(user);
//
//            return new ResponseEntity<>("User registered success!", HttpStatus.OK);
//        }
//    }
