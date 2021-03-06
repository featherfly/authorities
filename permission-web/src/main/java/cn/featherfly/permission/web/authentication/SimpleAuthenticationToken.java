package cn.featherfly.permission.web.authentication;

import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * SimpleAuthenticationToken
 * </p>
 * <p>
 * 2019-08-21
 * </p>
 *
 * @author zhongj
 */
public class SimpleAuthenticationToken implements AuthenticationToken {

    private String token;

    /**
     * @param token
     */
    public SimpleAuthenticationToken(String token) {
        super();
        this.token = token;
    }

    /**
     * 返回token
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token
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
        if (LangUtils.isEmpty(token)) {
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
