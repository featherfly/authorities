package cn.featherfly.permission.web.login.springmvc.interceptor;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.featherfly.common.i18n.ResourceBundleUtils;
import cn.featherfly.permission.web.login.WebApplicationLoginManager;
import cn.featherfly.web.servlet.ServletUtils;
import cn.featherfly.web.spring.servlet.view.Result;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthorizedInterceptor implements HandlerInterceptor {

    private WebApplicationLoginManager<?, ?> applicationLoginManager;
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    private Collection<String> excludes = new HashSet<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    private String charset = "UTF-8";

    public AuthorizedInterceptor() {
    }

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     *
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Result<?> result = new Result<Object>();

        String uri = ServletUtils.getRequestURI(request);
        logger.debug("uri -> {}", uri);
        boolean exclude = false;
        for (String excludeUri : excludes) {
//			if (uri.matches(excludeUri)) {
            if (antPathMatcher.match(excludeUri, uri)) {
                exclude = true;
                break;
            }
        }
        if (!exclude) {
            if (!applicationLoginManager.isLogin(request)) {
                result.setMessage(ResourceBundleUtils.getString("@permission#session.invalidation"));
                request.getSession().invalidate();
                if (request.getHeader("Accept").contains("application/json")) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    render(response, result);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, result.getMessage());
                }
                return false;
            }
        }
        return true;
    }

    // 在业务处理器处理请求执行完成后,生成视图之前执行的动作
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    private void render(HttpServletResponse response, Object result) {
        try {
            response.setContentType("application/json;charset="+charset);
            response.setCharacterEncoding(charset);
            objectMapper.setSerializationInclusion(Include.NON_EMPTY)
                    .writerFor(result.getClass())
                    .writeValue(response.getOutputStream(), result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    /**
     * 设置excludeURI
     * @param excludes excludeURI
     */
    public void setExcludes(Collection<String> excludes) {
        this.excludes = excludes;
    }

    /**
     * 设置charset
     * @param charset charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * 设置applicationLoginManager
     * @param applicationLoginManager applicationLoginManager
     */
    public void setApplicationLoginManager(
            WebApplicationLoginManager<?, ?> applicationLoginManager) {
        this.applicationLoginManager = applicationLoginManager;
    }
}
