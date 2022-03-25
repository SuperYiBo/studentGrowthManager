package com.yeqifu.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeqifu.sys.entity.User;


public interface UserMapper extends BaseMapper<User> {

    Integer getIdByLoginName(String loginName);

}
