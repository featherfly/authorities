package cn.featherfly.permission.web.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.permission.authentication.AuthenticationException;
import cn.featherfly.permission.core.PermissionActor;
import cn.featherfly.permission.exception.PermissionException;
import cn.featherfly.permission.login.LoginEvent;
import cn.featherfly.permission.login.LoginListener;
import cn.featherfly.permission.web.authentication.WebAuthenticator;
import cn.featherfly.web.servlet.ServletUtils;

/**
 * <p>
 * WebApplicationLoginManagerImpl
 * </p>
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author 钟冀
 */
public abstract class AbstractWebApplicationLoginManager<W extends WebLoginInfo<A>, A extends PermissionActor>
        implements WebApplicationLoginManager<W, A> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     */
    public AbstractWebApplicationLoginManager() {
    }

    /**
     * {@inheritDoc}
     */
    /**
     * {@inheritDoc}
     */

    @Override
    public void login(A actor, HttpServletRequest request) {
        logger.debug("登录: {}", actor.getDescp());
        if (LangUtils.isEmpty(authenticators)) {
            throw new PermissionException("@permission#authenticators.null");
        }
        // 判断串session
        String key = getKey(request);
        W info = webActorLoginStorage.getLoginInfo(key);
        if (info != null && checkCrossSession) {
            if (!info.getActor().getId().equals(actor.getId())) {
                throw new AuthenticationException("@permission#session.with.account");
            }
        }
        // 授权验证
        // authrizate(request, webActorLoginStorage.getLoginActors().size());
        for (@SuppressWarnings("unchecked")
        WebAuthenticator<A> authenticator : authenticators) {
            authenticator.authenticate(actor, request);
        }
        if (!isSameOnline() && isLogin(actor)) {
            logger.debug("{}已经登录，注销之前的登录信息并重新登录", actor.getDescp());
            logout(actor);
        }
        webActorLoginStorage.store(key, actor);
        W webLoginInfo = getLoginInfo(request);
        webLoginInfo.setIp(ServletUtils.getIpAddr(request));
        LoginEvent<W, A> loginEvent = new LoginEvent<>();
        loginEvent.setLoginInfo(webLoginInfo);
        for (LoginListener<W, A> loginListener : loginListeners) {
            loginListener.onLogin(loginEvent);
        }
    }

    protected abstract String getKey(HttpServletRequest request);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogin(HttpServletRequest request) {
        // authrizate(request, webActorLoginStorage.getLoginActors().size());
        return webActorLoginStorage.containsKey(request.getSession().getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLogin(A actor) {
        return getLoginInfo(actor) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout(HttpServletRequest request) {
        logout(request.getSession());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout(HttpSession session) {
        if (logger.isDebugEnabled()) {
            W webLoginInfo = getLoginInfo(session);
            if (webLoginInfo != null) {
                logger.debug("注销：{}", webLoginInfo.getActor().getDescp());
            }
        }
        webActorLoginStorage.remove(session.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout(A actor) {
        if (actor != null) {
            logger.debug("注销：{}", actor.getDescp());
            webActorLoginStorage.remove(actor);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<A> getLoginActors() {
        return webActorLoginStorage.getLoginActors();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public W getLoginInfo(HttpServletRequest request) {
        return getLoginInfo(request.getSession());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public W getLoginInfo(HttpSession session) {
        return webActorLoginStorage.getLoginInfo(session.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public W getLoginInfo(A actor) {
        return webActorLoginStorage.getLoginInfo(actor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<W> getLoginInfos() {
        return webActorLoginStorage.getLoginInfos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLoginListener(LoginListener<W, A> loginListener) {
        loginListeners.add(loginListener);
    }

    // ********************************************************************
    //
    // ********************************************************************
    /*
     * private void authrizate(HttpServletRequest request, int inlineNum) {
     * License license = License.getFromPersistence(); if (license == null) {
     * //没有注册 String msg = "该产品尚未注册，需进行注册。";
     * request.setAttribute(AuthorizationAction.NO_AUTHORIZATION_MESSAGE, msg);
     * throw new AuthrizationAuthenticationException(msg); } else if
     * (license.isOvertime()) { //过期 String msg = "许可证已过期！（许可证日期到：" +
     * DateUtils.formart(license.getEndDate(), "yyyy-MM-dd") + "），需重新注册。";
     * request.setAttribute(AuthorizationAction.NO_AUTHORIZATION_MESSAGE, msg);
     * throw new AuthrizationAuthenticationException(msg); } else if
     * (!license.hasMoreOnLineUser(inlineNum)) { throw new
     * AuthrizationAuthenticationException("已经达到最大在线用户数（" +
     * license.getOnLineUserNumber() + "）！"); } }
     */
    // private <A extends Actor> A getActor(HttpServletRequest request) {
    // return (A) request.getSession().getAttribute(LOGIN_USER_KEY);
    // }
    //
    // private <A extends Actor> void setActor(HttpServletRequest request, A
    // actor) {
    // request.getSession().setAttribute(LOGIN_USER_KEY, actor);
    // }

    // ********************************************************************
    //
    // ********************************************************************

    private WebActorLoginStorage<W, A> webActorLoginStorage;

    private List<LoginListener<W, A>> loginListeners = new ArrayList<>();

    // private Map<String, WebLoginInfo> webLoginInfos = new HashMap<String,
    // WebLoginInfo>();

    private boolean sameOnline;

    private boolean checkCrossSession;

    /**
     * 返回sameOnline
     *
     * @return sameOnline
     */
    public boolean isSameOnline() {
        return sameOnline;
    }

    /**
     * 设置sameOnline
     *
     * @param sameOnline sameOnline
     */
    public void setSameOnline(boolean sameOnline) {
        this.sameOnline = sameOnline;
    }

    /**
     * 设置webActorLoginStorage
     *
     * @param webActorLoginStorage webActorLoginStorage
     */
    public void setWebActorLoginStorage(WebActorLoginStorage<W, A> webActorLoginStorage) {
        this.webActorLoginStorage = webActorLoginStorage;
    }

    /**
     * 设置loginListeners
     *
     * @param loginListeners loginListeners
     */
    public void setLoginListeners(List<LoginListener<W, A>> loginListeners) {
        this.loginListeners = loginListeners;
    }

    @SuppressWarnings("rawtypes")
    private List<WebAuthenticator> authenticators = new ArrayList<>();

    /**
     * 返回authenticators
     *
     * @return authenticators
     */
    @SuppressWarnings("rawtypes")
    public List<WebAuthenticator> getAuthenticators() {
        return authenticators;
    }

    /**
     * 设置authenticators
     *
     * @param authenticators authenticators
     */
    public void setAuthenticators(@SuppressWarnings("rawtypes") List<WebAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    /**
     * 返回checkCrossSession
     *
     * @return checkCrossSession
     */
    public boolean isCheckCrossSession() {
        return checkCrossSession;
    }

    /**
     * 设置checkCrossSession
     *
     * @param checkCrossSession checkCrossSession
     */
    public void setCheckCrossSession(boolean checkCrossSession) {
        this.checkCrossSession = checkCrossSession;
    }
}
