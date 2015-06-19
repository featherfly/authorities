
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
public class ValidateCodeAuthenticator<A extends PermissionActor> implements WebAuthenticator<A>{

	private String validateCodeName = "validateCode";

	/**
	 */
	public ValidateCodeAuthenticator() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void authenticate(A actor, HttpServletRequest request) {
		String validateCode = request.getParameter(validateCodeName);
		if (LangUtils.isNotEmpty(validateCode)) {
			if (validateCode.equals(request.getSession().getAttribute(validateCodeName))) {
				return;
			}
		}
		throw new AuthenticationException("@permission#validateCode.error");
	}

}
