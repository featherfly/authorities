package cn.featherfly.authorities.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AuthorizedInterceptor.
 *
 * @author 钟冀
 */
public class WebEnv {

    private HttpServletRequest request;

    private HttpServletResponse response;

    /**
     * Instantiates a new web env.
     */
    public WebEnv() {
    }

    /**
     * Instantiates a new web env.
     *
     * @param request  the request
     * @param response the response
     */
    public WebEnv(HttpServletRequest request, HttpServletResponse response) {
        super();
        this.request = request;
        this.response = response;
    }

    /**
     * 返回request.
     *
     * @return request
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 设置request.
     *
     * @param request request
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 返回response.
     *
     * @return response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * 设置response.
     *
     * @param response response
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
