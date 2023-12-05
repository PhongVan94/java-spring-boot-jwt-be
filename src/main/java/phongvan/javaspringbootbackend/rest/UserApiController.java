package phongvan.javaspringbootbackend.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import phongvan.javaspringbootbackend.entity.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@PreAuthorize("hasAuthority('USER')")
public class UserApiController {

    private final UserApiService service;
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/read")
    public ResponseEntity<Response> getUserWithPagination(@RequestParam int page, @RequestParam int limit){
        return ResponseEntity.ok(service.getUserWithPagination(page,limit));
    }
    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(service.createUser(userRequest));
    }
    @PutMapping("/update")
    public ResponseEntity<Response> updateUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(service.updateUser(userRequest));
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable int id){
        return ResponseEntity.ok(service.deleteUser(id));
    }
}
