package cn.featherfly.authorities.web.login;

import cn.featherfly.authorities.Actor;
import jakarta.servlet.http.HttpServletRequest;

/**
 * WebLoginManagerSessionImpl.
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author zhongj
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
