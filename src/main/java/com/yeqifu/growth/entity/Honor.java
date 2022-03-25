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
 * @since 2022-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("growth_honor")
public class Honor implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 荣誉项目名
     */
    private String honorname;

    /**
     * 荣誉级别
     */
    private String honortype;

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
     * 图片地址
     */
    private String imgpath;

    /**
     * 团队形式
     */
    private String authortype;

    private Integer userid;

    /**
     * 修改时间
     */
    private Date honordate;

    /**
     * 驳回原因
     */
    private String why;

    /**
     * 是否通过
     */
    private String ypass;

    /**
     * 文档路径
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
