package com.jt.plt.product.util;

import com.jt.plt.product.entity.CallRsult;
import com.jt.plt.product.service.RedisCallBackInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 描述：缓存工具类
 * 类名称：MyRedisUtils.java
 * 作者： wangyang
 * 版本：1.0
 * 修改： 2018/5/14 13:52
 * 创建日期： 2018/5/14 13:52
 * 版权：江泰保险经纪股份有限公司
 */
@Component
@Getter
@Setter
@Slf4j
public class MyRedisUtils {

    private static final String msg = "缓存读取出错，从数据库中直接读取数据";

    private static final int TIMEOUT = 24;

    @Autowired
    private RedisTemplate redisTemplate;

    private static MyRedisUtils myRedisUtils;

    @PostConstruct
    public void init() {
        myRedisUtils = this;
    }

    public static <K, T> T excute(K dbKey, String key, RedisCallBackInterface callBackInterface) {
        CallRsult<T> result = new CallRsult();
        ValueOperations<String, T> map = null;
        //缓存读取出错，直接从数据库中读取，并返回
        try {
            map = myRedisUtils.redisTemplate.opsForValue();
        } catch (Exception e) {
            log.error(msg, e);
            result = callBackInterface.callback(dbKey);
            if (result.isAllowCache()) {
                map.set(key, result.getResult(), TIMEOUT, TimeUnit.HOURS);
            }
            return result.getResult();
        }

        //判断缓存中是否有数据，有则从
        if (!myRedisUtils.redisTemplate.hasKey(key)) {
            result = callBackInterface.callback(dbKey);
        } else {
            try {
                log.error("key: {} 从缓存中直接返回查询结果", key);
                return map.get(key);
            } catch (Exception e) {
                log.error(msg, e);
                result = callBackInterface.callback(dbKey);
            }
        }
        //查询后的缓存保存
        try {
            if (result.isAllowCache()) {
                log.error("key: {} 将数据库中查询结果缓存", key);
                map.set(key, result.getResult(), TIMEOUT, TimeUnit.HOURS);
            }
        } catch (Exception e) {
            log.error("key: {} 缓存保存出错", key, e);
        }
        return result.getResult();
    }

    public static void main(String[] args) {
//        HashMap<String, String> map = new HashMap();
////        map.put("key1", "1111");
//        map.put("key2", "2222");
//        map.put("key3", "3333");
//
//        HashMap<String, String> mainMap= new HashMap();
//        mainMap.put("key1","key1");
//        mainMap.put("key2","key2");
//        mainMap.put("key3","key3");
//        mainMap.put("key4","key4");
//        mainMap.put("key5","key5");
//
//        RedisCallBackInterface s = (String key) -> {
//            System.out.println("从数据中查询数据");
//            CallRsult callRsult = new CallRsult<String>();
//            if(mainMap.get(key)!=null){
//                System.out.println("从数据中查询数据 ： " + mainMap.get(key) + "不为空，缓存数据");
//                callRsult.setResultAndAllowCached(mainMap.get(key));
//            }
//            return callRsult;
//        };
//        String reult = MyRedisUtils.excute("key1", map, s);
//        System.out.println("查询出的数据为：" + reult);
////        GreetingService greetService1 = message -> System.out.println("Hello " + message);
    }
}
