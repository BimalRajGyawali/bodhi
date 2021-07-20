package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.jwt.JwtUtil;
import group.bonjai.bodhi.jwt.UserDetailsServiceImpl;
import group.bonjai.bodhi.requests.AuthenticationRequest;
import group.bonjai.bodhi.responses.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;


    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping (value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest)
            throws BadCredentialsException {

        authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );



        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(HttpStatus.OK, jwt));
    }

}
