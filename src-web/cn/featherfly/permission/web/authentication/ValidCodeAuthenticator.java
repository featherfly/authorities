
package cn.featherfly.permission.web.authentication;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.permission.authentication.AuthenticationException;
import cn.featherfly.permission.core.PermissionActor;

/**
 * <p>
 * 验证码验证器
 * </p>
 *
 * @param <A> 类型
 * @author 钟冀
 */
public class ValidCodeAuthenticator<A extends PermissionActor> implements WebAuthenticator<A>{

    /**
     * 从会话和请求参数获取验证码的KEY
     */
    public static final String VALID_CODE_KEY = "validCode";
    	
	/**
	 */
	public ValidCodeAuthenticator() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void authenticate(A actor, HttpServletRequest request) {
		String validateCode = request.getParameter(VALID_CODE_KEY);
		if (LangUtils.isNotEmpty(validateCode)) {
			if (validateCode.equals(request.getSession().getAttribute(VALID_CODE_KEY))) {
				return;
			}
		}
		throw new AuthenticationException("@permission#validateCode.error");
	}

}
