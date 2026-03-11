package cn.featherfly.authorities.web.login;

import javax.cache.Cache;

import cn.featherfly.authorities.Actor;

/**
 * cachable session WebActorLoginStorage.
 *
 * @author 钟冀
 */
public class RedissonWebActorLoginStorageImpl
    extends AbstractCacheableWebActorLoginStorage<WebLoginInfo<Actor>, Actor> {

    /**
     * Instantiates a new redis web actor login storage impl.
     *
     * @param cache the cache
     * @param keyCache the key cache
     */
    public RedissonWebActorLoginStorageImpl(Cache<String, WebLoginInfo<Actor>> cache, Cache<String, String> keyCache) {
        super(cache, keyCache);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected WebLoginInfo<Actor> createLoginInfo() {
        return new WebLoginInfo<>();
    }
}
