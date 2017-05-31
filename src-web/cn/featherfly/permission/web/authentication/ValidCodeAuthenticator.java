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
 * @param <A>
 *            类型
 * @author 钟冀
 */
public class ValidCodeAuthenticator<A extends PermissionActor> implements
        WebAuthenticator<A> {

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
        String validCode = request.getParameter(VALID_CODE_KEY);
        if (LangUtils.isEmpty(validCode)) {
            Object valid = request.getAttribute(VALID_CODE_KEY);
            if (valid != null) {
                validCode = valid.toString();
            }
        }
        Object storedValidCode = request.getSession().getAttribute(VALID_CODE_KEY);
        if (LangUtils.isNotEmpty(validCode) && storedValidCode != null) {            
            if (caseSensitive) {
                if (validCode.equals(storedValidCode.toString())) {
                    return;
                }
            } else {
                if (validCode.equalsIgnoreCase(storedValidCode.toString())) {
                    return;
                }
            }
        }
        throw new AuthenticationException("@permission#validateCode.error");
    }

    /**
     * <p>
     * 设置需要验证的code，用于在authenticate方法中使用
     * </p>
     * 
     * @param request
     *            request
     * @param validCode
     *            code
     */
    public static void setVerifyValidCode(HttpServletRequest request,
            String validCode) {
        request.setAttribute(VALID_CODE_KEY, validCode);
    }

    /**
     * <p>
     * 设置生成的验证码用于后续进行验证
     * </p>
     * 
     * @param request
     *            request
     * @param validCode
     *            code
     */
    public static void setGeneratedValidCode(HttpServletRequest request,
            String validCode) {
        request.getSession().setAttribute(VALID_CODE_KEY, validCode);
    }

    private boolean caseSensitive;

    /**
     * 返回caseSensitive
     * @return caseSensitive
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * 设置caseSensitive
     * @param caseSensitive caseSensitive
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }
}
