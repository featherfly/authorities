package cn.featherfly.authorities.web.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.featherfly.authorities.web.WebAuthenticatedChecker;
import cn.featherfly.authorities.web.WebEnv;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * AuthorizedInterceptor.
 *
 * @author zhongj
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
