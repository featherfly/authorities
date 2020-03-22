package cn.featherfly.authorities.web.authentication;

/**
 * <p>
 * AuthenticationKeyTokenV1
 * </p>
 * 
 *
 * @author zhongj
 */
public abstract class AbstractAuthenticationKeyToken implements AuthenticationKeyToken {

    private static final String AK_SPLIT_SIGN = ":";

    protected String identity;

    protected String signature;

    protected String authenticationKey;

    protected Long timestamp;

    protected String akSplitSign = AK_SPLIT_SIGN;

    /**
     * 返回akSplitSign
     *
     * @return akSplitSign
     */
    public String getAkSplitSign() {
        return akSplitSign;
    }

    /**
     * 设置akSplitSign
     *
     * @param akSplitSign akSplitSign
     */
    public void setAkSplitSign(String akSplitSign) {
        this.akSplitSign = akSplitSign;
    }

    /**
     * Gets signature.
     *
     * @return the signature
     */
    @Override
    public String getSignature() {
        return signature;
    }

    /**
     * Sets signature.
     *
     * @param signature the signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * Gets authentication key.
     *
     * @return the authentication key
     */
    @Override
    public String getAuthenticationKey() {
        return authenticationKey;
    }

    /**
     * Gets identity.
     *
     * @return the identity
     */
    @Override
    public String getIdentity() {
        return identity;
    }

    /**
     * 根据AuthenticationKey对应的时间戳
     */
    @Override
    public Long getTimestamp() {
        return timestamp;
    }
}
