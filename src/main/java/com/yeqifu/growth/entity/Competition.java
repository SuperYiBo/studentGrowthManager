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
 * @since 2022-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("growth_competition")
public class Competition implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 竞赛项目名
     */
    private String competitionname;

    /**
     * 竞赛类型（科技、非科技）
     */
    private String ctype;

    /**
     * 竞等级（国家级、省级）
     */
    private String competitiontype;

    /**
     * 学年
     */
    private String year;

    /**
     * 自评分数
     */
    private Double doscore;

    /**
     * 备注
     */
    private String text;

    /**
     * 图片路径
     */
    private String imgpath;

    /**
     * 团队个人排名
     */
    private String authortype;

    private Integer userid;

    /**
     * 修改时间
     */
    private Date competitiondate;

    /**
     * 驳回理由
     */
    private String why;

    /**
     * 是否通过
     */
    private String ypass;

    /**
     * word文档路径
     */
    private String wordpath;

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
