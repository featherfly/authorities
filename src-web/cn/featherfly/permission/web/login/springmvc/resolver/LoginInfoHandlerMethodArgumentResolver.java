
package cn.featherfly.permission.web.login.springmvc.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.permission.login.Login;
import cn.featherfly.permission.login.LoginInfo;
import cn.featherfly.permission.web.login.WebApplicationLoginManager;

/**
 * <p>
 * 登陆信息方法参数Resolver
 * </p>
 * 
 * @author 钟冀
 */
public abstract class LoginInfoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Login.class)
				&& ClassUtils.isParent(LoginInfo.class, parameter.getParameterType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
	    return applicationLoginManager.getLoginInfo((HttpServletRequest) webRequest.getNativeRequest());
	}
	
	private WebApplicationLoginManager<?> applicationLoginManager;

    /**
     * 设置applicationLoginManager
     * @param applicationLoginManager applicationLoginManager
     */
    public void setApplicationLoginManager(
            WebApplicationLoginManager<?> applicationLoginManager) {
        this.applicationLoginManager = applicationLoginManager;
    }	
}
