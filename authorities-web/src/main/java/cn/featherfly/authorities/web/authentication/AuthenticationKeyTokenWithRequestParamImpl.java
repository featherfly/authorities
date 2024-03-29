package cn.featherfly.authorities.web.authentication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

import cn.featherfly.authorities.authentication.AuthenticationException;
import cn.featherfly.common.algorithm.Base64;
import cn.featherfly.common.algorithm.MD5;
import cn.featherfly.common.algorithm.SHA;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * AuthenticationKeyToolV2.
 *
 * @author zhongj
 */
public class AuthenticationKeyTokenWithRequestParamImpl extends AbstractAuthenticationKeyToken {

    private java.nio.charset.Charset requestEncoding = StandardCharsets.UTF_8;

    /**
     * this construct is for decode.
     *
     * @param signature         the signature
     * @param authenticationKey the authentication key
     */
    public AuthenticationKeyTokenWithRequestParamImpl(String signature, String authenticationKey) {
        setSignature(signature);
        setAuthenticationKey(authenticationKey);
    }

    /**
     * this construct is for encode.
     *
     * @param token        the token
     * @param identity     the identity
     * @param timestamp    the timestamp
     * @param requestDescp the request descp
     */
    public AuthenticationKeyTokenWithRequestParamImpl(String token, String identity, Long timestamp,
            String requestDescp) {
        setSignature(token, timestamp, requestDescp);
        setAuthenticationKey(identity, timestamp, requestDescp);
    }

    /**
     * Sets authentication key.
     *
     * @param authenticationKey the authentication key
     */
    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
        try {
            String[] aks = decodeAuthenticationKey(authenticationKey);
            identity = aks[0];
            timestamp = Long.parseLong(aks[1].trim());
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }
    }

    /**
     * 验证token.
     *
     * @param token token
     * @return 是否是当前token boolean
     */
    @Override
    public boolean verify(String token) {
        AssertIllegalArgument.isNotEmpty(signature, "signature不能为空");
        AssertIllegalArgument.isNotEmpty(authenticationKey, "authenticationKey不能为空");
        String[] akvs = decodeAuthenticationKey(authenticationKey);
        return generateSignature(token, Long.parseLong(akvs[1]), akvs[2], false).equals(signature);
    }

    /**
     * 验证token.
     *
     * @param token   token
     * @param request request
     * @return token和request是否正确 boolean
     */
    public boolean verifyRequest(String token, HttpServletRequest request) {
        AssertIllegalArgument.isNotEmpty(signature, "signature");
        AssertIllegalArgument.isNotEmpty(authenticationKey, "authenticationKey");
        String requestDescp = getRequestDescp(request);
        String[] akvs = decodeAuthenticationKey(authenticationKey);
        return generateSignature(token, Long.parseLong(akvs[1]), requestDescp, true).equals(signature);
    }

    /**
     * Gets the request descp.
     *
     * @param request the request
     * @return the request descp
     */
    public String getRequestDescp(HttpServletRequest request) {
        String requestDescp = null;
        if (Lang.isNotEmpty(request.getContentType())
                && MediaType.APPLICATION_JSON.includes(MediaType.parseMediaType(request.getContentType()))) {
            requestDescp = request.getRequestURL() + "?" + getRequestBody(request);
        } else {
            requestDescp = createRequestDescpWithParameter(request);
        }
        return requestDescp;
    }

    private String[] decodeAuthenticationKey(String authenticationKey) {
        String akv;
        try {
            akv = Base64.decryptToString(authenticationKey);
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }
        String[] akvs = akv.split(akSplitSign);
        if (akvs.length != 3) {
            throw new AuthenticationException("非法请求");
        }
        return akvs;
    }

    private String createRequestDescpWithParameter(HttpServletRequest request) {
        String requestDescp;
        String queryParamStr = getParameters(request);
        if (Lang.isNotEmpty(queryParamStr)) {
            queryParamStr = "?" + queryParamStr;
        }
        requestDescp = request.getRequestURL() + queryParamStr;
        return requestDescp;
    }

    private String getParameters(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            if (Lang.isNotEmpty(values)) {
                for (String value : values) {
                    if (null == value) {
                        value = "";
                    }
                    params.append(name).append("=").append(value).append("&");
                }
            }
        }
        if (params.length() > 0) {
            params.deleteCharAt(params.length() - 1);
        }
        return params.toString();
    }

    private String getRequestBody(HttpServletRequest servletRequest) {
        String charset = servletRequest.getCharacterEncoding() == null ? StandardCharsets.UTF_8.displayName()
                : servletRequest.getCharacterEncoding();
        try {
            return IOUtils.toString(servletRequest.getInputStream(), charset);
        } catch (IOException e) {
            throw new AuthenticationException(e);
        }
    }

    /**
     * Sets signature.
     *
     * @param token        the token
     * @param timestamp    the timestamp
     * @param requestDescp the request descp
     */
    public void setSignature(String token, Long timestamp, String requestDescp) {
        this.setSignature(generateSignature(token, timestamp, requestDescp, true));
    }

    private String generateSignature(String token, Long timestamp, String requestDescp,
            boolean isOriginalRequestDescp) {
        try {
            if (isOriginalRequestDescp) {
                requestDescp = MD5.encrypt(requestDescp);
            }
            return SHA.encrypt(token + timestamp + MD5.encrypt(timestamp + requestDescp));
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }
    }

    /**
     * Sets authentication key.
     *
     * @param identity     the identity
     * @param timestamp    the timestamp
     * @param requestDescp the request descp
     */
    public void setAuthenticationKey(String identity, Long timestamp, String requestDescp) {
        this.setAuthenticationKey(generateAuthenticationKey(identity, timestamp, requestDescp));
        this.identity = identity;
    }

    private String generateAuthenticationKey(String identity, Long timestamp, String requestDescp) {
        try {
            return Base64.encryptToString(identity + akSplitSign + timestamp + akSplitSign + MD5.encrypt(requestDescp));
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }
    }

    /**
     * 返回requestEncoding.
     *
     * @return requestEncoding
     */
    public java.nio.charset.Charset getRequestEncoding() {
        return requestEncoding;
    }

    /**
     * 设置requestEncoding.
     *
     * @param requestEncoding requestEncoding
     */
    public void setRequestEncoding(java.nio.charset.Charset requestEncoding) {
        this.requestEncoding = requestEncoding;
    }
}
