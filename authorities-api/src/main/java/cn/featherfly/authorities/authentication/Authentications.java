
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Authentications.java
 * @Package cn.featherfly.authorities.authentication
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2022-04-22 18:14:22
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.authorities.authentication;

/**
 * Authentications.
 *
 * @author zhongj
 */
public enum Authentications implements Authentication {
    /**
     * 用户名密码
     */
    USERNAME_PASSWORD,
    /**
     * 手机短信（语音）验证码
     */
    MOBILE_VALIDCODE,
    /**
     * 邮件验证码
     */
    EMAIL_VALIDCODE,
    /**
     * 微信登录
     */
    WECHAT,
    /**
     * 支付宝登录
     */
    ALIPAY;

}
