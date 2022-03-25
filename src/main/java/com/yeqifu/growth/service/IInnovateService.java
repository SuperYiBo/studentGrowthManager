package com.yeqifu.growth.service;

import com.yeqifu.growth.entity.Innovate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-22
 */
public interface IInnovateService extends IService<Innovate> {
    Integer countInnovate();
}
