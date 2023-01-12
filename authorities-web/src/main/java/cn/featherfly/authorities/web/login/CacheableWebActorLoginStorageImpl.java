package cn.featherfly.authorities.web.login;

import javax.cache.Cache;

import cn.featherfly.authorities.Actor;

/**
 * cachable WebActorLoginStorage.
 *
 * @author 钟冀
 */
public class CacheableWebActorLoginStorageImpl
        extends AbstractCacheableWebActorLoginStorage<WebLoginInfo<Actor>, Actor> {

    /**
     * @param cache
     */
    public CacheableWebActorLoginStorageImpl(Cache<String, WebLoginInfo<Actor>> cache) {
        super(cache);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected WebLoginInfo<Actor> createLoginInfo() {
        return new WebLoginInfo<>();
    }
}
