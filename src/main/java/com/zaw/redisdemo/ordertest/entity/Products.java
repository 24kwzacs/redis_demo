package com.zaw.redisdemo.ordertest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName products
 */
@TableName(value ="products")
@Data
public class Products implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 
     */
    @TableField(value = "stock")
    private Integer stock;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}