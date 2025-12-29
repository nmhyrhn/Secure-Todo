package com.example.demo.controller;

import com.example.demo.domain.todo.Todo;
import com.example.demo.domain.todo.TodoRepository;
import com.example.demo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Todo> myTodos(Authentication auth) {
        return todoRepository.findByUserEmail(auth.getName());
    }

    @PostMapping
    public Todo create(@RequestBody Map<String, String> body, Authentication auth) {
        Todo todo = new Todo();
        todo.setTitle(body.get("title"));
        todo.setContent(body.get("content"));
        todo.setCompleted(false);

        userRepository.findByEmail(auth.getName()).ifPresent(todo::setUser);

        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setTitle((String) body.get("title"));
        todo.setContent((String) body.get("content"));
        todo.setCompleted((Boolean) body.get("completed"));
        return todoRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}
