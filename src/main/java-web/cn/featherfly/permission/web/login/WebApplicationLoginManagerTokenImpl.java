package cn.featherfly.permission.web.login;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.permission.core.PermissionActor;
import cn.featherfly.permission.web.authentication.AuthenticationToken;
import cn.featherfly.permission.web.authentication.AuthenticationTokenFactory;

/**
 * <p>
 * WebApplicationLoginManagerTokenImpl, support distribution
 * </p>
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author 钟冀
 */
public class WebApplicationLoginManagerTokenImpl<W extends WebLoginInfo<A>, A extends PermissionActor>
        extends AbstractWebApplicationLoginManager<W, A> {

    private AuthenticationTokenFactory factory;

    /**
     */
    public WebApplicationLoginManagerTokenImpl() {
    }

    /**
     * 返回factory
     *
     * @return factory
     */
    public AuthenticationTokenFactory getFactory() {
        return factory;
    }

    /**
     * 设置factory
     *
     * @param factory factory
     */
    public void setFactory(AuthenticationTokenFactory factory) {
        this.factory = factory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getKey(HttpServletRequest request) {
        AuthenticationToken authenticationToken = factory.create(request);
        return authenticationToken.getIdentity();
    }
}
