package cn.featherfly.authorities.web.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache;
import javax.cache.Cache.Entry;

import cn.featherfly.authorities.Actor;

/**
 * abstract cachable WebActorLoginStorage.
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author 钟冀
 */
public abstract class AbstractCacheableWebActorLoginStorage<W extends WebLoginInfo<A>, A extends Actor>
        implements WebActorLoginStorage<W, A> {

    private Cache<String, W> cache;

    /**
     * Instantiates a new abstract cacheable web actor login storage.
     *
     * @param cache the cache
     */
    protected AbstractCacheableWebActorLoginStorage(Cache<String, W> cache) {
        this.cache = cache;
    }

    /**
     * <p>
     * 创建LoginInfo
     * </p>
     *
     * @return 登陆信息
     */
    protected abstract W createLoginInfo();

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(String key, A actor) {
        W webLoginInfo = createLoginInfo();
        webLoginInfo.setActor(actor);
        webLoginInfo.setLoginTime(new Date());
        webLoginInfo.setSession(key);
        cache.put(key, webLoginInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(A actor) {
        if (actor != null) {
            remove(getLoginInfo(actor).getSession());
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
        if (actor != null) {
            Iterator<Entry<String, W>> iter = cache.iterator();
            while (iter.hasNext()) {
                Entry<String, W> e = iter.next();
                // TODO 后续是否直接使用actor.equals(actor)
                W w = e.getValue();
                if (w.getActor().getId().equals(actor.getId())) {
                    return w;
                }
            }
        }
        return null;
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
