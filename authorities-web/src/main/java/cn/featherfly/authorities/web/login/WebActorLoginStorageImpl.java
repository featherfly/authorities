package cn.featherfly.authorities.web.login;

import cn.featherfly.authorities.Actor;

/**
 * web环境简单实现，不支持集群.
 *
 * @author 钟冀
 * @deprecated use {@link CacheableWebActorLoginStorageImpl} instead
 */
@Deprecated
public class WebActorLoginStorageImpl extends AbstractWebActorLoginStorage<WebLoginInfo<Actor>, Actor> {

    /**
     */
    public WebActorLoginStorageImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected WebLoginInfo<Actor> createLoginInfo() {
        return new WebLoginInfo<>();
    }
}
