package cn.featherfly.permission.web.login;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.permission.Actor;

/**
 * <p>
 * WebLoginManagerSessionImpl
 * </p>
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author 钟冀
 */
public class WebLoginManagerSessionImpl<W extends WebLoginInfo<A>, A extends Actor>
        extends AbstractWebLoginManager<W, A> {

    /**
     */
    public WebLoginManagerSessionImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getKey(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
