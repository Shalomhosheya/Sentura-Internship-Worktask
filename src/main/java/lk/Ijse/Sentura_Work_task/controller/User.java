package lk.Ijse.Sentura_Work_task.controller;

import lk.Ijse.Sentura_Work_task.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class User {
private UserService userService;


}
