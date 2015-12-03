/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.param;

import com.cubbery.rule.code.RuleCodeEnum;

import java.io.Serializable;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.param <br>
 * <b>类名称</b>： Response <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Response implements Serializable {
    private Action resultAction;                //结果
    private String msg;                         //描述
    private String code;                        //结果码
    private Boolean success = Boolean.FALSE;    //成功的标记

    public Response() {
        this(RuleCodeEnum.SUCCESS.getCode(),true);
    }

    public Response(String code, Boolean success) {
        this.code = code;
        this.success = success;
    }

    public Response onSuccess(RuleHandler handler) {
        if(getSuccess()) {
            handler.handle(this);
        }
        return this;
    }

    public Response onFailure(RuleHandler handler) {
        if(!getSuccess()) {
            handler.handle(this);
        }
        return this;
    }

    public Action resultAction() {
        return resultAction;
    }

    public Action getResultAction() {
        return resultAction;
    }

    public Response setResultAction(Action resultAction) {
        this.resultAction = resultAction;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
