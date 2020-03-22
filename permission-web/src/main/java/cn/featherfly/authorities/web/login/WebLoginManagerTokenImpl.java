package cn.featherfly.authorities.web.login;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.web.authentication.AuthenticationToken;
import cn.featherfly.authorities.web.authentication.AuthenticationTokenFactory;

/**
 * <p>
 * WebLoginManagerTokenImpl, support distribution
 * </p>
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author 钟冀
 */
public class WebLoginManagerTokenImpl<W extends WebLoginInfo<A>, A extends Actor>
        extends AbstractWebLoginManager<W, A> {

    private AuthenticationTokenFactory factory;

    /**
     */
    public WebLoginManagerTokenImpl() {
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
