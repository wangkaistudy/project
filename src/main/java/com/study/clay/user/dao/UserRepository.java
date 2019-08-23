package com.study.clay.user.dao;

import com.study.clay.user.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @ClassName UserRepository
 * @Dscription TODO
 * @Author wk
 * @Date 2019/8/22 13:58
 * @Version 1.0
 **/
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
}
