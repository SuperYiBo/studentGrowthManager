package com.yeqifu.growth.controller;

import com.yeqifu.growth.entity.Rule;
import com.yeqifu.growth.service.IRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("growth")
public class GrowthController {
    @Autowired
    private IRuleService ruleService;

    /**
     * 跳转到学年成绩提交
     */
    @RequestMapping("scoreSubmit")
    public String losingManager() {
        return "growth/student/scoreSubmit";
    }

    /**
     * 跳转到学年成绩提交
     */
    @RequestMapping("scoreManager")
    public String scoreManager() {
        return "growth/teacher/scoreManager";
    }

    /**
     * 跳转到学生出版书籍信息提交
     */
    @RequestMapping("bookSubmit")
    public String outBook(Model model) {
        Rule bookRule = ruleService.getById(1);
        model.addAttribute("bookRule",bookRule);
        return "growth/student/bookSubmit";
    }

    /**
     * 跳转到学生出版书籍信息教师审核
     */
    @RequestMapping("bookChecking")
    public String bookCheck(Model model) {
        Rule bookRule = ruleService.getById(1);
        model.addAttribute("bookRule",bookRule);
        return "growth/teacher/bookCheck";
    }

    /**
     * 跳转到学生出版论文信息提交
     */
    @RequestMapping("paperSubmit")
    public String paperSubmit(Model model) {
        Rule paperRule = ruleService.getById(2);
        model.addAttribute("paperRule",paperRule);
        return "growth/student/paperSubmit";
    }

    /**
     * 跳转到学生出版论文信息教师审核
     */
    @RequestMapping("paperChecking")
    public String paperCheck(Model model) {
        Rule paperRule = ruleService.getById(2);
        model.addAttribute("paperRule",paperRule);
        return "growth/teacher/paperCheck";
    }

    /**
     * 跳转到学生创新创业课题研究信息提交
     */
    @RequestMapping("innovateSubmit")
    public String innovateSubmit(Model model) {
        Rule innovateRule = ruleService.getById(3);
        model.addAttribute("innovateRule",innovateRule);
        return "growth/student/innovateSubmit";
    }

    /**
     * 跳转到学生创新创业课题研究信息教师审核
     */
    @RequestMapping("innovateCheck")
    public String innovateCheck(Model model) {
        Rule innovateRule = ruleService.getById(3);
        model.addAttribute("innovateRule",innovateRule);
        return "growth/teacher/innovateCheck";
    }

    /**
     * 跳转到学生竞赛信息提交
     */
    @RequestMapping("competitionSubmit")
    public String competitionSubmit(Model model) {
        Rule competitionRule = ruleService.getById(4);
        model.addAttribute("competitionRule",competitionRule);
        return "growth/student/competitionSubmit";
    }

    /**
     * 跳转到学生竞赛信息教师审核
     */
    @RequestMapping("competitionCheck")
    public String competitionCheck(Model model) {
        Rule competitionRule = ruleService.getById(4);
        model.addAttribute("competitionRule",competitionRule);
        return "growth/teacher/competitionCheck";
    }
    /**
     * 跳转到学生专利信息提交
     */
    @RequestMapping("patentSubmit")
    public String patentSubmit(Model model) {
        Rule patentRule = ruleService.getById(5);
        model.addAttribute("patentRule",patentRule);
        return "growth/student/patentSubmit";
    }

    /**
     * 跳转到学生专利信息教师审核
     */
    @RequestMapping("patentCheck")
    public String patentCheck(Model model) {
        Rule patentRule = ruleService.getById(5);
        model.addAttribute("patentRule",patentRule);
        return "growth/teacher/patentCheck";
    }
    /**
     * 跳转到学生证书信息提交
     */
    @RequestMapping("certificateSubmit")
    public String certificateSubmit(Model model) {
        Rule certificateRule = ruleService.getById(6);
        model.addAttribute("certificateRule",certificateRule);
        return "growth/student/certificateSubmit";
    }

    /**
     * 跳转到学生证书信息教师审核
     */
    @RequestMapping("certificateCheck")
    public String certificateCheck(Model model) {
        Rule certificateRule = ruleService.getById(6);
        model.addAttribute("certificateRule",certificateRule);
        return "growth/teacher/certificateCheck";
    }
    /**
     * 跳转到学生志愿信息提交
     */
    @RequestMapping("volunteerSubmit")
    public String volunteerSubmit(Model model) {
        Rule volunteerRule = ruleService.getById(7);
        model.addAttribute("volunteerRule",volunteerRule);
        return "growth/student/volunteerSubmit";
    }

    /**
     * 跳转到学生志愿信息教师审核
     */
    @RequestMapping("volunteerCheck")
    public String volunteerCheck(Model model) {
        Rule volunteerRule = ruleService.getById(7);
        model.addAttribute("volunteerRule",volunteerRule);
        return "growth/teacher/volunteerCheck";
    }
    /**
     * 跳转到学生工作信息提交
     */
    @RequestMapping("workSubmit")
    public String workSubmit(Model model) {
        Rule workRule = ruleService.getById(8);
        model.addAttribute("workRule",workRule);
        return "growth/student/workSubmit";
    }

    /**
     * 跳转到学生工作信息教师审核
     */
    @RequestMapping("workCheck")
    public String workCheck(Model model) {
        Rule workRule = ruleService.getById(8);
        model.addAttribute("workRule",workRule);
        return "growth/teacher/workCheck";
    }
    /**
     * 跳转到学生荣誉信息提交
     */
    @RequestMapping("honorSubmit")
    public String honorSubmit(Model model) {
        Rule honorRule = ruleService.getById(9);
        model.addAttribute("honorRule",honorRule);
        return "growth/student/honorSubmit";
    }

    /**
     * 跳转到学生荣誉信息教师审核
     */
    @RequestMapping("honorCheck")
    public String honorCheck(Model model) {
        Rule honorRule = ruleService.getById(9);
        model.addAttribute("honorRule",honorRule);
        return "growth/teacher/honorCheck";
    }

    /**
     * 跳转到学生综合成绩排名
     */
    @RequestMapping("allScore")
    public String allScore() {
        return "growth/teacher/allScore";
    }
    /**
     * 跳转评分规则
     */
    @RequestMapping("rule")
    public String rule() {
        return "growth/teacher/rule";
    }
}
