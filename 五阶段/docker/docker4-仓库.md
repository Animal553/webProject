# Docker仓库搭建

**问题描述:**

```
在我们普通的 docker pull 的过程,都是从hub.docker.com 进行镜像的拉取。但是这个有一个问题,在公司的内部项目中如果push 上去,那么就会被其他的人看到，这个显然是不允许的。
就好比 很多公司不会把项目代码放到github上面一样,他们会在自己的内网搭建gitlab服务器
好在docker已经考虑到这一点,提供好了。
```



## Docker官方Registry

### 拉取镜像仓库

```
docker pull registry 
```

### 查看所有镜像

```
docker images
```

### 启动镜像服务器registry

首先在在主机上新建一个目录，供存储镜像

> 由于Registry是一个镜像，运行后若我们删除了容器，里面的资源就会丢失，
>
> 所以我们在运行时，指定一个资源的挂载目录，映射到宿主的一个目录下，这样资源就不会丢失了。

```
cd /usr/local/
mkdir docker_registry 
```



```
docker run -d -p 5000:5000 --name=guoweixinregistry --restart=always   -v /usr/local/docker_registry:/var/lib/registry  docker.io/registry
```

--privileged=true

> 
>
> -p 5000:5000 端口
> --name=guoweixinregistry 运行的容器名称
> --restart=always 自动重启
> --privileged=true centos7中的安全模块selinux把权限禁止了，加上这行是给容器增加执行权限
> -v /usr/local/docker_registry:/var/lib/registry 把主机的/usr/local/docker_registry 目录挂载到registry容器的/var/lib/registry目录下，假如有删除容器操作，我们的镜像也不会被删除
> docker.io/registry  镜像名称



### 测试

**从公有镜像仓库中下载一个镜像下来，或本地构建的镜像。然后push到私有仓库进行测试**

```bat
#利用tag 标记一个新镜像
docker tag exam 127.0.0.1:5000/exam

#推送像像
docker push 127.0.0.1:5000/exam
```

**此时，访问浏览器私有仓库地址：http://宿主IP:5000/v2/_catalog， 即可看见推送的镜像信息了**

<http://192.168.20.135:5000/v2/_catalog>

```
{"repositories":["exam"]}
```



### IP地址提交

直接使用127.0.0.1或者local时，是没有进行安全检验的。

当我们使用外部的ip地址推送时，Registry为了安全性考虑，默认是需要https证书支持的。

```bat
#利用tag 标记一个新镜像
docker tag demo1 192.168.20.135:5000/demo1
#通过IP地址进行push提交
docker push 192.168.20.135:5000/demo1
```

**错误提示：**

```
The push refers to repository [192.168.20.135:5000/demo1]
Get https://192.168.20.135:5000/v2/: http: server gave HTTP response to HTTPS client
```

**解决方案:**

> 有两种方案：一种是通过daemon.json配置一个insecure-registries属性；
>
> 另一种就直接配置一个https的证书了。

在 `/etc/docker/daemon.json` 中写入如下内容

```
"insecure-registries": ["实际的ip:端口"]
```

```json
{
"insecure-registries": ["192.168.20.135:5000"],
 "registry-mirrors": ["https://gxeo3yz7.mirror.aliyuncs.com"]
}
```

> insecure-registries----->开放注册https协议
> registry-mirrors----->仓库源

### **重启Docker:**

```
sudo systemctl daemon-reload
sudo systemctl restart docker
```

### 拉取私有仓库镜像

```
docker pull IP地址:端口号/镜像名称
```





## Harbor介绍及实践

docker 官方提供的私有仓库 registry，用起来虽然简单 ，但在管理的功能上存在不足。

 Harbor是一个用于存储和分发Docker镜像的企业级Registry服务器，harbor使用的是官方的docker registry(v2命名是distribution)服务去完成。

harbor在docker distribution的基础上增加了一些安全、访问控制、管理的功能以满足企业对于镜像仓库的需求。









