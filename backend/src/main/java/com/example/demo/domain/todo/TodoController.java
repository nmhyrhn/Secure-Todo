package com.example.demo.domain.todo;

import com.example.demo.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @GetMapping
    public String test(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        return "HELLO " + user.getUsername();
    }
}
