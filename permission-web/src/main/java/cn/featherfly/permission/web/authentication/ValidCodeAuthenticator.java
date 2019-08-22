package cn.featherfly.permission.web.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.validate.VerifyCodeUtils;
import cn.featherfly.permission.authentication.AuthenticationException;
import cn.featherfly.permission.core.PermissionActor;

/**
 * <p>
 * 验证码验证器
 * </p>
 *
 * @param <A> 类型
 * @author 钟冀
 */
public class ValidCodeAuthenticator<A extends PermissionActor> implements WebAuthenticator<A> {

    /**
     * 从会话和请求参数获取验证码的KEY
     */
    public static final String VALID_CODE_KEY = "validCode";

    /**
     */
    public ValidCodeAuthenticator() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void authenticate(A actor, HttpServletRequest request) {
        String validCode = request.getParameter(VALID_CODE_KEY);
        if (LangUtils.isEmpty(validCode)) {
            Object valid = request.getAttribute(VALID_CODE_KEY);
            if (valid != null) {
                validCode = valid.toString();
            }
        }
        Object storedValidCode = request.getSession().getAttribute(VALID_CODE_KEY);
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
        throw new AuthenticationException("@permission#validateCode.error");
    }

    /**
     * <p>
     * 设置需要验证的code，用于在authenticate方法中使用
     * </p>
     *
     * @param request   request
     * @param validCode code
     */
    public static void setVerifyValidCode(HttpServletRequest request, String validCode) {
        request.setAttribute(VALID_CODE_KEY, validCode);
    }

    /**
     * <p>
     * 设置生成的验证码用于后续进行验证
     * </p>
     *
     * @param request   request
     * @param validCode code
     */
    public static void setGeneratedValidCode(HttpServletRequest request, String validCode) {
        request.getSession().setAttribute(VALID_CODE_KEY, validCode);
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
        ValidCodeAuthenticator.setGeneratedValidCode(request, verifyCode);
        //生成图片
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

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
}
