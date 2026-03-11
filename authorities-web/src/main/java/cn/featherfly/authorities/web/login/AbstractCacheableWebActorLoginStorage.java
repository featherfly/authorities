package cn.featherfly.authorities.web.login;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache;
import javax.cache.Cache.Entry;

import cn.featherfly.authorities.Actor;

/**
 * abstract cachable WebActorLoginStorage.
 *
 * @author zhongj
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 */
public abstract class AbstractCacheableWebActorLoginStorage<W extends WebLoginInfo<A>, A extends Actor>
    implements WebActorLoginStorage<W, A> {

    private Cache<String, W> cache;

    private Cache<String, String> keyCache;

    /**
     * Instantiates a new abstract cacheable web actor login storage.
     *
     * @param cache the cache
     * @param keyCache the key cache
     */
    protected AbstractCacheableWebActorLoginStorage(Cache<String, W> cache, Cache<String, String> keyCache) {
        this.cache = cache;
        this.keyCache = keyCache;
    }

    /**
     * 创建LoginInfo.
     *
     * @return 登陆信息
     */
    protected abstract W createLoginInfo();

    /**
     * {@inheritDoc}
     */
    @Override
    public W store(String key, A actor) {
        W webLoginInfo = createLoginInfo();
        webLoginInfo.setActor(actor);
        webLoginInfo.setLoginTime(LocalDateTime.now());
        webLoginInfo.setSession(key);
        cache.put(key, webLoginInfo);
        keyCache.put(actor.getId(), key);
        return webLoginInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(String key) {
        if (key == null) {
            return;
        }
        W webLoginInfo = cache.get(key);
        cache.remove(key);
        if (webLoginInfo != null) {
            keyCache.remove(webLoginInfo.getActor().getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(A actor) {
        if (actor == null) {
            return;
        }
        String finalKey = keyCache.get(actor.getId());
        keyCache.remove(actor.getId());
        if (finalKey != null) {
            remove(finalKey);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public W getLoginInfo(String key) {
        return cache.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public W getLoginInfo(A actor) {
        if (actor == null) {
            return null;
        }
        String finalKey = keyCache.get(actor.getId());
        if (finalKey == null) {
            return null;
        }
        return cache.get(finalKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<A> getLoginActors() {
        List<A> actors = new ArrayList<>();
        Iterator<Entry<String, W>> iter = cache.iterator();
        while (iter.hasNext()) {
            actors.add(iter.next().getValue().getActor());
        }
        return actors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<W> getLoginInfos() {
        List<W> infos = new ArrayList<>();
        Iterator<Entry<String, W>> iter = cache.iterator();
        while (iter.hasNext()) {
            infos.add(iter.next().getValue());
        }
        return infos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}
