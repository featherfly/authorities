
package cn.featherfly.permission.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.featherfly.permission.BusinessRole;
import cn.featherfly.permission.CustomRole;
import cn.featherfly.permission.Role;
import cn.featherfly.permission.RoleDefiner;
import cn.featherfly.permission.RoleDefinerManager;

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
public class RoleDefinerManagerSpringImpl implements RoleDefinerManager, ApplicationContextAware {

    @SuppressWarnings("rawtypes")
    private Map<String, RoleDefiner> roleDefinerMap;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        roleDefinerMap = applicationContext.getBeansOfType(RoleDefiner.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoleDefiner<Role>> getRoleDefiners() {
        return roleDefinerMap.values().stream().collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<RoleDefiner<BusinessRole>> getBusinessRoleDefiners() {
        return roleDefinerMap.values().stream().filter(rd -> {
            return rd.support(BusinessRole.class);
        }).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RoleDefiner<CustomRole>> getCustomRoleDefiners() {
        return roleDefinerMap.values().stream().filter(rd -> {
            return rd.support(CustomRole.class);
        }).collect(Collectors.toList());
    }

}
