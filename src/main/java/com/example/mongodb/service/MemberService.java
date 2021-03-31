package com.example.mongodb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mongodb.domain.Member;
import com.example.mongodb.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MongoTemplate mongoTemplate;

    /**
     * @implNote : Create or Update
     * db.my_collection.insert([
     *     {
     *         "name" : "DolphaGo",
     *         "age" : 15
     *     }
     * ])
     */
    public void insert() {
        Member member = Member.builder()
                              .name("DolphaGo")
                              .age(15)
                              .build();

        memberRepository.save(member); // repository 버전
        mongoTemplate.insert(member); // mongoTemplate 버전
    }

    /**
     * @implNote : Read - Find All
     * db.my_collection.find()
     * .pretty() 를 넣을 경우 이쁘게 나온다.
     * db.my_collection.find().pretty()
     */
    public List<Member> selectAll() {
        //Repository 버전
//        List<Member> list_by_Repository = memberRepository.findAll();

        //mongoTemplate 버전
        List<Member> list_by_mongoTemplate = mongoTemplate.findAll(Member.class);

        return list_by_mongoTemplate;
    }

    /**
     * @implNote : paging
     * // Page 1
     * db.my_collection.find().limit(3)
     *
     * // Page 2
     * db.my_collection.find().skip(3).limit(3)
     *
     * // Page 3
     * db.my_collection.find().skip(3).limit(3)
     */
    public void selectPaging() {
        Pageable pageable = PageRequest.of(0, 10);

        //Repository 버전
//        Page<Member> page_1 = memberRepository.findAll(pageable);

        Query query = new Query().with(pageable);
        List<Member> list = mongoTemplate.find(query, Member.class);

        //mongoTemplate 버전
        Page<Member> page_2 = PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(new Query().limit(-1).skip(-1), Member.class));
    }

    /**
     * @implNote : delete
     *
     * db.my_collection.deleteOne( { name: "DolphaGo" } )
     */
    public void deleteByName(String name){
        memberRepository.deleteByName("name"); // Repository 버전
        Query query =new Query(new Criteria().andOperator(Criteria.where("name").is(name)));
        Member andRemove = mongoTemplate.findAndRemove(query, Member.class);
        log.info("삭제된 녀석 {}",andRemove);
    }
}
