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
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("growth_innovate")
public class Innovate implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创新创业训练计划项目或课题研究名字
     */
    private String innovatename;

    /**
     * 自评分数
     */
    private Double doscore;

    /**
     * 学年
     */
    private String year;

    /**
     * 备注
     */
    private String text;

    /**
     * 材料图片地址
     */
    private String imgpath;
    /**
     * 材料文档路径
     */
    private String wordpath;

    /**
     * 参与度
     */
    private String authortype;

    /**
     * 课题类型（国家级）
     */
    private String innovatetype;

    /**
     * 课题进度，立项目，结算项目
     */
    private String progress;

    private Integer userid;

    /**
     * 更新时间
     */
    private Date innovatedate;

    /**
     * 驳回理由
     */
    private String why;

    /**
     * 是否通过
     */
    private String ypass;
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
