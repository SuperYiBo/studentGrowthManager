package com.yeqifu.sys.cache;


import com.yeqifu.sys.common.SpringUtil;
import com.yeqifu.sys.entity.Grade;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.mapper.GradeMapper;
import com.yeqifu.sys.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachePool {

    /**
     * 所有的缓存数据放到这个CACHE_CONTAINER  类似于redis
     */
    public static volatile Map<String,Object> CACHE_CONTAINER = new HashMap<>();

    /**
     * 根据KEY删除缓存
     * @param key   键
     */
    public static void removeCacheByKey(String key){
        if (CACHE_CONTAINER.containsKey(key)){
            CACHE_CONTAINER.remove(key);
        }
    }

    /**
     * 清空所有缓存
     */
    public static void removeAll(){
        CACHE_CONTAINER.clear();
    }

    /**
     * 同步缓存
     */
    public static void syncData(){
        //同步年级数据
        GradeMapper gradeMapper = SpringUtil.getBean(GradeMapper.class);
        List<Grade> gradeList = gradeMapper.selectList(null);
        for (Grade grade : gradeList) {
            CACHE_CONTAINER.put("grade:"+ grade.getId(), grade);
        }
        //同步用户数据
        UserMapper userMapper = SpringUtil.getBean(UserMapper.class);
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            CACHE_CONTAINER.put("user:"+user.getId(),user);
        }

    }


}
