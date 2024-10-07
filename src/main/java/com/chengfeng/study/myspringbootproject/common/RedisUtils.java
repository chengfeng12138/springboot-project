package com.chengfeng.study.myspringbootproject.common;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * RedisUtils class
 *
 * @author chengfeng
 * @date 2021/8/21 /0021 1:03
 */
@Component
public class RedisUtils {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
    * 指定缓存失效时间
    * @param key 键
    * @param time 时间(秒)
    * @author chengfeng
    * @date 2021/8/21 /0021 1:15
    **/
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
    * 获取缓存过期时间
    * @param key 键
    * @author chengfeng
    * @date 2021/8/21 /0021 1:18
    **/
    public Long getExpire(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return (long) -1;
        }
    }

    /**
     * 判断 key 是否存在
     * @param key 键
     * @author chengfeng
     * @date 2021/8/21 /0021 1:18
     **/
    public Boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
    * 删除缓存
    * @param keys 键值, 可传单个或多个
    * @author chengfeng
    * @date 2021/8/21 /0021 1:27
    **/
    public void del(String... keys) {
        try {
            if (keys != null && keys.length > 0) {
                if (keys.length == 1) {
                    redisTemplate.delete(keys[0]);
                } else {
                    redisTemplate.delete(CollectionUtils.arrayToList(keys));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * 普通缓存放入
    * @param key 键
    * @param value 值
    * @param time 过期时间
    * @param seconds 过期时间单位
    * @author chengfeng
    * @date 2021/8/21 /0021 1:30
    **/
    public boolean set(String key, Object value, long time, TimeUnit seconds) {
        try {
            redisTemplate.opsForValue().set(key, value, time, seconds);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
    * 普通缓存获取
    * @param key 键
    * @return 缓存值
    * @author chengfeng
    * @date 2021/8/21 /0021 1:42
    */
    public Object get(String key) {
        return StrUtil.isEmpty(key) ? null : redisTemplate.opsForValue().get(key);
    }

    /**
    * 递增
    * @param key 键
    * @param delta 递增因子(要增加几, 必须大于0)
    * @author chengfeng
    * @date 2021/8/21 /0021 1:47
    */
    public Long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("redis 递增因子必须大于0!!!!");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 递减因子(要减少几, 必须大于0)
     * @author chengfeng
     * @date 2021/8/21 /0021 1:47
     */
    public Long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("redis 递减因子必须大于0!!!!");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

}
