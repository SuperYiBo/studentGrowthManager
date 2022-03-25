package com.yeqifu.growth.service;

import com.yeqifu.growth.entity.Paper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-21
 */
public interface IPaperService extends IService<Paper> {
    Integer countPaper();
}
