package phongvan.javaspringbootbackend.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class TestAuthController {

    @GetMapping("/userProfile")
    @PreAuthorize("hasAuthority('GUEST')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

}