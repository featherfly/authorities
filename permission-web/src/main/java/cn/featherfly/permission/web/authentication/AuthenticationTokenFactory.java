
package cn.featherfly.permission.web.authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * AuthenticationTokenFactory
 * </p>
 * <p>
 * 2019-08-21
 * </p>
 * @author zhongj
 */
public interface AuthenticationTokenFactory {

    AuthenticationToken create(HttpServletRequest request);

}