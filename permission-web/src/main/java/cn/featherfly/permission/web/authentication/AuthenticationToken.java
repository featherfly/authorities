
package cn.featherfly.permission.web.authentication;

/**
 * <p>
 * AuthenticationKey
 * </p>
 * 
 *
 * @author zhongj
 */
public interface AuthenticationToken {
    /**
     * 验证token
     *
     * @param token token
     * @return 是否是当前token boolean
     */
    boolean verify(String token);

    /**
     * Gets identity.
     *
     * @return the identity
     */
    String getIdentity();
}
