
package cn.featherfly.authorities.web.authentication;

import cn.featherfly.common.lang.Lang;
import jakarta.servlet.http.HttpServletRequest;

/**
 * AuthenticationTokenFactory.
 *
 * @author zhongj
 */
public class AuthenticationTokenFactoryImpl implements AuthenticationTokenFactory {

    /**
     * The Enum AuthenticationTokens.
     *
     * @author zhongj
     */
    public enum AuthenticationTokens {
        /** The Simple authentication token. */
        SimpleAuthenticationToken,
        /** The Authentication key token. */
        AuthenticationKeyToken,
        /** The Authentication key token with request param. */
        AuthenticationKeyTokenWithRequestParam
    }

    private AuthenticationTokens type = AuthenticationTokens.SimpleAuthenticationToken;

    /**
     * 返回type.
     *
     * @return type
     */
    public AuthenticationTokens getType() {
        return type;
    }

    /**
     * 设置type.
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
