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
 * @since 2022-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("growth_paper")
public class Paper implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 论文名称
     */
    private String papername;

    /**
     * 论文数目
     */
    private String number;

    /**
     * 学年
     */
    private String year;

    /**
     * 备注
     */
    private String text;

    /**
     * 自评
     */
    private Double doscore;

    /**
     * 材料地址
     */
    private String imgpath;

    /**
     * 材料文档路径
     */
    private String wordpath;

    /**
     * 作者类型
     */
    private String authortype;

    /**
     * 论文类型
     */
    private String papertype;

    /**
     * 学生id
     */
    private Integer userid;

    /**
     * 修改时间
     */
    private Date paperdate;

    /**
     * 是否通过
     */
    private String ypass;

    /**
     * 驳回理由
     */
    private String why;

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
