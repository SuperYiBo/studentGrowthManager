package com.yeqifu.growth.service;

import com.yeqifu.growth.entity.Work;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxc-
 * @since 2022-03-01
 */
public interface IWorkService extends IService<Work> {
    Integer countWork();
}
