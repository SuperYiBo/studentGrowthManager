package com.yeqifu.growth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxc-
 * @since 2022-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("growth_allscore")
public class Allscore implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    private Integer userid;

    /**
     * 学年
     */
    private String year;

    /**
     * 学年平均成绩
     */
    private Double module;

    /**
     * 书籍审核成绩
     */
    private Double kbook;

    /**
     * 发表论文审核成绩
     */
    private Double kpaper;

    /**
     * 创新课题研究成绩
     */
    private Double kinnovate;

    /**
     * 竞赛审核成绩
     */
    private Double kcompetition;

    /**
     * 专利审核成绩
     */
    private Double kpatent;

    /**
     * 证书审核成绩
     */
    private Double kcertificate;

    /**
     * 志愿服务审核成绩
     */
    private Double svolunteer;

    /**
     * 社会工作审核成绩
     */
    private Double swork;

    /**
     * 荣誉申报审核成绩
     */
    private Double rhonor;

    /**
     * 科技综合
     */
    private Double k;

    /**
     * 社会实践综合
     */
    private Double s;

    /**
     * 综合总成绩
     */
    private Double allscore;

    /**
     * 学生姓名
     */
    @TableField(exist = false)
    private String studentname;

    /**
     * 所属年级
     */
    @TableField(exist = false)
    private String grades;

    /**
     * 学号
     */
    @TableField(exist = false)
    private String snumber;
    /**
     * 挂科数
     */
    private String fail;
}
