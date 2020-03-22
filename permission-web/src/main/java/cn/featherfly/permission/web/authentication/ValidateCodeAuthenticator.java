package cn.featherfly.permission.web.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.locale.ResourceBundleUtils;
import cn.featherfly.common.validate.VerifyCodeUtils;
import cn.featherfly.permission.Actor;
import cn.featherfly.permission.AuthorityException;
import cn.featherfly.permission.authentication.AuthenticationException;

/**
 * <p>
 * 验证码验证器
 * </p>
 *
 * @param <A> 类型
 * @author zhongj
 */
public class ValidateCodeAuthenticator<A extends Actor> implements WebAuthenticator<A> {
    /**
     */
    public ValidateCodeAuthenticator() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void authenticate(A actor, HttpServletRequest request) {
        String validCode = request.getParameter(validateCodeKey);
        if (LangUtils.isEmpty(validCode)) {
            Object valid = request.getAttribute(validateCodeKey);
            if (valid != null) {
                validCode = valid.toString();
            }
        }
        Object storedValidCode = request.getSession().getAttribute(validateCodeKey);
        if (LangUtils.isNotEmpty(validCode) && storedValidCode != null) {
            if (caseSensitive) {
                if (validCode.equals(storedValidCode.toString())) {
                    return;
                }
            } else {
                if (validCode.equalsIgnoreCase(storedValidCode.toString())) {
                    return;
                }
            }
        }

        throw new AuthenticationException(
                ResourceBundleUtils.getString(AuthorityException.class, "validateCode.error"));
    }

    /**
     * <p>
     * 设置需要验证的code，用于在authenticate方法中使用
     * </p>
     *
     * @param request   request
     * @param validCode code
     */
    public void setVerifyValidCode(HttpServletRequest request, String validCode) {
        request.setAttribute(validateCodeKey, validCode);
    }

    /**
     * <p>
     * 设置生成的验证码用于后续进行验证
     * </p>
     *
     * @param request   request
     * @param validCode code
     */
    public void setGeneratedValidCode(HttpServletRequest request, String validCode) {
        request.getSession().setAttribute(validateCodeKey, validCode);
    }

    /**
     * 生成验证图片并使用response输出
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void generateValidCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(validCodeSize);
        //设置
        setGeneratedValidCode(request, verifyCode);
        //生成图片
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    /**
     * 从会话和请求参数获取验证码的KEY
     */
    private String validateCodeKey = "VALIDATE_CODE";

    private boolean caseSensitive;

    private int validCodeSize = 4;

    /**
     * 返回validCodeSize
     *
     * @return validCodeSize
     */
    public int getValidCodeSize() {
        return validCodeSize;
    }

    /**
     * 设置validCodeSize
     *
     * @param validCodeSize validCodeSize
     */
    public void setValidCodeSize(int validCodeSize) {
        this.validCodeSize = validCodeSize;
    }

    /**
     * 返回caseSensitive
     *
     * @return caseSensitive
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * 设置caseSensitive
     *
     * @param caseSensitive caseSensitive
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    /**
     * 返回validateCodeKey
     *
     * @return validateCodeKey
     */
    public String getValidateCodeKey() {
        return validateCodeKey;
    }

    /**
     * 设置validateCodeKey
     *
     * @param validateCodeKey validateCodeKey
     */
    public void setValidateCodeKey(String validateCodeKey) {
        this.validateCodeKey = validateCodeKey;
    }

}
