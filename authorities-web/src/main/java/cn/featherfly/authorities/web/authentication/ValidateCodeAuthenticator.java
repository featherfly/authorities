package cn.featherfly.authorities.web.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.AuthorityException;
import cn.featherfly.authorities.authentication.AuthenticationException;
import cn.featherfly.authorities.authentication.Authentications;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.locale.ResourceBundleUtils;
import cn.featherfly.common.validate.SimpleValidateCodeGenerator;
import cn.featherfly.common.validate.ValidateCode;
import cn.featherfly.common.validate.ValidateCodeGenerator;
import cn.featherfly.common.validate.ValidateCodeUtils;

/**
 * <p>
 * 验证码验证器
 * </p>
 *
 * @param <A> 类型
 * @author zhongj
 */
public class ValidateCodeAuthenticator<A extends Actor> extends AbstractWebAuthenticator<A> {

    /**
     */
    public ValidateCodeAuthenticator() {
        SimpleValidateCodeGenerator g = new SimpleValidateCodeGenerator();
        g.setSize(4);
        authentications.add(Authentications.USERNAME_PASSWORD);
        this.validateCodeGenerator = g;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void authenticate(A actor, HttpServletRequest request) {
        String clientValidateCode = request.getParameter(validateCodeKey);
        if (Lang.isEmpty(clientValidateCode)) {
            Object valid = request.getAttribute(validateCodeKey);
            if (valid != null) {
                clientValidateCode = valid.toString();
            }
        }
        ValidateCode validateCode = getGeneratedValidCode(request);
        if (Lang.isNotEmpty(clientValidateCode) && validateCode != null) {
            if (caseSensitive) {
                if (clientValidateCode.equals(validateCode.getValid())) {
                    return;
                }
            } else {
                if (clientValidateCode.equalsIgnoreCase(validateCode.getValid().toString())) {
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
    public void setClientValidateCode(HttpServletRequest request, String validCode) {
        request.setAttribute(validateCodeKey, validCode);
    }

    /**
     * <p>
     * 获取需要验证的code，用于在authenticate方法中使用
     * </p>
     *
     * @param request request
     * @return
     */
    public String getClientValidateCode(HttpServletRequest request) {
        String clientValidateCode = request.getParameter(validateCodeKey);
        if (Lang.isEmpty(clientValidateCode)) {
            Object valid = request.getAttribute(validateCodeKey);
            if (valid != null) {
                clientValidateCode = valid.toString();
            }
        }
        return clientValidateCode;
    }

    /**
     * <p>
     * 设置生成的验证码用于后续进行验证
     * </p>
     *
     * @param request   request
     * @param validCode code
     */
    public void setGeneratedValidCode(HttpServletRequest request, ValidateCode validCode) {
        request.getSession().setAttribute(validateCodeKey, validCode);
    }

    /**
     * getGeneratedValidCode
     *
     * @param request
     * @return
     */
    public ValidateCode getGeneratedValidCode(HttpServletRequest request) {
        return (ValidateCode) request.getSession().getAttribute(validateCodeKey);
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
        ValidateCode validateCode = validateCodeGenerator.generate();
        //设置
        setGeneratedValidCode(request, validateCode);
        //生成图片
        ValidateCodeUtils.outputImage(imageWith, imageHight, response.getOutputStream(), validateCode.getShow());
    }

    /**
     * 从会话和请求参数获取验证码的KEY
     */
    private String validateCodeKey = "VALIDATE_CODE";

    private boolean caseSensitive;

    private int imageHight = 80;

    private int imageWith = 200;

    private ValidateCodeGenerator validateCodeGenerator;

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

    /**
     * 返回validateCodeGenerator
     *
     * @return validateCodeGenerator
     */
    public ValidateCodeGenerator getValidateCodeGenerator() {
        return validateCodeGenerator;
    }

    /**
     * 设置validateCodeGenerator
     *
     * @param validateCodeGenerator validateCodeGenerator
     */
    public void setValidateCodeGenerator(ValidateCodeGenerator validateCodeGenerator) {
        this.validateCodeGenerator = validateCodeGenerator;
    }

    /**
     * 返回imageHight
     *
     * @return imageHight
     */
    public int getImageHight() {
        return imageHight;
    }

    /**
     * 设置imageHight
     *
     * @param imageHight imageHight
     */
    public void setImageHight(int imageHight) {
        this.imageHight = imageHight;
    }

    /**
     * 返回imageWith
     *
     * @return imageWith
     */
    public int getImageWith() {
        return imageWith;
    }

    /**
     * 设置imageWith
     *
     * @param imageWith imageWith
     */
    public void setImageWith(int imageWith) {
        this.imageWith = imageWith;
    }

    public boolean validate(String validcode, HttpServletRequest request) {
        ValidateCode validateCode = getGeneratedValidCode(request);
        if (validateCode != null) {
            return validateCode.getValid().equals(validcode);
        }
        return false;
    }
}
