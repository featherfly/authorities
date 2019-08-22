package cn.featherfly.permission.web.login;

import cn.featherfly.permission.core.PermissionActor;

/**
 * <p>
 * web环境简单实现，不支持集群
 * </p>
 *
 *
 * @author 钟冀
 */
public class WebActorLoginStorageSimpleImpl
        extends
        AbstractWebActorLoginStorage<WebLoginInfo<PermissionActor>, PermissionActor> {

    /**
	 */
    public WebActorLoginStorageSimpleImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected WebLoginInfo<PermissionActor> createLoginInfo() {
        return new WebLoginInfo<PermissionActor>();
    }
}
