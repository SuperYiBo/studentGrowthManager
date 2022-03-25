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
@TableName("growth_work")
public class Work implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 校院干部
     */
    private String worktype;

    /**
     * 班级干部
     */
    private String ctype;

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

    private Integer userid;

    /**
     * 修改时间
     */
    private Date workdate;

    /**
     * 驳回理由
     */
    private String why;

    /**
     * 是否通过
     */
    private String ypass;

    /**
     * 文件路径
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
