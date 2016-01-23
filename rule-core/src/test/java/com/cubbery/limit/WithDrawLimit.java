/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.limit;


/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.limit <br>
 * <b>类名称</b>： WithDrawLimit <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class WithDrawLimit {

    private Channel bankCode;//提现渠道
    private String bankCardNumber;//卡
    private String customerId;//会员

    private int total;//记账成功
    private int apply;//申请成功
    private int available;//可用金额

    private CustomerRecords customerRecords;

    public Channel getBankCode() {
        return bankCode;
    }

    public void setBankCode(Channel bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getApply() {
        return apply;
    }

    public void setApply(int apply) {
        this.apply = apply;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public CustomerRecords getCustomerRecords() {
        return customerRecords;
    }

    public void setCustomerRecords(CustomerRecords customerRecords) {
        this.customerRecords = customerRecords;
    }

    @Override
    public String toString() {
        return "WithDrawLimit{" +
                ", bankCode=" + bankCode +
                ", bankCardNumber='" + bankCardNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", total=" + total +
                ", apply=" + apply +
                ", available=" + available +
                ", customerRecords=" + customerRecords +
                '}';
    }
}
