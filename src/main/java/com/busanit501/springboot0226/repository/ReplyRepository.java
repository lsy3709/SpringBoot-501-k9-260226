package com.busanit501.springboot0226.repository;

import com.busanit501.springboot0226.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 아무 기능이 없어도, 상속을 받아서, 기본적인 CRUD 가능함.
}
