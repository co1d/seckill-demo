package com.zplus.seckilldemo.DO;

import java.math.BigDecimal;
import java.util.Date;

public class PromoDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo_info.id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo_info.promo_name
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private String promoName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo_info.start_date
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo_info.end_date
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo_info.comm_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private Integer commId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo_info.promo_comm_price
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    private BigDecimal promoCommPrice;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo_info.id
     *
     * @return the value of promo_info.id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo_info.id
     *
     * @param id the value for promo_info.id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo_info.promo_name
     *
     * @return the value of promo_info.promo_name
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public String getPromoName() {
        return promoName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo_info.promo_name
     *
     * @param promoName the value for promo_info.promo_name
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setPromoName(String promoName) {
        this.promoName = promoName == null ? null : promoName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo_info.start_date
     *
     * @return the value of promo_info.start_date
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo_info.start_date
     *
     * @param startDate the value for promo_info.start_date
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo_info.end_date
     *
     * @return the value of promo_info.end_date
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo_info.end_date
     *
     * @param endDate the value for promo_info.end_date
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo_info.comm_id
     *
     * @return the value of promo_info.comm_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public Integer getCommId() {
        return commId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo_info.comm_id
     *
     * @param commId the value for promo_info.comm_id
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setCommId(Integer commId) {
        this.commId = commId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo_info.promo_comm_price
     *
     * @return the value of promo_info.promo_comm_price
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public BigDecimal getPromoCommPrice() {
        return promoCommPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo_info.promo_comm_price
     *
     * @param promoCommPrice the value for promo_info.promo_comm_price
     *
     * @mbg.generated Fri Mar 15 14:17:34 CST 2019
     */
    public void setPromoCommPrice(BigDecimal promoCommPrice) {
        this.promoCommPrice = promoCommPrice;
    }
}