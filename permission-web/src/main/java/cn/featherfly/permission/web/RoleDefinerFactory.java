
package cn.featherfly.permission.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.featherfly.permission.core.RoleDefiner;

/**
 * <p>
 * RoleDefinerFactory
 * </p>
 * <p>
 * 2019-08-22
 * </p>
 *
 * @author zhongj
 */
public class RoleDefinerFactory implements ApplicationContextAware {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.getBeansOfType(RoleDefiner.class);

    }

}
