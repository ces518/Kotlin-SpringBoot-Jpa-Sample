package me.springboot.jpa.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
@EnableCaching(proxyTargetClass = true)
class RedisCacheConfig(
        private val redisConnectionFactory: RedisConnectionFactory,
        private val objectMapper: ObjectMapper
) {

//    @Bean
//    override fun cacheManager(): CacheManager? =
//        RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory).apply {
//            objectMapper.registerModule(KotlinModule())
//            objectMapper.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.NON_FINAL, "@class")
//
//            val config = RedisCacheConfiguration.defaultCacheConfig()
//                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer(objectMapper)))
//                    .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
//                    .entryTtl(Duration.ofMinutes(1L))
//            cacheDefaults(config)
//        }.build()
//
//    @Bean
//    override fun cacheManager(): CacheManager = RedisCacheManager(redisTemplate)
}