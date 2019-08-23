package cn.featherfly.permission.web.login;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.permission.PermissionActor;

/**
 * <p>
 * WebApplicationLoginManagerImpl
 * </p>
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author 钟冀
 */
public class WebApplicationLoginManagerSessionImpl<W extends WebLoginInfo<A>, A extends PermissionActor>
        extends AbstractWebApplicationLoginManager<W, A> {

    /**
     */
    public WebApplicationLoginManagerSessionImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getKey(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
