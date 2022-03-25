package com.yeqifu.growth.service.impl;

import com.yeqifu.growth.entity.Volunteer;
import com.yeqifu.growth.mapper.VolunteerMapper;
import com.yeqifu.growth.service.IVolunteerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-28
 */
@Service
public class VolunteerServiceImpl extends ServiceImpl<VolunteerMapper, Volunteer> implements IVolunteerService {

    @Autowired
    private VolunteerMapper volunteerMapper;
    @Override
    public Integer countVolunteer() {
        return volunteerMapper.countVolunteer();
    }
}
