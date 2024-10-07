package com.chengfeng.study.myspringbootproject.jparepository;

import com.chengfeng.study.myspringbootproject.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * AuthorRepository class
 *
 * @author chengfeng
 * @date 2020/7/12 /0012 23:04
 */
public interface AuthorRepository extends JpaRepository<User, Integer> {
    @Override
    Optional<User> findById(Integer userId);

}
