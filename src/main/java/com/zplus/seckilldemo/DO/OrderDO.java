package com.zplus.seckilldemo.DO;

import java.math.BigDecimal;

public class OrderDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.user_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.comm_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private Integer commId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.comm_price
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private BigDecimal commPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.quantity_purchased
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private Integer quantityPurchased;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_price
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private BigDecimal orderPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.promo_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private Integer promoId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.id
     *
     * @return the value of order_info.id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.id
     *
     * @param id the value for order_info.id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.user_id
     *
     * @return the value of order_info.user_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.user_id
     *
     * @param userId the value for order_info.user_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.comm_id
     *
     * @return the value of order_info.comm_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public Integer getCommId() {
        return commId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.comm_id
     *
     * @param commId the value for order_info.comm_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setCommId(Integer commId) {
        this.commId = commId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.comm_price
     *
     * @return the value of order_info.comm_price
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public BigDecimal getCommPrice() {
        return commPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.comm_price
     *
     * @param commPrice the value for order_info.comm_price
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setCommPrice(BigDecimal commPrice) {
        this.commPrice = commPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.quantity_purchased
     *
     * @return the value of order_info.quantity_purchased
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public Integer getQuantityPurchased() {
        return quantityPurchased;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.quantity_purchased
     *
     * @param quantityPurchased the value for order_info.quantity_purchased
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setQuantityPurchased(Integer quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_price
     *
     * @return the value of order_info.order_price
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_price
     *
     * @param orderPrice the value for order_info.order_price
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.promo_id
     *
     * @return the value of order_info.promo_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public Integer getPromoId() {
        return promoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.promo_id
     *
     * @param promoId the value for order_info.promo_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}