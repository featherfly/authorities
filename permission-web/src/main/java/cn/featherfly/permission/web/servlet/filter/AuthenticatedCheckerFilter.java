//
//package cn.featherfly.permission.web.servlet.filter;
//
//import static cn.featherfly.common.lang.LangUtils.isNotEmpty;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import cn.featherfly.common.lang.ArrayUtils;
//import cn.featherfly.permission.web.WebAuthenticatedChecker;
//import cn.featherfly.permission.web.WebEnv;
//
///**
// * <p>
// * AuthenticatedCheckerFilter
// * </p>
// *
// * @author zhongj
// */
//public class AuthenticatedCheckerFilter implements Filter {
//
//    private static final String AUTHENTICATEURL_NAME = "authenticated.authenticateURL";
//    private static final String EXCLUDES_NAME = "authenticated.excludes";
//    private static final String CHARSET_NAME = "authenticated.charset";
//
//    private WebAuthenticatedChecker authenticatedChecker;
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        authenticatedChecker = new WebAuthenticatedChecker();
//        isNotEmpty(filterConfig.getInitParameter(AUTHENTICATEURL_NAME),
//                n -> authenticatedChecker.setAuthenticateURL(filterConfig.getInitParameter(AUTHENTICATEURL_NAME)));
//        isNotEmpty(filterConfig.getInitParameter(EXCLUDES_NAME),
//                n -> authenticatedChecker.setExcludes(ArrayUtils.toList(n.split(","))));
//        isNotEmpty(filterConfig.getInitParameter(CHARSET_NAME), n -> authenticatedChecker.setCharset(n));
//        // TODO 使用constant配置
//        //        authorizer.setApplicationLoginManager(applicationLoginManager);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        if (authenticatedChecker.check(new WebEnv((HttpServletRequest) request, (HttpServletResponse) response))) {
//            chain.doFilter(request, response);
//        }
//    }
//
//}
