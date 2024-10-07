package com.chengfeng.study.myspringbootproject.pojo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Cart class
 * 购物车
 * @author chengfeng
 * @date 2022/4/16 /0016 15:54
 */
public class Cart {
    /**
     * 商品清单
     */
    private List<Item> items;
    /**
     * 总优惠
     */
    private BigDecimal totalDiscount;
    /**
     * 商品总价
     */
    private BigDecimal totalItemPrice;
    /**
     * 总运费
     */
    private BigDecimal totalDeliveryPrice;
    /**
     * 应付总价
     */
    private BigDecimal payPrice;

    public Cart() {
    }

    public Cart(List<Item> items, BigDecimal totalDiscount, BigDecimal totalItemPrice, BigDecimal totalDeliveryPrice, BigDecimal payPrice) {
        this.items = items;
        this.totalDiscount = totalDiscount;
        this.totalItemPrice = totalItemPrice;
        this.totalDeliveryPrice = totalDeliveryPrice;
        this.payPrice = payPrice;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(BigDecimal totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public BigDecimal getTotalDeliveryPrice() {
        return totalDeliveryPrice;
    }

    public void setTotalDeliveryPrice(BigDecimal totalDeliveryPrice) {
        this.totalDeliveryPrice = totalDeliveryPrice;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(items, cart.items) && Objects.equals(totalDiscount, cart.totalDiscount) && Objects.equals(totalItemPrice, cart.totalItemPrice) && Objects.equals(totalDeliveryPrice, cart.totalDeliveryPrice) && Objects.equals(payPrice, cart.payPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, totalDiscount, totalItemPrice, totalDeliveryPrice, payPrice);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", totalDiscount=" + totalDiscount +
                ", totalItemPrice=" + totalItemPrice +
                ", totalDeliveryPrice=" + totalDeliveryPrice +
                ", payPrice=" + payPrice +
                '}';
    }
}
