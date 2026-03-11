package cn.featherfly.authorities.web.servlet.filter;

import java.io.IOException;
import java.util.function.Consumer;

import cn.featherfly.authorities.web.WebAuthenticatedChecker;
import cn.featherfly.authorities.web.WebEnv;
import cn.featherfly.common.exception.NotImplementedException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Lang;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * AuthenticatedCheckerFilter.
 *
 * @author zhongj
 */
public class AuthenticatedCheckerFilter implements Filter {

    /** The Constant AUTHENTICATEURL_NAME. */
    public static final String AUTHENTICATEURL_NAME = "authenticated.authenticateURL";

    /** The Constant EXCLUDES_NAME. */
    public static final String EXCLUDES_NAME = "authenticated.excludes";

    /** The Constant CHARSET_NAME. */
    public static final String CHARSET_NAME = "authenticated.charset";

    private WebAuthenticatedChecker authenticatedChecker;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authenticatedChecker = new WebAuthenticatedChecker();
        Lang.ifNotEmpty(filterConfig.getInitParameter(AUTHENTICATEURL_NAME), authenticatedChecker::setAuthenticateURL);
        Lang.ifNotEmpty(filterConfig.getInitParameter(EXCLUDES_NAME),
            (Consumer<String>) n -> authenticatedChecker.setExcludes(ArrayUtils.toList(n.split(","))));
        Lang.ifNotEmpty(filterConfig.getInitParameter(CHARSET_NAME), authenticatedChecker::setCharset);

        // TODO 未实现
        authenticatedChecker.setLoginManager(null);
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        if (authenticatedChecker.check(new WebEnv((HttpServletRequest) request, (HttpServletResponse) response))) {
            chain.doFilter(request, response);
        }
    }

}
