package cn.featherfly.authorities.login;

import java.util.List;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.Application;

/**
 * <p>
 * 应用程序管理器
 * </p>
 * .
 *
 * @author 钟冀
 * @param <E> 泛型，用于具体依赖运行环境的定义
 * @param <L> the generic type
 * @param <I> the generic type
 * @param <A> the generic type
 */
public interface ApplicationManager<E, L extends LoginManager<E, I, A>, I extends LoginInfo<A>, A extends Actor> {

    /**
     * getLoginManager.
     *
     * @param environment the environment
     * @return the login manager
     */
    L getLoginManager(E environment);

    /**
     * getLoginManager.
     *
     * @param application the application
     * @return the login manager
     */
    L getLoginManager(Application application);

    /**
     * getApplications.
     *
     * @return the applications
     */
    List<Application> getApplications();
}
