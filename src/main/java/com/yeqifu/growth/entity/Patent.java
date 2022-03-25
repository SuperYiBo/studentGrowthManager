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
@TableName("growth_patent")
public class Patent implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专利名称
     */
    private String patentname;

    /**
     * 专利类型
     */
    private String patenttype;

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
     * 作者排名
     */
    private String authortype;

    private Integer userid;

    /**
     * 修改日期
     */
    private Date patentdate;

    /**
     * 理由
     */
    private String why;

    /**
     * 是否通过
     */
    private String ypass;

    /**
     * word路径
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
