package com.chengfeng.study.myspringbootproject.config.redisconfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisConfig class
 *
 * @author chengfeng
 * @date 2021/8/15 /0015 21:42
 */
@Configuration
public class RedisConfig {

    /**
    * 重写 RedisAutoConfiguration 类中的方法, 自定义redisTemplate, 增加序列化
    *  这里的序列化配置可直接拿到公司使用
    * @author chengfeng
    * @date 2021/8/15 /0015 21:53
    **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> objectObjectRedisTemplate = new RedisTemplate<>();
        objectObjectRedisTemplate.setConnectionFactory(factory);
        //序列化配置
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //String序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //设置key的序列化器
        objectObjectRedisTemplate.setKeySerializer(stringRedisSerializer);
        //设置hash key的序列化
        objectObjectRedisTemplate.setHashKeySerializer(stringRedisSerializer);
        //设置value的序列化器
        objectObjectRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //设置hash value的序列化
        objectObjectRedisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        objectObjectRedisTemplate.afterPropertiesSet();
        return objectObjectRedisTemplate;
    }
}
