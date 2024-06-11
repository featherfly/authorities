package cn.featherfly.authorities.web.login;

import javax.cache.Cache;

import cn.featherfly.authorities.Actor;

/**
 * cachable WebActorLoginStorage.
 *
 * @author zhongj
 */
public class CacheableWebActorLoginStorageImpl
    extends AbstractCacheableWebActorLoginStorage<WebLoginInfo<Actor>, Actor> {

    /**
     * Instantiates a new cacheable web actor login storage impl.
     *
     * @param cache cache
     * @param keyCache the key cache
     */
    public CacheableWebActorLoginStorageImpl(Cache<String, WebLoginInfo<Actor>> cache, Cache<String, String> keyCache) {
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
