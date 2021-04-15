package cn.featherfly.authorities.web.authentication;

import cn.featherfly.authorities.authentication.AuthenticationException;
import cn.featherfly.common.algorithm.Base64;
import cn.featherfly.common.algorithm.SHA;
import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * <p>
 * AuthenticationKeyTokenV1
 * </p>
 *
 * @author zhongj
 */
public class AuthenticationKeyTokenImpl extends AbstractAuthenticationKeyToken {

    /**
     * @param signature
     * @param authenticationKey
     */
    public AuthenticationKeyTokenImpl(String signature, String authenticationKey) {
        setSignature(signature);
        setAuthenticationKey(authenticationKey);
    }

    /**
     * @param token
     * @param identity
     * @param timestamp
     */
    public AuthenticationKeyTokenImpl(String token, String identity, Long timestamp) {
        setSignature(token, timestamp);
        setAuthenticationKey(identity, timestamp);
    }

    /**
     * Sets authentication key.
     *
     * @param authenticationKey the authentication key
     */
    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
        String[] aks = decodeAuthenticationKey(authenticationKey);
        identity = aks[0];
        timestamp = Long.parseLong(aks[1].trim());
    }

    /**
     * 验证token
     *
     * @param token token
     * @return 是否是当前token boolean
     */
    @Override
    public boolean verify(String token) {
        AssertIllegalArgument.isNotEmpty(signature, "signature");
        AssertIllegalArgument.isNotEmpty(authenticationKey, "authenticationKey");
        String[] akvs = decodeAuthenticationKey(authenticationKey);
        try {
            return SHA.encrypt(token + akvs[1]).equals(signature);
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }
    }

    /**
     * Sets signature.
     *
     * @param token     the token
     * @param timestamp the timestamp
     */
    public void setSignature(String token, Long timestamp) {
        try {
            setSignature(SHA.encrypt(token + timestamp));
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }
    }

    /**
     * Sets authentication key.
     *
     * @param identity  the identity
     * @param timestamp the timestamp
     */
    public void setAuthenticationKey(String identity, Long timestamp) {
        try {
            this.setAuthenticationKey(Base64.encryptToString(identity + akSplitSign + timestamp));
            this.identity = identity;
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }
    }

    private String[] decodeAuthenticationKey(String authenticationKey) {
        try {
            return Base64.decryptToString(authenticationKey).split(akSplitSign);
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }
    }

}
