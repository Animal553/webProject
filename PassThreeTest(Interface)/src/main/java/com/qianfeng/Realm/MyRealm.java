package com.qianfeng.Realm;

import com.qianfeng.entity.Employee;
import com.qianfeng.mapper.EmployeeMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class MyRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        String empId = principalCollection.getPrimaryPrincipal().toString();
        Employee employee = employeeMapper.get_EmployeeById(Integer.parseInt(empId));
        info.addRole(employee.getRole().getRoleName());

        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String principal = authenticationToken.getPrincipal().toString();

        Employee employee = employeeMapper.get_EmployeeById(Integer.parseInt(principal));
        System.out.println(employee);
        if (employee==null){
            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee.getEmpId().toString(), employee.getEmpPassword(), getName());
        return info;
    }
}
