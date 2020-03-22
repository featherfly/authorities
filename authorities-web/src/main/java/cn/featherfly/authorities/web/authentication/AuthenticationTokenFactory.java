
package cn.featherfly.authorities.web.authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * AuthenticationTokenFactory
 * </p>
 * 
 * @author zhongj
 */
public interface AuthenticationTokenFactory {

    AuthenticationToken create(HttpServletRequest request);

}