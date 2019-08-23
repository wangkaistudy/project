package com.study.clay.product.entity;

import com.google.common.base.MoreObjects;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "tbProduct")
@Data
@ToString
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    /** 商品数据库主键 */
    private Long id;
    /** 商品名称 */
    private String name;
    /** 商品封面图片 */
    private String coverImage;
    /** 商品价格(分) */
    private int price;
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
              .add("id", getId())
              .add("name", getName()).toString();
    }
    // 省略getters和setters
}
