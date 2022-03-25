package com.yeqifu.sys.vo;

import com.yeqifu.sys.entity.Grade;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class GradeVo extends Grade {

    private Integer page=1;
    private Integer limit=10;

}
