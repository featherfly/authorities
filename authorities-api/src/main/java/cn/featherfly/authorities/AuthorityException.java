package cn.featherfly.authorities;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * AuthorityException.
 *
 * @author 钟冀
 */
public class AuthorityException extends LocalizedException {

    private static final long serialVersionUID = 1763928344130473684L;

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param locale  locale
     * @param ex      异常
     */
    public AuthorityException(String message, Object[] argus, Locale locale, Throwable ex) {
        super(message, argus, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     * @param ex      异常
     */
    public AuthorityException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param ex      异常
     */
    public AuthorityException(String message, Object[] argus, Throwable ex) {
        super(message, argus, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param ex      异常
     */
    public AuthorityException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     * @param locale  locale
     */
    public AuthorityException(String message, Object[] argus, Locale locale) {
        super(message, argus, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param argus   信息绑定参数
     */
    public AuthorityException(String message, Object[] argus) {
        super(message, argus);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     * @param locale  locale
     */
    public AuthorityException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * 构造方法
     *
     * @param message 信息
     */
    public AuthorityException(String message) {
        super(message);
    }

    /**
     * 构造方法
     *
     * @param ex 异常
     */
    public AuthorityException(Throwable ex) {
        super(ex);
    }

}
