package com.qianfeng.entity;

public class Role {
     private Integer roleId;
     private String roleName;
     private String status;

    public Role() {
    }

    public Role(Integer roleId, String roleName, String status) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.status = status;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
