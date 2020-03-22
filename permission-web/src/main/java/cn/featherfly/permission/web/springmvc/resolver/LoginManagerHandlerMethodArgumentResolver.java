package cn.featherfly.permission.web.springmvc.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.permission.web.login.WebEnvironmentLoginManager;
import cn.featherfly.permission.web.login.WebEnvironmentLoginManagerImpl;
import cn.featherfly.permission.web.login.WebLoginManager;

/**
 * <p>
 * LoginManager方法参数Resolver
 * </p>
 *
 * @author 钟冀
 */
public class LoginManagerHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ClassUtils.isParent(WebEnvironmentLoginManager.class, parameter.getParameterType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return new WebEnvironmentLoginManagerImpl<>(loginManager, (HttpServletRequest) webRequest.getNativeRequest());
    }

    private WebLoginManager<?, ?> loginManager;

    /**
     * 设置loginManager
     *
     * @param loginManager loginManager
     */
    public void setLoginManager(WebLoginManager<?, ?> loginManager) {
        this.loginManager = loginManager;
    }

}
