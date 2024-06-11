
package cn.featherfly.authorities.web.authentication;

import jakarta.servlet.http.HttpServletRequest;

/**
 * AuthenticationTokenFactory.
 *
 * @author zhongj
 */
public interface AuthenticationTokenFactory {

    /**
     * Creates the.
     *
     * @param request the request
     * @return the authentication token
     */
    AuthenticationToken create(HttpServletRequest request);

}