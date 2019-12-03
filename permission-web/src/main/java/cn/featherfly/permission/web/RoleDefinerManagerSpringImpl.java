
package cn.featherfly.permission.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @SuppressWarnings("unchecked")
    @Override
    public List<RoleDefiner<Role>> getRoleDefiners() {
        List<RoleDefiner<Role>> list = new ArrayList<>();
        roleDefinerMap.values().forEach(rd -> {
            list.add(rd);
        });
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RoleDefiner<BusinessRole>> getBusinessRoleDefiners() {
        List<RoleDefiner<BusinessRole>> list = new ArrayList<>();
        roleDefinerMap.values().forEach(rd -> {
            if (rd.support(BusinessRole.class)) {
                list.add(rd);
            }
        });
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RoleDefiner<CustomRole>> getCustomRoleDefiners() {
        List<RoleDefiner<CustomRole>> list = new ArrayList<>();
        roleDefinerMap.values().forEach(rd -> {
            if (rd.support(CustomRole.class)) {
                list.add(rd);
            }
        });
        return list;
    }

}
