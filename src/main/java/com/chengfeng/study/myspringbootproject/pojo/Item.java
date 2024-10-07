package com.chengfeng.study.myspringbootproject.pojo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Item class
 * 商品
 * @author chengfeng
 * @date 2022/4/16 /0016 15:54
 */
public class Item {
    /**
     * 商品id
     */
    private long id;
    /**
     * 商品数量
     */
    private int quantity;
    /**
     * 商品单价
     */
    private BigDecimal price;
    /**
     * 商品优惠
     */
    private BigDecimal couponPrice;
    /**
     * 商品运费
     */
    private BigDecimal deliveryPrice;

    public Item() {
    }

    public Item(long id, int quantity, BigDecimal price, BigDecimal couponPrice, BigDecimal deliveryPrice) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.couponPrice = couponPrice;
        this.deliveryPrice = deliveryPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && quantity == item.quantity && Objects.equals(price, item.price) && Objects.equals(couponPrice, item.couponPrice) && Objects.equals(deliveryPrice, item.deliveryPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price, couponPrice, deliveryPrice);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", couponPrice=" + couponPrice +
                ", deliveryPrice=" + deliveryPrice +
                '}';
    }
}
