package cn.featherfly.authorities.web.servlet.filter;

import java.io.IOException;
import java.util.function.Consumer;

import cn.featherfly.authorities.web.WebAuthorityChecker;
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
 * AuthorityCheckerFilter.
 *
 * @author zhongj
 */
public class AuthorityCheckerFilter implements Filter {

    /** The Constant EXCLUDES_NAME. */
    public static final String EXCLUDES_NAME = "authority.excludes";

    /** The Constant CHARSET_NAME. */
    public static final String CHARSET_NAME = "authority.charset";

    /** The Constant AUTO_REDIRECT. */
    public static final String AUTO_REDIRECT = "authority.autoRedirect";

    /** The Constant REDIRECT_URL. */
    public static final String REDIRECT_URL = "authority.redirectURL";

    private WebAuthorityChecker authorityChecker;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authorityChecker = new WebAuthorityChecker();
        authorityChecker.setAutoRedirect(false);
        authorityChecker.setRedirectURL("");
        Lang.ifNotEmpty(filterConfig.getInitParameter(REDIRECT_URL), authorityChecker::setRedirectURL);
        Lang.ifNotEmpty(filterConfig.getInitParameter(CHARSET_NAME), authorityChecker::setCharset);
        Lang.ifNotEmpty(filterConfig.getInitParameter(EXCLUDES_NAME),
            (Consumer<String>) n -> authorityChecker.setExcludes(ArrayUtils.toList(n.split(","))));
        Lang.ifNotEmpty(filterConfig.getInitParameter(AUTO_REDIRECT),
            (Consumer<String>) n -> authorityChecker.setAutoRedirect(Boolean.parseBoolean(n)));
        // TODO 未实现
        authorityChecker.setLoginManager(null);
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        if (authorityChecker.check(new WebEnv((HttpServletRequest) request, (HttpServletResponse) response))) {
            chain.doFilter(request, response);
        }
    }

}
