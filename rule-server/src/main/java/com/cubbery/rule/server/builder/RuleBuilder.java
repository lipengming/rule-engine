/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.server.builder;

import com.cubbery.rule.dal.domin.ConditionVo;
import com.cubbery.rule.dal.domin.InstructionVo;
import com.cubbery.rule.dal.domin.RuleVo;

import java.text.SimpleDateFormat;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.server.builder <br>
 * <b>类名称</b>： RuleBuilder <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * 参考 http://docs.jboss.org/drools/release/5.5.0.Final/drools-expert-docs/html/ch04.html
 *
 * @see org.drools.rule.builder.RuleBuilder
 * @see org.drools.compiler.PackageBuilder
 * @version 1.0.0 <br>
 */
class RuleBuilder {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    /**
     * 规则构建
     * @param rule
     * @return
     */
    public static StringBuffer buildRule(RuleVo rule) {
        StringBuffer sb = new StringBuffer().append("\n");
        //设置rule name
        sb.append("rule").append(" ").append("rule_").append(rule.getId()).append("\n");
        //设置过期时间
        sb.append(insertExpiryDate(rule));
        //设置规则条件
        sb.append(insertRuleCondition(rule.getCondition()));
        //设置指令（结果处理）
        sb.append(insertRuleInstructions(rule.getInstruction()));
        return sb;
    }

    /**
     * 设置过期事件(TIME)
     * @param rule
     * @return
     */
    public static StringBuffer insertExpiryDate(RuleVo rule){
        StringBuffer sb = new StringBuffer().append("\n");
        if(rule.getBegin() != null) {
            String begin = sdf.format(rule.getBegin());
            sb.append("date-effective \"" + begin +"\"").append("\n");
        }
        if(rule.getEnd() != null) {
            String end = sdf.format(rule.getEnd());
            sb.append("date-expires \"" + end +"\"").append("\n");
        }
        return sb;
    }

    /**
     * 构建条件(LHS)
     * $member:Member(loginNum>=3 && loginNum<15)
     * @param condition
     * @return
     */
    public static StringBuffer insertRuleCondition(ConditionVo condition){
        StringBuffer sb = new StringBuffer().append("\n");
        sb.append("when").append("\n");
        if(condition == null || condition.getFact() == null) {
            sb.append("eval( true )").append("\n");
            return sb;
        }
        sb.append("$").append(condition.getFact().getName().toLowerCase())//$member
                .append(":").append(condition.getFact().getName())//:Member
                .append("(")
                .append(condition.getCondition())//loginNum>=3 && loginNum<15
                .append(")");
        return sb;
    }

    /**
     * 构建指令(结果RHS)    处理结果放在全局变量里面 $resultAction
     * @param instructions
     * @return
     */
    public static StringBuffer insertRuleInstructions(InstructionVo instructions){
        StringBuffer sb = new StringBuffer().append("\n");
        sb.append("then").append("\n");
        sb.append("$resultAction.put(\"").append(instructions.getName()).append("\",\"").append(instructions.getExpression()).append("\");\n");
        sb.append("end").append("\n");
        return sb;
    }
}