package cn.featherfly.authorities.web.springmvc.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.featherfly.authorities.login.LoginInfo;
import cn.featherfly.authorities.web.login.WebLoginManager;
import cn.featherfly.common.lang.ClassUtils;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 登陆信息方法参数Resolver.
 *
 * @author zhongj
 */
public class LoginInfoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //        return parameter.hasParameterAnnotation(Login.class)
        //                && ClassUtils.isParent(LoginInfo.class,
        //                        parameter.getParameterType());
        return ClassUtils.isParent(LoginInfo.class, parameter.getParameterType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return loginManager.getLoginInfo((HttpServletRequest) webRequest.getNativeRequest());
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
