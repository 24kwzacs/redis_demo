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
 * @TableName order_details
 */
@TableName(value ="order_details")
@Data
public class OrderDetails implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 
     */
    @TableField(value = "price_at_purchase")
    private BigDecimal priceAtPurchase;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}