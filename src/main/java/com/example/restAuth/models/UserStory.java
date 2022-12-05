package com.example.restAuth.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "userstory")
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public UserStory(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public UserStory() {

    }

}
