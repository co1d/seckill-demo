package com.zplus.seckilldemo.DO;

public class UserPasswordDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.id
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.encrypt_password
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    private String encryptPassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.user_id
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.encrypt_salt
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    private String encryptSalt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.id
     *
     * @return the value of user_password.id
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.id
     *
     * @param id the value for user_password.id
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.encrypt_password
     *
     * @return the value of user_password.encrypt_password
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    public String getEncryptPassword() {
        return encryptPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.encrypt_password
     *
     * @param encryptPassword the value for user_password.encrypt_password
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword == null ? null : encryptPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.user_id
     *
     * @return the value of user_password.user_id
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.user_id
     *
     * @param userId the value for user_password.user_id
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.encrypt_salt
     *
     * @return the value of user_password.encrypt_salt
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    public String getEncryptSalt() {
        return encryptSalt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.encrypt_salt
     *
     * @param encryptSalt the value for user_password.encrypt_salt
     *
     * @mbg.generated Wed Mar 13 20:46:09 CST 2019
     */
    public void setEncryptSalt(String encryptSalt) {
        this.encryptSalt = encryptSalt == null ? null : encryptSalt.trim();
    }
}