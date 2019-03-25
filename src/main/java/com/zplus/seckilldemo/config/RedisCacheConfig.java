package com.zplus.seckilldemo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.TimeZone;

@Configuration
@EnableCaching
// @EnableTransactionManagement
public class RedisCacheConfig extends CachingConfigurerSupport
{

    //自定义缓存key的生成策略
    @Bean
    @Override
    public KeyGenerator keyGenerator()
    {
        return (target, method, params) ->
        {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params)
            {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    //缓存配置管理器
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory)
    {
        //以锁写入的方式创建RedisCacheWriter对象
        RedisCacheWriter writer = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        /*
        设置CacheManager的Value序列化方式为JdkSerializationRedisSerializer,
        但其实RedisCacheConfiguration默认就是使用
        StringRedisSerializer序列化key，
        JdkSerializationRedisSerializer序列化value,
        所以以下注释代码就是默认实现，没必要写，直接注释掉
         */
        // RedisSerializationContext.SerializationPair pair = RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer(this.getClass().getClassLoader()));
        // RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        //创建默认缓存配置对象
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10L))
                .disableCachingNullValues();
        return RedisCacheManager.builder(writer).cacheDefaults(config).build();
    }

    // 获取缓存操作助手对象
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        //以下代码为将RedisTemplate的Value序列化方式由JdkSerializationRedisSerializer更换为Jackson2JsonRedisSerializer
        //此种序列化方式结果清晰、容易阅读、存储字节少、速度快，所以推荐更换
        // Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //序列化joda time
        om.registerModule(new JodaModule());
        om.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        GenericJackson2JsonRedisSerializer serializer=new GenericJackson2JsonRedisSerializer(om);
        // jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(connectionFactory);
        //key序列化
        redisTemplate.setKeySerializer(stringSerializer);
        //value序列化
        redisTemplate.setValueSerializer(serializer);
        //Hash key序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        //Hash value序列化
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /*//redis连接工厂
    @Bean
    public LettuceConnectionFactory redisConnectionFactory()
    {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("47.106.240.57", 6379));
    }*/


    /*//redis响应式连接工厂
    @Bean
    public ReactiveRedisConnectionFactory connectionFactory()
    {
        return new LettuceConnectionFactory("localhost", 6379);
    }

    //高可用redis连接工厂（容灾）
    @Bean
    public RedisConnectionFactory lettuceConnectionFactory()
    {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new LettuceConnectionFactory(sentinelConfig);
    }*/
}
