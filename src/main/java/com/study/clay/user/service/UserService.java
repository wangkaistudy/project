package com.study.clay.user.service;

import com.study.clay.user.dto.UserDto;
import com.study.clay.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    /**
    * 获取用户分页数据
    * @param pageable分页参数
    * @return 分页数据
    */
    Page<User> getPage(Pageable pageable);
    /**
    * 加载指定的用户信息
    * @param id 用户主键
    * @return
    */
   User load(Long id);
   /**
    * 保存/更新用户
    * @param userDto
    * @return
    */
   User save(UserDto userDto);
   /**
    * 删除指定用户
    * @param id 所要删除的用户主键
    */
   void delete(Long id);
}
