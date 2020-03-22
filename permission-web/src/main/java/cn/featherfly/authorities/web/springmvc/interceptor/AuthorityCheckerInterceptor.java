package cn.featherfly.authorities.web.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.featherfly.authorities.web.WebAuthorityChecker;
import cn.featherfly.authorities.web.WebEnv;

/**
 * <p>
 * AuthorityCheckerInterceptor
 * </p>
 *
 * @author 钟冀
 */
public class AuthorityCheckerInterceptor implements HandlerInterceptor {

    private WebAuthorityChecker authorityChecker;

    /**
     *
     */
    public AuthorityCheckerInterceptor() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return authorityChecker.check(new WebEnv(request, response));
    }

    // 在业务处理器处理请求执行完成后,生成视图之前执行的动作
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
     * 设置authorityChecker
     *
     * @param authorityChecker authorityChecker
     */
    public void setAuthorityChecker(WebAuthorityChecker authorityChecker) {
        this.authorityChecker = authorityChecker;
    }
}
