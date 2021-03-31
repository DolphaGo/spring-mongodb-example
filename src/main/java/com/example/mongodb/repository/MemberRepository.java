package com.example.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.mongodb.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
    Member findByName(String name);

    List<Member> findByAge(int age);

    void deleteByName(String name);
}