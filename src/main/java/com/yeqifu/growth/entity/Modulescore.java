package com.yeqifu.growth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxc-
 * @since 2022-02-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("growth_modulescore")
public class Modulescore implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 通识必修课成绩
     */
    private Double general;

    /**
     * 专业必修课成绩
     */
    private Double major;

    /**
     * 学科基础必修课
     */
    private Double ambit;

    /**
     * 公共基础课
     */
    private Double publicBase;

    /**
     * 专业基础课
     */
    private Double specialized;

    /**
     * 集中实践教学环节
     */
    private Double practice;

    /**
     * 专周
     */
    private Double spacialWeek;

    /**
     * 学生id
     */
    private Integer userid;

    /**
     * 模块平均成绩
     */
    private Double averagescore;

    /**
     * 提交时间
     */
    private Date createDate;

    /**
     * 学年
     */
    private String year;
    /**
     * 挂科数目
     */
    private String fail;

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



}
