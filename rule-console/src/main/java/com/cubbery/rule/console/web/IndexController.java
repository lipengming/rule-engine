/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.console.web;

import com.cubbery.rule.console.web.vo.Menu;
import com.cubbery.rule.repository.SceneVo;
import com.cubbery.rule.repository.code.DataDire;
import com.cubbery.rule.repository.code.DataType;
import com.cubbery.rule.repository.code.RuleType;
import com.cubbery.rule.repository.data.DataVo;
import com.cubbery.rule.repository.rule.RuleVo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class IndexController extends BaseAjaxController {

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

    @RequestMapping(value = "/data.htm")
    public String data(Long sceneId,String type,Model model){
        model.addAttribute("sceneId",sceneId);
        model.addAttribute("type",type);
        return "/page/data";
    }

    @RequestMapping(value = "/dataA.htm")
    public void dataA(Long sceneId,String type,HttpServletResponse response){
        outJsonString(response,sceneVo.getDataRepositories());
    }

    @RequestMapping("/menu.htm")
    public void menu(HttpServletRequest request,HttpServletResponse response){
        Menu menu = Menu.fromScene(sceneVo);
        outJsonString(response,Lists.newArrayList(menu));
    }

    static SceneVo sceneVo = new SceneVo();
    static {
        //init
        sceneVo.setDisplayName("工资计算");
        sceneVo.setSceneName("salary");
        sceneVo.setId(1L);

        DataVo dataVo = new DataVo();
        dataVo.setDataType(DataType.INCOME);
        dataVo.setDire(DataDire.IN);
        dataVo.setDisplayName("职位");
        dataVo.setName("position");
        dataVo.setType("java.lang.String");

        DataVo dataVo1 = new DataVo();
        dataVo1.setDataType(DataType.TEMP);
        dataVo1.setDire(DataDire.IO);
        dataVo1.setDisplayName("基本工资");
        dataVo1.setName("salaryBasic");
        dataVo1.setType("java.lang.Double");

        sceneVo.setDataRepositories(Lists.newArrayList(dataVo,dataVo1));

        RuleVo ruleVo = new RuleVo();
        ruleVo.setDisplayName("基本工资计算");
        ruleVo.setName("salary-basic");
        ruleVo.setType(RuleType.SINGLE);
        ruleVo.setId(11L);

        RuleVo ruleVo1 = new RuleVo();
        ruleVo1.setDisplayName("所得税计算");
        ruleVo1.setName("income-tax");
        ruleVo1.setType(RuleType.SETS);
        ruleVo1.setId(12L);

        //...
        sceneVo.setRuleRepositories(Lists.newArrayList(ruleVo,ruleVo1));
    }
}
