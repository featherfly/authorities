
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-03-11 16:18:11
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.authorities.web.login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.concurrent.TimeUnit;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.SimpleActor;
import cn.featherfly.authorities.login.LoginInfo;

/**
 * RedissonWebActorLoginStorageTest.
 *
 * @author zhongj
 */
public class RedissonWebActorLoginStorageTest {

    RedissonWebActorLoginStorageImpl storage;

    @BeforeClass
    void setup() {
        Config redissonCfg = new Config();

        JsonJacksonCodec codec = new JsonJacksonCodec();
        codec.getObjectMapper().registerModule(new JavaTimeModule())
        // .disable(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_TIMES)
        // .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // Disable serialization of dates as timestamps; use ISO 8601 strings instead
        ;

        redissonCfg
            .setPassword("123456")
            .setCodec(codec)
            .useSingleServer()
            .setAddress("redis://redishost:6379");

        MutableConfiguration<String, WebLoginInfo<Actor>> configuration =
            new MutableConfiguration<String, WebLoginInfo<Actor>>()
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 3)));

        MutableConfiguration<String, String> configuration2 = new MutableConfiguration<String, String>()
            .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 3)));

        Configuration<String, WebLoginInfo<Actor>> config =
            RedissonConfiguration.fromConfig(redissonCfg, configuration);
        Configuration<String, String> config2 = RedissonConfiguration.fromConfig(redissonCfg, configuration2);

        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        Cache<String, WebLoginInfo<Actor>> actorCache = manager.createCache("actorCache", config);
        Cache<String, String> keyCache = manager.createCache("keyCache", config2);

        storage = new RedissonWebActorLoginStorageImpl(actorCache, keyCache);
    }

    private void assertActor(String key, Actor actor) {
        LoginInfo<Actor> loginInfo = storage.getLoginInfo(key);
        assertEquals(loginInfo.getActor().getId(), actor.getId());
        assertEquals(loginInfo.getActor().getName(), actor.getName());
    }

    @Test
    public void test() throws InterruptedException {
        String key = "key_1";
        SimpleActor actor = new SimpleActor();
        actor.setId("1");
        actor.setName("actor_1");
        storage.store(key, actor);

        assertActor(key, actor);

        key = "key_2";
        actor.setId("2");
        actor.setName("actor_2");
        storage.store(key, actor);

        assertActor(key, actor);

        Thread.sleep(4 * 1000);

        assertNull(storage.getLoginInfo(key));
    }
}
