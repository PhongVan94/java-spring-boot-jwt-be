package phongvan.javaspringbootbackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import phongvan.javaspringbootbackend.config.JwtService;
import phongvan.javaspringbootbackend.entity.Group;
import phongvan.javaspringbootbackend.entity.User;
import phongvan.javaspringbootbackend.repository.GroupRepository;
import phongvan.javaspringbootbackend.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final GroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        String email = request.getEmail();
        String phone = request.getPhone();
        String firstname = request.getFirstname();
        String lastname = request.getLastname();
        String password = request.getPassword();
        try {
            if (repository.findByEmail(email).isPresent()) {
                return AuthenticationResponse.builder()
                        .EC(-1)
                        .EM("THE EMAIL IS ALREADY EXIST")
                        .DT("email")
                        .build();
            }
            if (repository.findByPhone(phone).isPresent()) {
                return AuthenticationResponse.builder()
                        .EC(-1)
                        .EM("THE PHONE NUMBER IS ALREADY EXIST")
                        .DT("email")
                        .build();
            }
            var group = groupRepository.findByName("USER")
                    .orElse(Group.builder()
                            .name("USER")
                            .build()
                    );

            var user = User.builder()
                    .firstname(firstname)
                    .lastname(lastname)
                    .email(email)
                    .phone(phone)
                    .password(passwordEncoder.encode(password))
                    .group(group)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .EC(0)
                    .EM("CREATE USER SUCCESS")
                    .build();
        } catch (Exception e) {
            return AuthenticationResponse.builder()
                    .EC(-1)
                    .EM("SOMETHING WENT WRONG IN SERVER")
                    .DT(null)
                    .build();
        }

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String valueLogin = request.getValueLogin();
        System.out.println(valueLogin);
        String password = request.getPassword();
        System.out.println(password);
        try {
            boolean userExists = repository.findByEmail(valueLogin)
                    .or(() -> repository.findByPhone(valueLogin))
                    .isPresent();

            if (!userExists) {
                return AuthenticationResponse.builder()
                        .EC(-1)
                        .EM("YOUR EMAIL/PHONE NUMBER OR PASSWORD IS INCORRECT")
                        .build();
            }else {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                valueLogin,
                                password
                        )
                );
                var user = repository.findByEmail(valueLogin)
                        .orElseThrow();

                var jwtToken = jwtService.generateToken(user);

                Map<String, Object> data = new HashMap<>();
                data.put("firstname", user.getFirstname());
                data.put("lastname", user.getLastname());
                data.put("phone",user.getPhone());
                data.put("email", user.getEmail());
                data.put("group", user.getGroup());
                data.put("token", jwtToken);

                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .EC(0)
                        .EM("GET DATA SUCCESS")
                        .DT(data)
                        .build();


            }

        } catch (AuthenticationException e) {
            return AuthenticationResponse.builder()
                    .EC(-1)
                    .EM("SOMETHING WENT WRONG IN SERVER")
                    .DT(null)
                    .build();
        }



    }
}
