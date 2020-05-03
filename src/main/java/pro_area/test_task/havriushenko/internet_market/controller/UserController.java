package pro_area.test_task.havriushenko.internet_market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;
import pro_area.test_task.havriushenko.internet_market.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sing-up")
    public void singUp(@RequestBody UserDto user){
        userService.singUp(user);
    }
}
