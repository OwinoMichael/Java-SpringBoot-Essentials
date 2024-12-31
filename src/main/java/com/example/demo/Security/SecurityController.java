package com.example.demo.Security;

import com.example.demo.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = JWTUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwtToken));

        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }

    }

    @PostMapping("/createNewUser")
    public ResponseEntity createNewUser(@RequestBody LoginRequest request){
        Optional<CustomUser> customUserOptional = customUserRepository.findById(request.getUsername());
        if(customUserOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name already exists");
        }

        CustomUser user = new CustomUser();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        customUserRepository.save(user);
        return ResponseEntity.ok("success");

    }
}
