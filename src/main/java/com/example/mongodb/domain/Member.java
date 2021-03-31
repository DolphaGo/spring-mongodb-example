package com.example.mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "my_collection")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Member {

    @Id
    private String id;
    private String name;
    private int age;

    @Builder
    public Member(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
