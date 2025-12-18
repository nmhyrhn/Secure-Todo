package com.example.demo.domain.todo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private boolean completed;

    private String userEmail;
}
