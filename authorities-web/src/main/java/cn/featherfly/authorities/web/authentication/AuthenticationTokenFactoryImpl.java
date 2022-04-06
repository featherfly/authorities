
package cn.featherfly.authorities.web.authentication;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * AuthenticationTokenFactory
 * </p>
 * 
 *
 * @author zhongj
 */
public class AuthenticationTokenFactoryImpl implements AuthenticationTokenFactory {

    public enum AuthenticationTokens {
        SimpleAuthenticationToken, AuthenticationKeyToken, AuthenticationKeyTokenWithRequestParam
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
        if (Lang.isEmpty(ak)) {
            ak = request.getParameter("ak");
        }
        String signature = request.getHeader("signature");
        if (Lang.isEmpty(signature)) {
            signature = request.getParameter("signature");
        }
        switch (type) {
            case AuthenticationKeyToken:
                return new AuthenticationKeyTokenImpl(signature, ak);
            case AuthenticationKeyTokenWithRequestParam:
                return new AuthenticationKeyTokenWithRequestParamImpl(signature, ak);
            default:
                String token = request.getHeader("token");
                if (Lang.isEmpty(token)) {
                    token = request.getParameter("token");
                }
                return new SimpleAuthenticationToken(token);
        }
    }
}
