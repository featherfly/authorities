package cn.featherfly.authorities.web.login;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.authorities.Actor;

/**
 * web环境简单实现抽象，不支持集群.
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author zhongj
 * @deprecated use {@link AbstractCacheableWebActorLoginStorage} instead
 */
@Deprecated
public abstract class AbstractWebActorLoginStorage<W extends WebLoginInfo<A>, A extends Actor>
    implements WebActorLoginStorage<W, A> {

    private Map<String, W> webLoginInfos = new HashMap<>();

    /**
     */
    @Deprecated
    public AbstractWebActorLoginStorage() {
    }

    /**
     * <p>
     * 创建LoginInfo
     * </p>
     *
     * @return 登陆信息
     */
    @Deprecated
    protected abstract W createLoginInfo();

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public W store(String key, A actor) {
        W webLoginInfo = createLoginInfo();
        webLoginInfo.setActor(actor);
        webLoginInfo.setLoginTime(LocalDateTime.now());
        webLoginInfo.setSession(key);
        webLoginInfos.put(key, webLoginInfo);
        return webLoginInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public void remove(A actor) {
        if (actor != null) {
            webLoginInfos.remove(getLoginInfo(actor).getSession());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public void remove(String key) {
        webLoginInfos.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public W getLoginInfo(String key) {
        return webLoginInfos.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public W getLoginInfo(A actor) {
        if (actor != null) {
            for (W webLoginInfo : webLoginInfos.values()) {
                if (webLoginInfo.getActor().getId().equals(actor.getId())) {
                    return webLoginInfo;
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public List<A> getLoginActors() {
        ArrayList<A> actors = new ArrayList<>();
        for (W loginActor : webLoginInfos.values()) {
            A a = loginActor.getActor();
            actors.add(a);
        }
        return actors;
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public List<W> getLoginInfos() {
        return new ArrayList<>(webLoginInfos.values());
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public boolean containsKey(String key) {
        return webLoginInfos.containsKey(key);
    }

}
