
package cn.featherfly.permission.web.authentication;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * AuthenticationTokenFactory
 * </p>
 * <p>
 * 2019-08-21
 * </p>
 *
 * @author zhongj
 */
public class AuthenticationTokenFactoryImpl implements AuthenticationTokenFactory {

    public enum AuthenticationTokens {
        SimpleAuthenticationToken, AuthenticationKeyTokenImpl, AuthenticationKeyTokenWithRequestParamImpl
    }

    private AuthenticationTokens type = AuthenticationTokens.SimpleAuthenticationToken;

    /**
     * 返回type
     *
     * @return type
     */
    public AuthenticationTokens getType() {
        return type;
    }

    /**
     * 设置type
     *
     * @param type type
     */
    public void setType(AuthenticationTokens type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthenticationToken create(HttpServletRequest request) {
        String ak = request.getHeader("ak");
        if (LangUtils.isEmpty(ak)) {
            ak = request.getParameter("ak");
        }
        String signature = request.getHeader("signature");
        if (LangUtils.isEmpty(signature)) {
            signature = request.getParameter("signature");
        }
        switch (type) {
            case AuthenticationKeyTokenImpl:
                return new AuthenticationKeyTokenImpl(signature, ak);
            case AuthenticationKeyTokenWithRequestParamImpl:
                return new AuthenticationKeyTokenWithRequestParamImpl(signature, ak);
            default:
                String token = request.getHeader("token");
                if (LangUtils.isEmpty(token)) {
                    token = request.getParameter("token");
                }
                return new SimpleAuthenticationToken(token);
        }
    }
}
