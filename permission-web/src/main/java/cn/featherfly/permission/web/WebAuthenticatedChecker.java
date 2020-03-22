package cn.featherfly.permission.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.featherfly.common.lang.LogUtils;
import cn.featherfly.common.locale.ResourceBundleUtils;
import cn.featherfly.permission.AuthenticatedChecker;
import cn.featherfly.permission.AuthorityException;
import cn.featherfly.permission.web.login.WebLoginManager;
import cn.featherfly.web.spring.servlet.view.Result;

/**
 * <p>
 * AuthorizedInterceptor
 * </p>
 *
 * @author 钟冀
 */
public class WebAuthenticatedChecker extends AbstractChecker implements AuthenticatedChecker<WebEnv> {

    private WebLoginManager<?, ?> loginManager;

    private String authenticateURL = "/";

    /**
     *
     */
    public WebAuthenticatedChecker() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doCheck(HttpServletRequest request, HttpServletResponse response, String uri) {
        Result<?> result = new Result<>();
        result.setStatus(0);
        boolean authenticated = true;
        if (!loginManager.isLogin(request)) {
            try {
                result.setMessage(ResourceBundleUtils.getString(AuthorityException.class, "session.invalidation"));
                request.getSession().invalidate();
            } catch (Exception e) {
                LogUtils.debug(e, logger);
            }
            authenticated = false;
        } else if (!loginManager.getLoginInfo(request).getActor().isAvailable()) {
            try {
                result.setMessage(ResourceBundleUtils.getString(AuthorityException.class, "user.available"));
            } catch (Exception e) {
                LogUtils.debug(e, logger);
            }
            authenticated = false;
        }
        if (!authenticated) {
            request.getSession().invalidate();
            response.setHeader("WWW-Authenticate", authenticateURL);
            if (request.getHeader("Accept").contains("application/json")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                render(response, result);
            } else {
                try {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, result.getMessage());
                } catch (IOException e) {
                    throw new AuthorityException(e);
                }
            }
        }
        return authenticated;
    }

    /**
     * 设置loginManager
     *
     * @param loginManager loginManager
     */
    public void setLoginManager(WebLoginManager<?, ?> loginManager) {
        this.loginManager = loginManager;
    }

    /**
     * 设置authenticateURL
     *
     * @param authenticateURL authenticateURL
     */
    public void setAuthenticateURL(String authenticateURL) {
        this.authenticateURL = authenticateURL;
    }
}
