package cn.featherfly.authorities.web;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.featherfly.authorities.AuthenticatedChecker;
import cn.featherfly.authorities.AuthorityException;
import cn.featherfly.authorities.web.login.WebLoginManager;
import cn.featherfly.common.api.Response;
import cn.featherfly.common.lang.LogUtils;
import cn.featherfly.common.locale.ResourceBundleUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * AuthorizedInterceptor.
 *
 * @author zhongj
 */
public class WebAuthenticatedChecker extends AbstractChecker implements AuthenticatedChecker<WebEnv> {

    private WebLoginManager<?, ?> loginManager;

    private String authenticateURL = "/";

    /**
     * Instantiates a new web authenticated checker.
     */
    public WebAuthenticatedChecker() {
        super(new ObjectMapper());
    }

    /**
     * Instantiates a new web authenticated checker.
     *
     * @param objectMapper the object mapper
     */
    public WebAuthenticatedChecker(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doCheck(HttpServletRequest request, HttpServletResponse response, String uri) {
        Response<?> result = new Response<>();
        boolean authenticated = true;
        if (!loginManager.isLogin(request)) {
            try {
                result.setMessage(ResourceBundleUtils.getString(AuthorityException.class, "session.invalidation"));
            } catch (Exception e) {
                LogUtils.error(e, logger);
            }
            authenticated = false;
        } else if (!loginManager.getLoginInfo(request).getActor().isAvailable()) {
            try {
                result.setMessage(ResourceBundleUtils.getString(AuthorityException.class, "user.available"));
            } catch (Exception e) {
                LogUtils.error(e, logger);
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
