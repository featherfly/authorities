//
//package cn.featherfly.permission.web.servlet.filter;
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
//import cn.featherfly.permission.web.WebAuthorityChecker;
//import cn.featherfly.permission.web.WebEnv;
//
///**
// * <p>
// * AuthorityCheckerFilter
// * </p>
// *
// * @author zhongj
// */
//public class AuthorityCheckerFilter implements Filter {
//
//    private static final String AUTHENTICATEURL_NAME = "authorizer.authenticateURL";
//    private static final String EXCLUDES_NAME = "authorizer.excludes";
//    private static final String CHARSET_NAME = "authorizer.charset";
//
//    private WebAuthorityChecker authorityChecker;
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        //        authorityChecker = new WebAuthorityChecker();
//        //        isNotEmpty(filterConfig.getInitParameter(AUTHENTICATEURL_NAME),
//        //                n -> authorizer.setAuthenticateURL(filterConfig.getInitParameter(AUTHENTICATEURL_NAME)));
//        //        isNotEmpty(filterConfig.getInitParameter(EXCLUDES_NAME),
//        //                n -> authorizer.setExcludes(ArrayUtils.toList(n.split(","))));
//        //        isNotEmpty(filterConfig.getInitParameter(CHARSET_NAME), n -> authorizer.setCharset(n));
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
//        if (authorizer.check(new WebEnv((HttpServletRequest) request, (HttpServletResponse) response))) {
//            chain.doFilter(request, response);
//        }
//    }
//
//}
