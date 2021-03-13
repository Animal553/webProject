# Docker图形化工具

​		  docker 图形页面管理工具常用的有三种，DockerUI ，**Portainer** ，Shipyard 。DockerUI 是 Portainer 的前身，这三个工具通过docker api来获取管理的资源信息。平时我们常常对着shell对着这些命令行客户端，审美会很疲劳，如果有漂亮的图形化界面可以直观查看docker资源信息，也是非常方便的。今天我们就搭建单机版的三种常用图形页面管理工具。这三种图形化管理工具以**Portainer**最为受欢迎。



## 1.查看portainer镜像

```
docker search portainer
```

## 2.portainer镜像下载

```
docker pull portainer/portainer 
```

## 3.启动dockerui容器

```
docker run -d --name portainerUI -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock portainer/portainer
```

## 4.浏览器访问 

<http://106.15.239.153:9000/>

![](img-portainer6\1.jpg)

 设置一个密码即可，点击创建用户



## 5.单机版Docker，直接选择Local ，点击连接

![](img-portainer6\2.jpg)



## 6.使用即可

![](img-portainer6\3.jpg)