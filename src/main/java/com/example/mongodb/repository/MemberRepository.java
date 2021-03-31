package com.example.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodb.domain.Member;

public interface MemberRepository extends MongoRepository<Member, String> {
    Member findByName(String name);

    List<Member> findByAge(int age);

    void deleteByName(String name);
}