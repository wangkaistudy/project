package com.study.clay.user.controller;

import com.study.clay.user.dto.UserDto;
import com.study.clay.user.entity.User;
import com.study.clay.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserController
 * @Dscription TODO
 * @Author wk
 * @Date 2019/8/22 14:58
 * @Version 1.0
 **/
@RestController
@RequestMapping("/users")
@Api(value = "UserEndpoint", tags = "用户管理相关Api")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(
            value = "获取用户分页数据",
            notes = "获取用户分页数据",
            httpMethod = "GET",
            tags = "用户管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "page",
                    value = "第几页，从0开始，默认为第0页",
                    dataType = "int",
                    paramType = "query"),
            @ApiImplicitParam(
                    name = "size",
                    value = "每一页记录数的大小，默认为20",
                    dataType = "int",
                    paramType = "query"),
            @ApiImplicitParam(
                    name = "sort",
                    value = "排序，格式为：property, property(, ASC|DESC)的方式组织",
                    dataType = "String",
                    paramType = "query")
    })
    public List<UserDto> findAll(Pageable pageable) {
        Page<User> page = this.userService.getPage(pageable);
        if (null!= page){
            // 转换成DTO对象
            return page.getContent().stream().map((user) -> {
                return new UserDto(user);
            }).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(
            value = "获取用户详情数据",
            notes = "获取用户详情数据",
            httpMethod = "GET",
            tags = "用户管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "用户的主键",
                    dataType = "int",
                    paramType = "path")
    })
    public UserDto detail(@PathVariable Long id) {
        User user = this.userService.load(id);
        return (null!= user) ?new UserDto(user) :null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ApiOperation(
            value = "更新用户详情数据",
            notes = "更新用户详情数据",
            httpMethod = "POST",
            tags = "用户管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "用户的主键",
                    dataType = "int",
                    paramType = "path"),
            @ApiImplicitParam(
                    name = "userDto",
                    value = "用户详情数据",
                    dataType = "UserDto",
                    paramType = "body"),
    })
    public UserDto update(@PathVariable Long id,
                          @RequestBody UserDto userDto) {
        userDto.setId(id);
        User user = this.userService.save(userDto);
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(
            value = "删除指定用户",
            notes = "删除指定用户",
            httpMethod = "DELETE",
            tags = "用户管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "所要删除用户的主键",
                    dataType = "int",
                    paramType = "path")
    })
    public boolean delete(@PathVariable Long id) {
        this.userService.delete(id);
        return true;
    }
}


