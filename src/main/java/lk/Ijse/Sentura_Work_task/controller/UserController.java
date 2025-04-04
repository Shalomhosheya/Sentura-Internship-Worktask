package lk.Ijse.Sentura_Work_task.controller;

import lk.Ijse.Sentura_Work_task.dto.UserDto;
import lk.Ijse.Sentura_Work_task.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto user) {
        String response = userService.createWeavyUser(user);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
