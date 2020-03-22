package cn.featherfly.authorities.web.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.featherfly.authorities.web.WebAuthenticatedChecker;
import cn.featherfly.authorities.web.WebEnv;

/**
 * <p>
 * AuthorizedInterceptor
 * </p>
 *
 * @author 钟冀
 */
public class AuthenticatedCheckerInterceptor implements HandlerInterceptor {

    private WebAuthenticatedChecker authenticatedChecker;

    /**
     *
     */
    public AuthenticatedCheckerInterceptor() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return authenticatedChecker.check(new WebEnv(request, response));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    /**
     * 设置authenticatedChecker
     *
     * @param authenticatedChecker authenticatedChecker
     */
    public void setAuthenticatedChecker(WebAuthenticatedChecker authenticatedChecker) {
        this.authenticatedChecker = authenticatedChecker;
    }
}
