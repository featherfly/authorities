# 0.3.2 2022-04-14 
1. 修复common-core不兼容升级

# 0.3.1 2021-04-14

1. 修复AuthenticationKeyTokenWithRequestParamImpl, AuthenticationKeyTokenImpl解码错误
    
# 0.3.0 2020-05-22
1. 模块改名
2. 新增Checker,AuthorityChecker,AuthenticatedChecker
3. 新增LoginManagerHandlerMethodArgumentResolver,LoginActorHandlerMethodArgumentResolver
4. 兼容性依赖升级

# 0.2.0 2019-12-03
1. 拆分模块
2. 升级依赖
3. 修改group名称

# 0.1.13 2019-8-21
1. 升级依赖
2. ValidCodeAuthenticator加入generateValidCodeImage(HttpServletRequest request, HttpServletResponse response)方法
3. 加入AuthenticationToken一些列接口以及实现
4. 加入WebApplicationLoginManagerTokenImpl,PermissionInterceptor（未测试，0.2.0发布时测试完成）
    
# 0.1.12 2017-08-16
1. ApplicationLoginManager加入List<I> getLoginInfos()
    
# 0.1.10 2016-12-08 
1. ValidCodeAuthenticator加入忽略大小写设置
    
# 0.1.9 2016-11-25
1. 加入ValidCodeAuthenticator，废弃ValidateCodeAuthenticator
2. Exception继承自LocalizedException

# 0.1.8 2016-07-29
1. WebApplicationLoginManagerImpl 加入checkCrossSession属性，判断是否检查串session问题

# 0.1.7 2016-04-28 
1. request.getSession().invalidate() 加入try catch

# 0.1.5 2015-07-07 
1. AuthorizedInterceptor 加入authenticateURL参数用于设置WWW-Authenticate请求头

# 0.1.4 2015-06-19 
1. LoginInfoHandlerMethodArgumentResolver去掉抽象修饰符

# 0.1.3 2015-06-19 
1. 修复紧急BUG，打包没有漏掉了属性文件

# 0.1.2 2015-06-19
1. 修复紧急BUG

# 0.1.1 2015-06-19 
1. 修复紧急BUG

# 0.1.0 2015-06-19
1. 发布