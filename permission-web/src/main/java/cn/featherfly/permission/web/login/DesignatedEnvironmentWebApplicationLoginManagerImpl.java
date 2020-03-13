package cn.featherfly.permission.web.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.permission.PermissionActor;
import cn.featherfly.permission.login.DesignatedEnvironmentLoginManager;
import cn.featherfly.permission.login.LoginListener;

/**
 * <p>
 * WebApplicationLoginManagerImpl
 * </p>
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author 钟冀
 */
public class DesignatedEnvironmentWebApplicationLoginManagerImpl<W extends WebLoginInfo<A>, A extends PermissionActor>
        implements DesignatedEnvironmentLoginManager<W, A> {

    private WebApplicationLoginManagerSessionImpl<W, A> loginManger;

    private HttpServletRequest request;

    /**
     * @param loginManger
     */
    public DesignatedEnvironmentWebApplicationLoginManagerImpl(WebApplicationLoginManagerSessionImpl<W, A> loginManger,
            HttpServletRequest request) {
        super();
        this.loginManger = loginManger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void login(A actor) {
        loginManger.login(actor, request);
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
