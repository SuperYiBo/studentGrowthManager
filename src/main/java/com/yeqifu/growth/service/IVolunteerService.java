package com.yeqifu.growth.service;

import com.yeqifu.growth.entity.Volunteer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-28
 */
public interface IVolunteerService extends IService<Volunteer> {
    Integer countVolunteer();
}
