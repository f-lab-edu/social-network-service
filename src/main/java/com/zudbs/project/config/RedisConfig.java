package com.zudbs.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


/*
  Session Clustering
   서버가 한대일 경우 WAS에 세션 정보를 담아두고 사용하면 되지만 서버가 N대로 늘어날 경우 늘어난 서버의 ip, port 정보를 N대의 서버에 일
   일이 입력해줘야 한다. 따라서 WAS가 제공하는 세션 클러스터링 기능에 의존하지 않고 새로운 저장소를 두고 세션 정보를 두고 공유하게 되면
   서버가 늘어나도 저장소 정보만 공유해주면 된다.

  Redis
   Redis는 데이터 저장소로 가장 I/O 속도가 빠른 장치인 메모리를 사용하고 있고 구조적으로는 key, value 형식의 데이터 처리에 특화되어
   있고 value로 다양한 구조체를 지원한다.

  Redis Lib
   jedis (thread-safe하지 않기 때문에 jedis-pool을 사용해야한다. 그러나 비용이 증가)
   Lettuce (Netty 기반의 Redis Client로 비동기로 요청을 처리하여 성능에 장점)
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    /* RedisConnection 생성 */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);

        return lettuceConnectionFactory;
    }

    /*
    Redis 데이터 액세스 코드를 단순화하는 도우미 클래스 (커넥션 위에서 값을 조작하는 메서드 제공)
    지정된 객체와 Redis 저장소의 기본 이진 데이터간에 자동 직렬화 / 역 직렬화를 수행합니다.
    기본적으로 객체를 통해 Java 직렬화를 사용합니다 (JdkSerializationRedisSerializer)
    SringTemplate : RedisTemplate 상속받은 클래스로서 문자열에 특화되어 있다.(StringRedisSerializer)
    */
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {

        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }

}
