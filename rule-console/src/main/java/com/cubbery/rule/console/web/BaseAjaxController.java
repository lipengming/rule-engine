/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.console.web;

import com.cubbery.rule.console.utils.JsonConverter;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.console.web <br>
 * <b>类名称</b>： BaseAjaxController <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/10 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class BaseAjaxController extends MultiActionController {
    private static DateFormat normalDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static DateFormat otherDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    protected void bindObject(HttpServletRequest request, Object object) {
        ServletRequestDataBinder binder = new ServletRequestDataBinder(object);
        binder.registerCustomEditor(java.util.Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(BaseAjaxController.normalDateFormat.parse(value));
                } catch (ParseException e) {
                    try {
                        setValue(BaseAjaxController.otherDateFormat.parse(value));
                    } catch (ParseException e1) {
                        setValue(null);
                    }
                }
            }

            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format((java.util.Date) getValue());
            }
        });
        binder.bind(request);
    }

    public void outJsonString(HttpServletResponse response, Object object, String ContentType) {
        response.setContentType(ContentType);
        response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        try {
            PrintWriter out = response.getWriter();
            out.write(toJson(object));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outJsonString(HttpServletResponse response, String json) {
        response.setContentType("text/javascript;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        try {
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void outJsonString(HttpServletResponse response, Object object) {
        response.setContentType("text/javascript;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        try {
            PrintWriter out = response.getWriter();
            String s = toJson(object);
            out.write(s);
            out.close();
        } catch (IOException e) {
            logger.warn("BaseAjaxController | outJsonString | " + object + " | error:" + e.getMessage());
        }
    }

    private String toJson(Object object) {
        return JsonConverter.serialize(object);
    }

}
