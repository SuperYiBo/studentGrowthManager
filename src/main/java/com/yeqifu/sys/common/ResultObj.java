package com.yeqifu.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {

    private Integer code;
    private String msg;

    public static final ResultObj LOGIN_SUCCESS=new ResultObj(Constast.OK,"登陆成功");
    public static final ResultObj LOGIN_ERROR_PASS=new ResultObj(Constast.ERROR,"用户名或密码错误");
    public static final ResultObj LOGIN_ERROR_CODE=new ResultObj(Constast.ERROR,"验证码错误");

    public static final ResultObj ADD_SUCCESS = new ResultObj(Constast.OK,"添加成功");
    public static final ResultObj ADD_ERROR = new ResultObj(Constast.ERROR,"添加失败");
    public static final ResultObj ADD_ERROR_LOGINNAME = new ResultObj(Constast.ERROR,"添加失败,可能存在重复登陆学号，请检查！");
    public static final ResultObj ADD_ERROR2 = new ResultObj(Constast.ERROR,"失败：供应数量不能大于库存数量");

    public static final ResultObj DELETE_SUCCESS = new ResultObj(Constast.OK,"删除成功");
    public static final ResultObj DELETE_ERROR = new ResultObj(Constast.ERROR,"删除失败");

    public static final ResultObj UPDATE_SUCCESS = new ResultObj(Constast.OK,"修改成功");
    public static final ResultObj UPDATE_ERROR = new ResultObj(Constast.ERROR,"修改失败");

    public static final ResultObj RESET_SUCCESS = new ResultObj(Constast.OK,"重置成功");
    public static final ResultObj RESET_ERROR = new ResultObj(Constast.ERROR,"重置失败");

    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(Constast.OK,"分配成功");
    public static final ResultObj DISPATCH_ERROR = new ResultObj(Constast.ERROR,"分配失败");

    public static final ResultObj BACKINPORT_SUCCESS = new ResultObj(Constast.OK,"退货成功");
    public static final ResultObj BACKINPORT_ERROR = new ResultObj(Constast.ERROR,"退货失败");
    public static final ResultObj SYNCCACHE_SUCCESS = new ResultObj(Constast.OK,"同步缓存成功");

    public static final ResultObj DELETE_ERROR_NEWS = new ResultObj(Constast.ERROR,"删除用户失败，该用户是其他用户的直属领导，请先修改该用户的下属的直属领导，再进行删除操作");
    public static final ResultObj DELETE_QUERY = new ResultObj();

    public static final ResultObj APPLY_SUCCESS = new ResultObj(Constast.OK,"回复成功");

    public static final ResultObj MODULE_SCORE_SUCCESS = new ResultObj(Constast.OK,"提交成功");
    public static final ResultObj MODULE_SCORE_ERROR = new ResultObj(Constast.OK,"提交失败");
    public static final ResultObj MODULE_SCORE_REPEAT_ERROR = new ResultObj(Constast.OK,"该学年已提交，可以修改记录！");

    public static final ResultObj VERIFY_SUCCESS = new ResultObj(Constast.OK,"审核成功");
    public static final ResultObj VERIFY_ERROR = new ResultObj(Constast.OK,"审核失败，该学生申请学年不存在！");


    public static final ResultObj SUBMIT_SUCCESS = new ResultObj(Constast.OK,"上传成功");
    public static final ResultObj SUBMIT_ERROR = new ResultObj(Constast.OK,"上传失败");

    public static final ResultObj YEAR_ERROR = new ResultObj(Constast.OK,"该学年通道已经开启，请勿重复操作！");
    public static final ResultObj YEAR_SUCCESS = new ResultObj(Constast.OK,"开启成功！");
    public static final ResultObj ERROR = new ResultObj(Constast.OK,"操作失败");

    public static final ResultObj IMPORT_SUCCESS = new ResultObj(Constast.OK,"上传用户表单成功");
    public static final ResultObj IMPORT_ERROR = new ResultObj(Constast.OK,"上传用户表单失败");
    public static final ResultObj ERROR_NULL = new ResultObj(Constast.OK,"自评分数不能为空！");








}
