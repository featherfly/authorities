package cn.featherfly.authorities.authentication;

import java.util.Locale;

import cn.featherfly.authorities.AuthorityException;

/**
 * <p>
 * 认证异常
 * </p>
 *
 * @author 钟冀
 */
public class AuthenticationException extends AuthorityException {

    private static final long serialVersionUID = -2456682960388084764L;

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param locale  locale
     * @param ex      异常
     */
    public AuthenticationException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     * @param ex      异常
     */
    public AuthenticationException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param ex      异常
     */
    public AuthenticationException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param ex      异常
     */
    public AuthenticationException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param locale  locale
     */
    public AuthenticationException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     */
    public AuthenticationException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     */
    public AuthenticationException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * 构造方法
     *
     * @param ex 异常
     */
    public AuthenticationException(Throwable ex) {
        super(ex);
    }
}
