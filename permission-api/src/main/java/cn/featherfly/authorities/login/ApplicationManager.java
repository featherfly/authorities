package cn.featherfly.authorities.login;

import java.util.List;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.Application;

/**
 * <p>
 * 应用程序管理器
 * </p>
 *
 * @param <E> 泛型，用于具体依赖运行环境的定义
 * @author 钟冀
 */
public interface ApplicationManager<E, L extends LoginManager<E, I, A>, I extends LoginInfo<A>,
        A extends Actor> {
    /**
     * getLoginManager
     *
     * @param environment
     * @return
     */
    L getLoginManager(E environment);

    /**
     * getLoginManager
     *
     * @param application
     * @return
     */
    L getLoginManager(Application application);

    /**
     * getApplications
     *
     * @return
     */
    List<Application> getApplications();
}
