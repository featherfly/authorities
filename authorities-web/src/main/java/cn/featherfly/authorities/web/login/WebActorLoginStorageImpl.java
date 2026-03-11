package cn.featherfly.authorities.web.login;

import cn.featherfly.authorities.Actor;

/**
 * web环境简单实现，不支持集群.
 *
 * @author zhongj
 * @deprecated use {@link CacheableWebActorLoginStorageImpl} instead
 */
@Deprecated
public class WebActorLoginStorageImpl extends AbstractWebActorLoginStorage<WebLoginInfo<Actor>, Actor> {

    /**
     * Instantiates a new web actor login storage impl.
     */
    @Deprecated
    public WebActorLoginStorageImpl() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    protected WebLoginInfo<Actor> createLoginInfo() {
        return new WebLoginInfo<>();
    }
}
