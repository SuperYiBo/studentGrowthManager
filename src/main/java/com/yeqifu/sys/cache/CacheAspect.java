package com.yeqifu.sys.cache;

import com.yeqifu.sys.entity.Grade;
import com.yeqifu.sys.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Map;


@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {

    /**
     * 日志出处
     */
    private Log log = LogFactory.getLog(CacheAspect.class);

    /**
     * 声明一个缓存容器
     */
    private Map<String,Object> CACHE_CONTAINER = CachePool.CACHE_CONTAINER;

    /**
     * 声明年级的切面表达式
     */
    private static final String POINTCUT_GRADE_ADD="execution(* com.yeqifu.sys.service.impl.GradeServiceImpl.save(..))";
    private static final String POINTCUT_GRADE_UPDATE="execution(* com.yeqifu.sys.service.impl.GradeServiceImpl.updateById(..))";
    private static final String POINTCUT_GRADE_GET="execution(* com.yeqifu.sys.service.impl.GradeServiceImpl.getById(..))";
    private static final String POINTCUT_GRADE_DELETE="execution(* com.yeqifu.sys.service.impl.GradeServiceImpl.removeById(..))";

    private static final String CACHE_GRADE_PROFIX="Grade:";

    /**
     * 添加年级切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GRADE_ADD)
    public Object cacheGradeAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Grade object = (Grade) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_GRADE_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询年级切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GRADE_GET)
    public Object cacheGRADEGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_GRADE_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到年级对象"+CACHE_GRADE_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到年级对象，从数据库中查询并放入缓存");
            Grade res2 =(Grade) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_GRADE_PROFIX+res2.getId(),res2);
            return res2;
        }
    }

    /**
     * 更新年级切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GRADE_UPDATE)
    public Object cacheGradeUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Grade gradeVo = (Grade) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            Grade grade =(Grade) CACHE_CONTAINER.get(CACHE_GRADE_PROFIX + gradeVo.getId());
            if (null== grade){
                grade =new Grade();
            }
            BeanUtils.copyProperties(gradeVo, grade);
            log.info("年级对象缓存已更新"+CACHE_GRADE_PROFIX + gradeVo.getId());
            CACHE_CONTAINER.put(CACHE_GRADE_PROFIX+ grade.getId(), grade);
        }
        return isSuccess;
    }

    /**
     * 删除年级切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GRADE_DELETE)
    public Object cacheGradeDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_GRADE_PROFIX+id);
        }
        return isSuccess;
    }

    /**
     * 声明用户的切面表达式
     */
    private static final String POINTCUT_USER_UPDATE="execution(* com.yeqifu.sys.service.impl.UserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_ADD="execution(* com.yeqifu.sys.service.impl.UserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_GET="execution(* com.yeqifu.sys.service.impl.UserServiceImpl.getById(..))";
    private static final String POINTCUT_USER_DELETE="execution(* com.yeqifu.sys.service.impl.UserServiceImpl.removeById(..))";

    private static final String CACHE_USER_PROFIX="user:";

    /**
     * 添加用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_ADD)
    public Object cacheUserAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("添加用户=============================================cache");
        //取出第一个参数
        User object = (User) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_GET)
    public Object cacheUserGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_USER_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到用户对象"+CACHE_USER_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到用户对象，从数据库中查询并放入缓存");
            User res2 =(User) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_USER_PROFIX+res2.getId(),res2);
            return res2;
        }
    }

    /**
     * 更新用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_UPDATE)
    public Object cacheUserUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        User userVo = (User) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            User user =(User) CACHE_CONTAINER.get(CACHE_USER_PROFIX + userVo.getId());
            if (null==user){
                user=new User();
            }
            BeanUtils.copyProperties(userVo,user);
            log.info("用户对象缓存已更新"+CACHE_USER_PROFIX + userVo.getId());
            CACHE_CONTAINER.put(CACHE_USER_PROFIX+user.getId(),user);
        }
        return isSuccess;
    }

    /**
     * 删除用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_DELETE)
    public Object cacheUserDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_USER_PROFIX+id);
        }
        return isSuccess;
    }

}
