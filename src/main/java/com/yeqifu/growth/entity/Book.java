package com.yeqifu.growth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("growth_book")
public class Book implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    /**
     * 书籍名字
     */
    private String bookname;

    /**
     * 出版方式：团队 个人
     */
    private String dotype;

    /**
     * 自评分数
     */
    private Double doscore;

    /**
     * 出版书数目
     */
    private String number;

    /**
     * 学年
     */
    private String year;

    /**
     * 备注信息
     */
    private String text;

    /**
     * 材料图片路径
     */
    private String imgpath;
    /**
     * 材料文档路径
     */
    private String wordpath;


    /**
     * 主编，副编，参编
     */
    private String authortype;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 时间
     */
    private Date bookdate;


    /**
     * 是否通过
     */
    private String ypass;

    /**
     * 驳回原因
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
