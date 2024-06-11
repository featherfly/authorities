package cn.featherfly.authorities.web.authentication;

/**
 * AuthenticationKeyTokenV1.
 *
 * @author zhongj
 */
public abstract class AbstractAuthenticationKeyToken implements AuthenticationKeyToken {

    private static final String AK_SPLIT_SIGN = ":";

    /** The identity. */
    protected String identity;

    /** The signature. */
    protected String signature;

    /** The authentication key. */
    protected String authenticationKey;

    /** The timestamp. */
    protected Long timestamp;

    /** The ak split sign. */
    protected String akSplitSign = AK_SPLIT_SIGN;

    /**
     * 返回akSplitSign.
     *
     * @return akSplitSign
     */
    public String getAkSplitSign() {
        return akSplitSign;
    }

    /**
     * 设置akSplitSign.
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
     * 根据AuthenticationKey对应的时间戳.
     *
     * @return the timestamp
     */
    @Override
    public Long getTimestamp() {
        return timestamp;
    }
}
