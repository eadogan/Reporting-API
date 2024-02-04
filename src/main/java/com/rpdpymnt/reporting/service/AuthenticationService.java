package com.rpdpymnt.reporting.service;

import com.rpdpymnt.reporting.config.jwt.helper.JwtHelper;
import com.rpdpymnt.reporting.domain.UserIdModel;
import com.rpdpymnt.reporting.domain.request.SignInRequest;
import com.rpdpymnt.reporting.domain.request.SignUpRequest;
import com.rpdpymnt.reporting.domain.response.AuthenticationResponse;
import com.rpdpymnt.reporting.entity.UserEntity;
import com.rpdpymnt.reporting.exception.InvalidDataException;
import com.rpdpymnt.reporting.exception.InvalidUserException;
import com.rpdpymnt.reporting.repository.UserRepository;
import com.rpdpymnt.reporting.util.RoleEnum;
import com.rpdpymnt.reporting.util.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtHelper jwtHelper;
  private final AuthenticationManager authenticationManager;

    public UserIdModel signup(SignUpRequest request) {
        try {
            var user = UserEntity
                    .builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(RoleEnum.ROLE_USER)
                    .build();

            user = userService.save(user);
            return new UserIdModel(user.getId());
        } catch (InvalidUserException e) {
            throw new InvalidUserException("Invalid user details {}", e);
        }
    }


    public AuthenticationResponse login(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtHelper.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .status(StatusEnum.APPROVED)
                .build();
    }
}