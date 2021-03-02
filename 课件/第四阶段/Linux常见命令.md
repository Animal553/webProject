# Linux常见命令

* 安装ifconfig
```
yum search ifconfig
yum install net-tools.x86_64
```
* 安装vim
```
yum install vim
```
* 安装wget
```
yum install wget
```
* 安装rz(文件上传)
```
yum install lrzsz
```
* 查看防火墙状态
```
systemctl status firewalld
firewall-cmd --state
```
* 开启/关闭防火墙
```
# 开启
service firewalld start
# 重启
service firewalld restart
# 关闭
service firewalld stop
```
* 查询/开放/关闭端口
```
# 查询端口是否开放
firewall-cmd --query-port=8080/tcp
# 开放80端口
firewall-cmd --permanent --add-port=80/tcp
# 移除端口
firewall-cmd --permanent --remove-port=8080/tcp

#重启防火墙(修改配置后要重启防火墙)
firewall-cmd --reload

# 参数解释
1、firwall-cmd：是Linux提供的操作firewall的一个工具；
2、--permanent：表示设置为持久；
3、--add-port：标识添加的端口；
```

---
* 添加用户和群组
```
1. 创建用户组
    groupadd hs
2. 将用户添加到组中
    useradd -g  hs hs
3. 切换用户
    su -hs
```