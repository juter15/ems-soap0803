package com.example.emssoap0803.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.List;

@Configuration
public class RedisClusterConfiguration
//        extends CachingConfigurerSupport
{
    @Value("${spring.redis.cluster.nodes}")
    private List<String> clusterNodes;

//    @Bean
//    @Override
//    public CacheManager cacheManager() {
//        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory());
//        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
//                .prefixKeysWith("imhere:")
//                .entryTtl(Duration.ofHours(5L));
//
//        builder.cacheDefaults(configuration);
//        return builder.build();
//    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
                // Noti Data 조회하기 위해 -> 기본: 1분

                .commandTimeout(Duration.ZERO)
                .shutdownTimeout(Duration.ZERO)
                .build();
        //log.info("clusterNodes: {}", clusterNodes);
        org.springframework.data.redis.connection.RedisClusterConfiguration redisClusterConfiguration = new org.springframework.data.redis.connection.RedisClusterConfiguration(clusterNodes);

        return new LettuceConnectionFactory(redisClusterConfiguration,lettuceClientConfiguration);

    }
//
    @Bean
    public RedisTemplate<String, String> redisTemplate(){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
