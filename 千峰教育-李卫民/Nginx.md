# 一、Nginx介绍

## 1.1 引言

> 为什么要学习Nginx
>
> 问题1：客户端到底要将请求发送到哪台服务器。
>
> 问题2：如果所有客户端的请求都发送给了服务器1.
>
> 问题3：客户端发送的请求可能是申请动态资源的，也有申请静态资源。

```
服务器搭建集群后：
```

![image-20201117152211761](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201117152211761.png)

```
在搭建集群后，使用Nginx做反向代理服务器
```

![image-20201117152545597](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201117152545597.png)

## 1.2 Nginx介绍

> Nginx是由俄罗斯人研发的，应对Rambler的网站，并且2004年发布了第一个版本

![image-20201117153427103](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201117153427103.png)

> Nginx的特点：
>
> ​    1.稳定性极强。 7*24小时不间断运行
>
>     2.  Nginx提供了非常丰富的配置实例
>        3.  占用内存小，并发能力强

# 二、Nginx的安装

## 2.1 安装Nginx

```yml
version: '3.1'
services: 
  nginx: 
    restart: always
    image: nginx
    container_name: nginx
    ports: 
      - 80:80
```

## 2.2 Nginx的配置文件

> 关于Nginx的核心配置文件nginx.conf

```json
worker_processes  1;

error_log  /var/log/nginx/error.log warn;

# 以上统称为全局块
# work_processes他的数值越大，Nginx的并发能力就越强
# error_log 代表Nginx的错误日志存放的位置


events {
    worker_connections  1024;
}
# event块
# worker_connections他越大，Nginx并发能力越强



http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    #include /etc/nginx/conf.d/*.conf;
    server {
        listen       80;
        listen  [::]:80;
        server_name  localhost;

    
         location / {
            root   /usr/share/nginx/html;
            index  index.html index.htm;
         }
         # location块
         # root：将接送到的请求根据/usr/share/nginx/html去寻找静态资源
         # index: 默认去上述的路径中找到index.html或者index.htm
         


    }
    # server块
    # listen：代表Nginx监听的端口号
    # localhost: 代表Nginx接收请求的ip

}

# http块 
# include代表引入一个外部文件 -> /mime.types中放着大量的媒体类型
# include /etc/nginx/conf.d/*.conf; -> 引入了conf.d目录下以.conf为结尾的配置文件

```

## 2.3 修改docker-compose文件

```sh
version: '3.1'
services: 
  nginx: 
    restart: always
    image: nginx
    container_name: nginx
    ports: 
      - 80:80
    volumes: 
      - /usr/local/docker/nginx/conf.d/:/etc/nginx/conf.d
```

# 三. Nginx的反向代理

## 3.1 正向代理和反向代理的说明

> 正向代理：
>
>     1. 正向代理服务时由客户端设立的。
>        2. 客户端了解代理服务器和目标服务器都是谁。
>        3. 帮助咱们实现突破访问权限，提高访问的速度，对目标服务器隐藏客户端的ip地址。

![image-20201122101218709](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201122101218709.png)



> 反向代理：
>
> 1. 反向代理服务器是配置在服务端的。
> 2. 客户端是不知道访问的到底是哪一台服务器。
> 3. 达到负载均衡，并且可以隐藏服务器真正的ip地址。

![image-20201122101718745](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201122101718745.png)

## 3.2 基于Nginx实现反向代理

> 准备一个目标服务器。
>
> 启动了之前的tomcat服务器。
>
> 编写nginx的配置文件，通过Nginx访问到tomcat服务器.



```json
server{
  listen 80;
  server_name localhost;
  # 基于反向代理访问到Tomcat服务器
  location / {
    proxy_pass http://192.168.190.129:8080/;
  }
}
```



## 3.3关于Nginx的location路径映射

> 优先级关系
>
> (location = ) > (location /xxx/yyy/zzz) > (location ^~) > (loaction ~,~*) > (location /起始路径) > (loaction /)

