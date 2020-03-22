package cn.featherfly.permission.web.login;

import cn.featherfly.permission.Actor;

/**
 * <p>
 * web环境简单实现，不支持集群
 * </p>
 *
 *
 * @author 钟冀
 */
public class WebActorLoginStorageImpl
        extends
        AbstractWebActorLoginStorage<WebLoginInfo<Actor>, Actor> {

    /**
	 */
    public WebActorLoginStorageImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected WebLoginInfo<Actor> createLoginInfo() {
        return new WebLoginInfo<Actor>();
    }
}
