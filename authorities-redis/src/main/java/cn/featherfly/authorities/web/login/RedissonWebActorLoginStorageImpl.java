package cn.featherfly.authorities.web.login;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;

import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;

import cn.featherfly.authorities.Actor;
import cn.featherfly.common.lang.Strings;

/**
 * cachable session WebActorLoginStorage.
 *
 * @author 钟冀
 */
public class RedissonWebActorLoginStorageImpl
        extends AbstractCacheableWebActorLoginStorage<WebLoginInfo<Actor>, Actor> {

    /** The Constant KEY_PATTERN. */
    //    public static final String KEY_PATTERN = "";

    private String key;

    private RedissonClient redissonClient;

    /**
     * Instantiates a new redis web actor login storage impl.
     *
     * @param cache      the cache
     * @param appName    the app name
     * @param moduleName the module name
     * @param propName   the prop name
     */
    public RedissonWebActorLoginStorageImpl(RedissonClient redissonClient, String appName, String moduleName,
            String propName) {
        MutableConfiguration<String, String> jcacheConfig = new MutableConfiguration<>();

        Config redissonCfg = null;
        Configuration<String, String> config = RedissonConfiguration.fromConfig(redissonCfg, jcacheConfig);

        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        Cache<String, String> cache = manager.createCache("namedCache", config);

        super(cache);
        key = Strings.format("{0}:{1}:{2}", appName, moduleName, propName);
    }

    /**
     * Instantiates a new redis web actor login storage impl.
     *
     * @param cache the cache
     */
    public RedissonWebActorLoginStorageImpl(Cache<String, WebLoginInfo<Actor>> cache) {
        super(cache);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected WebLoginInfo<Actor> createLoginInfo() {
        return new WebLoginInfo<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Actor actor) {
        if (actor != null) {
            remove(getLoginInfo(actor).getSession());
        }
    }

    /**
     * get key value
     *
     * @return key
     */
    public String getKey() {
        return key;
    }
}
