/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.console.web;

import com.cubbery.rule.console.utils.JsonConverter;
import com.cubbery.rule.console.utils.JsonResult;
import com.cubbery.rule.console.web.vo.Menu;
import com.cubbery.rule.repository.SceneVo;
import com.cubbery.rule.repository.code.DataDire;
import com.cubbery.rule.repository.code.DataType;
import com.cubbery.rule.repository.code.RuleType;
import com.cubbery.rule.repository.data.DataVo;
import com.cubbery.rule.repository.rule.RuleVo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.console.web <br>
 * <b>类名称</b>： IndexController <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/10 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@Controller
public class IndexController {

    @RequestMapping("/console.htm")
    public String index(){
        return "console";
    }

    @RequestMapping("/scene.htm")
      public String scene(Long sceneId){
        return "/page/scene";
    }

    @RequestMapping("/package.htm")
    public String packages(Long packageId){
        return "/page/scene";
    }

    @RequestMapping("/rule.htm")
    public String rule(Long ruleId){
        return "/page/rule";
    }

    @RequestMapping("/data.htm")
    public String data(Long sceneId){
        return "/page/data";
    }

    @ResponseBody
    @RequestMapping("/menu.htm")
    public String menu(HttpServletRequest request,HttpServletResponse response){
        Menu menu = Menu.fromScene(sceneVo);
        return JsonConverter.serialize(Lists.newArrayList(menu));
    }

    static SceneVo sceneVo = new SceneVo();
    static {
        //init
        sceneVo.setDisplayName("工资计算");
        sceneVo.setSceneName("salary");
        sceneVo.setId(1L);

        DataVo dataVo = new DataVo();
        dataVo.setDataType(DataType.IMPORT);
        dataVo.setDire(DataDire.IN);
        dataVo.setDisplayName("职位");
        dataVo.setName("position");
        dataVo.setType("java.lang.String");

        sceneVo.setDataRepositories(Lists.newArrayList(dataVo));

        RuleVo ruleVo = new RuleVo();
        ruleVo.setDisplayName("基本工资");
        ruleVo.setName("salary-basic");
        ruleVo.setType(RuleType.SINGLE);
        ruleVo.setId(11L);
        //...
        sceneVo.setRuleRepositories(Lists.newArrayList(ruleVo));
    }
}
