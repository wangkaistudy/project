package com.study.clay.user.entity;

import com.google.common.base.MoreObjects;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

// @Entity、@Table和下面的@Id、@GeneratedValued注解后面再进行说明
@Entity
@Table(name = "tbUser")
@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户数据库主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;

    // 使用了Guava中的MoreObjects.ToStringHelper来辅助处理toString
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("nickname", getNickname()).toString();
    }

    public User() {
    }

    public User(String nickname) {
        this.nickname = nickname;

    }

    public User(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
    }

    // 省略getters和setters
}
