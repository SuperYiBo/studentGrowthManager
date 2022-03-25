package com.yeqifu.growth.vo;

import com.yeqifu.growth.entity.Paper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaperVo extends Paper {
    /**
     * 分页参数，当前是第一页，每页10条数据
     */
    private Integer page=1;
    private Integer limit=10;

    /**
     * 批量删除
     */
    private Integer[] ids;
}
