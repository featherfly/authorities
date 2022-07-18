package cn.featherfly.authorities.web.authentication;

import cn.featherfly.common.lang.Lang;

/**
 * SimpleAuthenticationToken.
 *
 * @author zhongj
 */
public class SimpleAuthenticationToken implements AuthenticationToken {

    private String token;

    /**
     * Instantiates a new simple authentication token.
     *
     * @param token the token
     */
    public SimpleAuthenticationToken(String token) {
        super();
        this.token = token;
    }

    /**
     * 返回token.
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token.
     *
     * @param token token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String token) {
        if (Lang.isEmpty(token)) {
            return false;
        }
        return token.equals(this.token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdentity() {
        return token;
    }

}