```json
# 1. = 匹配
location = / {
  # 精准匹配，主机名后面不能带任何的字符串
}
```

--------

```json
# 2. 通用匹配
location /xxx {
  # 匹配所有以/xxx开头的路径
}
```

---------------

```json
# 3. 正则匹配
location ~ /xxx {
   #匹配所有以/xxx开头的路径
}
```

--------------------

```json
# 4. 匹配开头路径
location ^~ /images/ {
   # 匹配所有以images开头的路径
}
```

----------------

```json
# 5. ~* \.(gif|jpg|png)$ {
   # 匹配以gif或者jpg或者png为结尾的路径
}
```

# 四. Nginx负载均衡

> Nginx为我们默认提供了三种负载均衡的策略：
>
>     1. 轮询：
>
> ​        将客户端发起的请求，平均的分配给每一台服务器。
>
> 2. 权重： 
>
> ​        会将客户端的请求，根据服务器的权重值不同，分配不同的数量。
>
> 3. ip hash: 
>
> ​       基于发起请求的客户端的ip地址不同，他始终会将请求发送到指定的服务器上。

--------------

![image-20201126145330016](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201126145330016.png)



## 4.1 轮询

> 想实现Nginx轮询负载均衡机制只需要在配置文件中添加以下内容

-----------

```json
upstream 名字 {
  server ip:port;
  server ip:port;
  ...
}
例:
upstream my-server {
  server 192.168.190.129:8080;
  server 192.168.190.129:8081;
}

server {
  listen 80;
  server_name localhost;
  
  location / {
     proxy_pass http://名字/;
  }
  例:
  location / {
     proxy_pass http://my-server/;
  }
  
}

```

--------------

## 4.2 权重

> 实现权重的方式

```json
upstream 名字 {
  server ip:port weight=权重比例;
  server ip:port weight=权重比例;
  ...
}
例:
upstream my-server {
  server 192.168.190.129:8080 weight=10;
  server 192.168.190.129:8081 weight=2;
}

server {
  listen 80;
  server_name localhost;

  location / {
     proxy_pass http://名字/;
  }
  例:
  location / {
     proxy_pass http://my-server/;
  }

}
```

## 4.3 ip_hash

> 实现ip_hash的方式

```
upstream 名字 {
  ip_hash;
  server ip:port;
  server ip:port;
  ...
}
例:
upstream my-server {
  server 192.168.190.129:8080;
  server 192.168.190.129:8081;
}

server {
  listen 80;
  server_name localhost;

  location / {
     proxy_pass http://名字/;
  }
  例:
  location / {
     proxy_pass http://my-server/;
  }

}
```

# 五. 动静分离

> Nginx的并发能力公式：
>
> ​    work_processes *  worker_connections / 4|2 =Nginx的最终的并发能力
>
> 动态资源需要/4，静态资源需要/2.
>
> Nginx通过动静分离，来提升Nginx的并发能力，更快的给用户响应

--------------------

动态资源：

![image-20201126151814880](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201126151814880.png)



静态资源：

![image-20201126151909445](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201126151909445.png)



## 5.1 动态资源代理

```
# 配置如下
location / {
  proxy_pass 路径;
}
```

## 5.2 静态资源代理

```json
# 配置如下
loaction / {
  root 静态资源路径：
  index 默认访问路径下的什么资源;
  autoindex on; #代表展示静态资源的全部内容，以列表的形式展开。
}

# 先修改docker,添加一个数据卷，映射到Nginx服务器的一个目录
# 添加了index.html和1.jpg静态资源
# 修改配置文件
```

# 六. Nginx集群

## 6.1 引言

> 单点故障，避免nginx的宕机，导致整个程序的崩溃
>
> 准备多台Nginx。
>
> 准备keepalived,监听nginx的健康情况。
>
> 准备haproxy,提供一个虚拟的路径，统一的去接收用户的请求。

-------------------------------



![image-20201126154538485](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201126154538485.png)



## 6.2 搭建集群操作