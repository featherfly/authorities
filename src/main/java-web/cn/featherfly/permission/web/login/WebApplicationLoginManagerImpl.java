package cn.featherfly.permission.web.login;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.permission.core.PermissionActor;

/**
 * <p>
 * WebApplicationLoginManagerImpl
 * </p>
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author 钟冀
 * @deprecated use {@link WebApplicationLoginManagerSessionImpl} instead
 */
@Deprecated
public class WebApplicationLoginManagerImpl<W extends WebLoginInfo<A>, A extends PermissionActor>
        extends AbstractWebApplicationLoginManager<W, A> {

    /**
     */
    public WebApplicationLoginManagerImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getKey(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
