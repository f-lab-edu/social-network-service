package com.zudbs.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.Duration;

/*
  Session Clustering
   서버가 한대일 경우 WAS에 세션 정보를 담아두고 사용하면 되지만 서버가 N대로 늘어날 경우 늘어난 서버의 ip, port 정보를 N대의 서버에 일
   일이 입력해줘야 한다. 따라서 WAS가 제공하는 세션 클러스터링 기능에 의존하지 않고 새로운 저장소를 두고 세션 정보를 두고 공유하게 되면
   서버가 늘어나도 저장소 정보만 공유해주면 된다.

  Redis
   데이터 저장소로 가장 I/O 속도가 빠른 장치인 메모리를 사용하여 휘발성이나 임시데이터 임시데이터를 저장하는데주로 사용됩니다.
   메모리를 사용하지만 특정 시점 또는 반복적으로 메모리에 있는 전체 데이터를 디스크에 주기적으로저장하여 백업이나 복구 가능합니다.
   구조적으로는 key, value(다양한 구조체 지원) 형식으로 간단한 자료구조 데이터를 유지하면서 빠르게 사용할 수 있습니다.
   redis의 빠른 I/O속도가 입출력이 빈번하고 임시데이터 특성을 가진 Session을 지원하기에 적합합니다.

  Redis Lib
   jedis
    - thread-safe하지 않기 때문에 스레드 안전 네트워크 연결 풀인 JedisPool을 사용해야한다.
      그러나 물리적인 비용의(connection할 인스턴스를 미리 만들어놓고 대기하는 연결비용) 증가가 따른다.

   Lettuce
    - Netty 기반의 Redis Client로 비동기로 요청을 처리하여 성능에 장점

   Netty
    - 유지 관리가 용이한 고성능 프로토콜 서버와 클라이언트를 신속하게 개발하기 위한
      비동기식 이벤트 기반 네트워크 애플리케이션 프레임워크이다. Netty는 자바 nio의
      selector 개념을 이용하여 적은 스레드로 많은 요청을 처리하고 ByteBuf는 자바의
      바이트 버퍼와 다르게 프레임워크 레벨의 바이트 버퍼풀을 제공하고 이를 통해 생성된
      바이트 버퍼를 재사용하여 GC부하를 최소화한다.

  @EnableRedisHttpSession
   Filter를 구현한 springSessionRepositoryFilter라는 이름의 Spring Bean을 생성합니다.
   이 필터는 HttpSession 구현체를 Spring Session 으로 교체하는 역할을 하고, 이 Spring Session은 Redis에 저장됩니다.

*/
@EnableRedisHttpSession
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    /*RedisConnectionFactory를 생성해서 Spring Session을 Redis서버와 연결시킨다.( RedisConnection 생성 )*/
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);

        return lettuceConnectionFactory;
    }

    /*
    Redis 데이터 액세스 코드를 단순화하는 도우미 클래스 (커넥션 위에서 값을 조작하는 메서드 제공)
    지정된 객체와 Redis 저장소의 기본 이진 데이터간에 자동 직렬화 / 역 직렬화를 수행합니다.
    기본적으로 객체를 통해 Java 직렬화를 사용합니다 (JdkSerializationRedisSerializer)
    Java 직렬화는 직렬화/역직렬화 과정에서 원한는 작업을 할 수 있도록 writeObject()/readObject() 메소드를
    지원하는데 이때 서버 측에 존재하는 Class들 둥에서 임의의 메소드(주로 "Runtieme.exec")를 실행할 수 있도록
    작성된 취약한 Class또는 라이브러리를 직렬과/역직렬화에 사용할 경우 readObject()메서드에 Runtime.exec 와 같은
    실행메서드를 정의하여 역직렬화 단계 동안 응용 프로그램에서 원치 않는 코드가 실행할 수 있게 합니다.
    결과적으로 신뢰할 수 없는 환경에서는 Java 직렬화를 지양합니다.
    SringTemplate : RedisTemplate 상속받은 클래스로서 문자열에 특화되어 있다.(StringRedisSerializer)
    */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        /*
        StringRedisSerializer는 키 값을 직렬화할 때 String.getBytes() 사용하여
        JDK 직렬화를 사용할 때처럼 불필요한 타입정보가 붙지 않는다.
        */

        return redisTemplate;
    }

    /*
    CacheManager            Spring에서 제공하는 캐시 서비스 추상화
    RedisCacheManager       Redis를 이용하여 CacheManager 구현한 클래스
    RedisCacheConfiguration RedisCacheManager 설정

      - serializeKeysWith        : Key 직렬화 관련 설정
        (StringRedisSerializer - Key 값은 String을 사용하기 때문)
      - serializeValuesWith      : Value 직렬화 관련 설정
        (GenericJackson2JsonRedisSerializer - Value 값은 다양한 자료구조 지원하기 위해)
      - disableCachingNullValues : Null은 캐싱하지 않음
      - entryTtl                 : 캐시의 기본 유효시간 설정
        (캐시Key 별 default 유효시간 설정 - RedisCacheManager withInitialCacheConfigurations)

    */
    @Bean
    public CacheManager redisCacheManager() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder.
                fromConnectionFactory(redisConnectionFactory()).cacheDefaults(redisCacheConfiguration).build();

        return redisCacheManager;
    }

}
