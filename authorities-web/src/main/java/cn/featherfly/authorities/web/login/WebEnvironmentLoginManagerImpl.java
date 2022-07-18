package cn.featherfly.authorities.web.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.authentication.Authentication;
import cn.featherfly.authorities.login.LoginListener;

/**
 * WebEnvironmentLoginManagerImpl.
 *
 * @author 钟冀
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 */
public class WebEnvironmentLoginManagerImpl<W extends WebLoginInfo<A>, A extends Actor>
        implements WebEnvironmentLoginManager<W, A> {

    private WebLoginManager<W, A> loginManger;

    private HttpServletRequest request;

    /**
     * Instantiates a new web environment login manager impl.
     *
     * @param loginManger the login manger
     * @param request     the request
     */
    public WebEnvironmentLoginManagerImpl(WebLoginManager<W, A> loginManger, HttpServletRequest request) {
        super();
        this.loginManger = loginManger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void login(A actor, Authentication authentication) {
        loginManger.login(actor, authentication, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogin() {
        return loginManger.isLogin(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogin(A actor) {
        return loginManger.isLogin(actor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout() {
        loginManger.logout(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout(A actor) {
        loginManger.logout(actor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<A> getLoginActors() {
        return loginManger.getLoginActors();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public W getLoginInfo() {
        return loginManger.getLoginInfo(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public W getLoginInfo(A actor) {
        return loginManger.getLoginInfo(actor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<W> getLoginInfos() {
        return loginManger.getLoginInfos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLoginListener(LoginListener<W, A> loginListener) {
        loginManger.addLoginListener(loginListener);
    }

}
