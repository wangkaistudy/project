package com.study.clay.user.service.impl;

import com.study.clay.user.dao.UserRepository;
import com.study.clay.user.dto.UserDto;
import com.study.clay.user.entity.User;
import com.study.clay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    // 注入UserRepository
    @Autowired
    protected UserRepository userRepository;

    @Override
    public Page<User> getPage(Pageable pageable) {
      // 这里直接使用JPA所提供的分页查询功能即可
      return this.userRepository.findAll(pageable);
    }

    @Override
    public User load(Long id) {

      return this.userRepository.findById(id).get();
    }


    @Override
    @Transactional
    public User save(UserDto userDto) {
      // 对于保存，首先获取数据库中存在的对象，然后将所要保存的值复制进去
      User user = this.userRepository.findById(userDto.getId()).get();
      if (null == user) {
          user = new User();
      }
      user.setNickname(userDto.getNickname());
      user.setAvatar(userDto.getAvatar());
      return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
      // 依然使用JPA提供的删除功能即可
      this.userRepository.deleteById(id);
  }
}
