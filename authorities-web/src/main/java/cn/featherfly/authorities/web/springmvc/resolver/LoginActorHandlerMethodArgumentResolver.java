package cn.featherfly.authorities.web.springmvc.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.featherfly.authorities.login.Login;
import cn.featherfly.authorities.web.login.WebLoginManager;

/**
 * <p>
 * 登陆用户方法参数Resolver
 * </p>
 *
 * @author 钟冀
 */
public class LoginActorHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return loginManager.getLoginInfo((HttpServletRequest) webRequest.getNativeRequest()).getActor();
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
