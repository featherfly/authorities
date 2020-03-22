
package cn.featherfly.authorities.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.Authority;
import cn.featherfly.authorities.AuthorityChecker;
import cn.featherfly.authorities.AuthorityException;
import cn.featherfly.authorities.web.login.WebLoginManager;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.locale.ResourceBundleUtils;
import cn.featherfly.web.spring.servlet.view.Result;

/**
 * <p>
 * WebAuthorityChecker
 * </p>
 *
 * @author zhongj
 */
public class WebAuthorityChecker extends AbstractChecker implements AuthorityChecker<WebEnv> {

    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private WebLoginManager<?, ?> loginManager;

    private String redirectURL;

    private boolean autoRedirect;

    private WebAuthorityFacotry facotry;

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doCheck(HttpServletRequest request, HttpServletResponse response, String uri) {
        Result<?> result = new Result<>();
        result.setStatus(0);
        Authority authority = facotry.create(request);
        if (authority != null) {
            Actor actor = loginManager.getLoginInfo(request).getActor();
            if (!actor.hasAuthority(authority)) {
                String authorityName = authority.getName();
                if (LangUtils.isEmpty(authorityName)) {
                    authorityName = request.getMethod().toUpperCase() + ":" + uri;
                }
                result.setMessage(ResourceBundleUtils.getString(AuthorityException.class, "authority.not.auth",
                        new Object[] { authorityName }));
                if (request.getHeader("Accept").contains("application/json")) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    render(response, result);
                } else {
                    String location = redirectURL;
                    if (LangUtils.isEmpty(location)) {
                        location = request.getHeader("Referer");
                    }
                    try {
                        if (autoRedirect && LangUtils.isNotEmpty(location)) {
                            response.sendRedirect(location);
                        } else {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, result.getMessage());
                        }
                    } catch (IOException e) {
                        throw new AuthorityException(e);
                    }
                }
                return false;
            }
        }
        return true;
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
     * 设置redirectURL
     *
     * @param redirectURL redirectURL
     */
    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    /**
     * 设置autoRedirect
     *
     * @param autoRedirect autoRedirect
     */
    public void setAutoRedirect(boolean autoRedirect) {
        this.autoRedirect = autoRedirect;
    }

    /**
     * 设置facotry
     *
     * @param facotry facotry
     */
    public void setFacotry(WebAuthorityFacotry facotry) {
        this.facotry = facotry;
    }
}
