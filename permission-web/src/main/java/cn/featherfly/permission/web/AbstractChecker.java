
package cn.featherfly.permission.web;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.featherfly.permission.AuthorityChecker;
import cn.featherfly.permission.AuthorityException;
import cn.featherfly.web.servlet.ServletUtils;

/**
 * <p>
 * WebAuthorityChecker
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractChecker implements AuthorityChecker<WebEnv> {

    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    protected String charset = "UTF-8";

    protected Collection<String> excludes = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(WebEnv env) {
        HttpServletRequest request = env.getRequest();
        HttpServletResponse response = env.getResponse();
        String uri = ServletUtils.getRequestURI(request);
        logger.debug("uri -> {}", uri);
        boolean exclude = false;
        for (String excludeUri : excludes) {
            if (antPathMatcher.match(excludeUri, uri)) {
                exclude = true;
                break;
            }
        }

        if (!exclude) {
            return doCheck(request, response, uri);
        }
        return true;
    }

    protected void render(HttpServletResponse response, Object result) {
        try {
            response.setContentType("application/json;charset=" + charset);
            response.setCharacterEncoding(charset);
            objectMapper.setSerializationInclusion(Include.NON_EMPTY).writerFor(result.getClass())
                    .writeValue(response.getOutputStream(), result);
        } catch (IOException e) {
            throw new AuthorityException(e);
        }
    }

    /**
     * doCheck
     *
     * @param request  request
     * @param response response
     * @return check result
     */
    protected abstract boolean doCheck(HttpServletRequest request, HttpServletResponse response, String uri);

    /**
     * 返回charset
     *
     * @return charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * 设置charset
     *
     * @param charset charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * 返回excludes
     *
     * @return excludes
     */
    public Collection<String> getExcludes() {
        return excludes;
    }

    /**
     * 设置excludes
     *
     * @param excludes excludes
     */
    public void setExcludes(Collection<String> excludes) {
        this.excludes = excludes;
    }
}
