# 一.Docker介绍

##       1.1 引言

> 1. 我本地运行没问题呀
>
> ​       环境不一致
>
> 2. 哪个哥们又写死循环了，怎么这么卡
>
> ​        在多用户的操作系统下，会互相影响。
>
> 3. 淘宝在双11的时候，用户量暴增
>
> ​       运维成本过高的问题
>
> 4. 学习一门技术，学习安装成本过高
>
> ​       关于安装软件成本过高

## 1.2 Docker的由来

> 一帮年轻人创业，创办了一家公司，2010年的专门做PAAS平台。
>
> 到了2013年的时候，像亚马逊，微软，Google都开始做PAAS平台。
>
> 2013年，将公司内的核心技术对外开源，核心技术就是Docker.
>
> 到了2014年的时候，得到了C轮的融资.$4000W
>
> 到了2015年的时候，得到了D轮的融资.$9500W
>
> 然后就全身贯注的维护Docker。
>
> 所罗门的主要作者之一

![image-20201112134141585](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201112134141585.png)

Docker的作者已经离开了维护Docker。

## 1.3Docker的思想

> 1. 集装箱：
>
> ​      会将所有需要的内容放到不同的集装箱中，谁需要这些环境就直接拿到这个集装箱就可以了
>
> 2. 标准化：
>
> ​           2.1. 运输的标准化：Docker有一个码头，所有上传的集装箱都放在这个码头上，当谁需要某一个环境，就直接指派罗濂洁去搬运这个集装箱就可以了.
>
> ​          2.2. 命令的标准化：Docker提供了一系列的命令，帮助我们去获取集装箱等操作
>
> ​          2.3. 提供了REST的API: 衍生出了很多图形化界面，Rancher.
>
> 3. 隔离性：
>
> ​       Docker在运行集装箱内的内容时，会在Linux的内核中，单独开辟一片空间，这片空间不会影响到其他程序.

>   注册中心。（超级码头，上面放的就是集装箱）
>
>   镜像。（集装箱）
>
>   容器。（运行的镜像）

# 二.Docker的基本操作

## 2.1 安装Docker

> 1. 下载关于Docker的依赖环境
> 2. 设置一下下载Docker的镜像源
> 3. 安装Doctor
> 4. 启动，并设置开启自启动，测试
>
> ​       4.1. 启动Docker服务
>
> ​              sysetmctl start docker
>
> ​       4.2. 设置开机自动启动
>
> ​              systemctl enable docker
>
> ​       4.3. 测试
>
> ​              docker run hello-world

## 2.2Docker的中央仓库

> 1. Docker官方的中央仓库 ：这个仓库是镜像最全的，但是下载速度较慢
>
> ​       https://hub.docker.com/
>
> 2. 国内的镜像网站：网易蜂巢，daocloud
>
> ​       https://c.163yun.com/hub#/home
>
> ​       https://hub.daocloud.io (推荐使用)
>
> 3. 在公司内部会采用私服的方式拉取镜像。 (添加配置)
>
> ​      #需要在/etc/docker/daemon.json
>
> ​       {
> ​            "registry-mirrors":["https://registry.docker-cn.com"],
>
> ​            "insecure-registries":["ip:port"]
>
> ​       }
>
> ​       #重启两个服务
>
> ​        systemctl daemon-reload
>
> ​        systemctl restart docker

## 2.3 镜像的操作

> 1. 拉取镜像到本地
>
> docker pull 镜像名称[:tag]
>
>    举个例子
>
> docker pull daocloud.io/library/tomcat:8.5.15-jre8

> 2. 查看全部本地镜像
>
> ​     docker images
>
> 3. 删除本地镜像
>
> ​     docker rmi  镜像的标识

>        4. 镜像的导入导出（不规范）
>
> ​        4.1 将本地的镜像导出
>
> ​              docker save -o 导出的路径 镜像id
>
> ​        4.2 加载本地的镜像文件
>
> ​               docker load -i 镜像文件
>
> ​        4.3 修改镜像名称
>
> ​               docker tag 镜像id 新镜像名称:版本

## 2.4 容器的操作

> 1.  运行容器
>
> ​    #简单操作
>
> ​    docker run 镜像的标识 镜像名称[:tag]
>
> ​    #常用的参数
>
> ​     docker run -d -p 宿主机端口:容器端口 --name 容器名称 镜像的标识|镜像名称[:tag]
>
> ​    #-d: 代表后台运行容器
>
> ​    #-p 宿主机端口:容器端口: 为了映射当前Linux的端口和容器的端口
>
> ​    #--name 容器名称：指定容器的名称

-------------------------------

>         2. 查看正在运行的容器
>
> ​       docker ps [-qa]
>
> ​       #-a :查看全部的容器，包括没有运行
>
> ​       #-q: 只查看容器得到标识

--------------

>         3. 查看容器的日志
>
> ​        docker logs -f 容器id
>
> ​        #-f: 可以滚动查看日志的最后几行

-------------

> 4. 进入到容器内部
>
> ​        docker exec -it 容器id bash

----------

> 5. 删除容器（删除容器前，需要先停止容器）
>
> ​       docker stop 容器id
>
> ​      #停止指定的容器 
>
> ​       docker stop $(docker ps -qa)
>
> ​     #停止全部容器
>
> ​       docker rm 容器id
>
> ​     #删除指定容器
>
> ​       docker rm $(docker ps -qa)
>
> ​     #删除全部容器

-----------

>       6. 启动容器
>
> ​        docker start 容器id



# 三.Docker应用

## 3.1准备好SSM工程

> #MySQL数据库的连接用户名和密码改变了，修改book.properties

## 3.2准备MySQL容器

> #运行MySQL容器
>
> ​       docker run -d -p 3306:3306 --name mysql  -e MYSQL_PASSWORD=root 镜像id

----------

## 3.3准备Tomcat容器

```sh
#运行Tomcat容器，前面已经搞定，只需要SSM项目的war包部署到Tomcat容器中即可
#可以通过命令将宿主机的内容服务到容器内部
docker cp 文件名称 容器id:容器内部路径
#举个例子
docker cp ssm.war 7aaca3e426fb:/usr/local/tomcat/webapps
```

## 3.4数据卷

> 为了部署SSM的项目，需要使用到cp的命令将宿主机内的ssm.war文件复制到容器内部
>
> 数据卷：将宿主机的一个目录映射到容器的一个目录中。
>
> 可以在宿主机中操作目录中的内容，那么容器内部映射的文件，也会跟着一起改变

--------

>  #1.创建数据卷
>
> docker volume create 数据卷名称
>
> #创建数据卷之后，默认会存放在一个目录下 /var/lib/docker/volumes/数据卷名称/_data

-----------

>  #2 查看数据卷的详细信息
>
> ​     docker volume inspect 数据卷名称

---------------------------

>   #3. 查看全部数据卷
>
> ​     docker volume is

--------------------

>  #4 删除数据卷
>
> docker volume rm 数据卷名称

-----------------

> #5. 应用数据卷
>
> #当你映射数据卷时，如果数据卷不存在，Docker会帮你自动创建,会将容器内部自带的文件，存储在默认的存放路径中。
>
> docker run -v 数据卷名称:容器内部的路径 镜像id
>
> #直接指定一个路径作为数据卷的存放位置，这个路径下是空的
>
> docker run -v 路径:容器内部的路径 镜像id

# 四.Docker自定义镜像

> 中央仓库上的镜像，也是DOcker的用户直接上传过去的
>
> #1. 创建一个Dockerfile文件，并且指定自定义镜像信息
>
> #Dockerfile文件中常用的内容
>
> from: 指定当前自定义镜像依赖的环境
>
> copy: 将相对路径下的内容复制到自定义镜像中
>
> workdir: 声明镜像的默认工作目录
>
> cmd：需要执行的命令（在workdir下执行的，cmd可以写多个，但只以最后一个为准）
>
> #举个例子，自定义一个tomcat镜像，并且将ssm。war部署到tomcat中
>
> from tomcat:9.0.27
>
> copy ssm.war /usr/local/tomcat/webapps/ROOT

# 五.Docker-Compose

> 之前运行一个镜像，需要添加大量的参数。
>
> 可以通过Docker-Compose编写这些参数。
>
> Docker-Compose可以帮助我们批量的管理容器
>
> 只需要通过一个docker-compose.yml文件去维护即可

##  5.1 下载Docker-Compose 

> #1.去github官网上搜索docker-compose
>
> https://github.com/docker/compose/releases
>
> #2. 将下载好的文件，拖拽到LInux操作系统中
>
> #3. 需要将DockerCompose文件的名称修改一下，给予DockerCompose文件一个可执行的权限
>
> chmod 777 docker-compose
>
> #4 方便后期操作，配置一个环境变量
>
> #将docker-compose文件移动到了/usr/local/bin
>
> #5在任意目录下执行docker-compose命令
>
> ![image-20201113154456571](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113154456571.png)

------------------------

## 5.2 Docker-Compose管理MySQL和Tomcat容器

> yml文件以key: value方式来指定配置信息
>
> 多个配置信息以换行+缩进的方式来区分
>
> 在docker-compose.yml文件中，不要使用制表符tab键

------

```yml
version: '3.1'
services:
  mysql:                 #服务的名称
    restart: always      #代表只要docker启动，那么这个容器就跟着一起启动 
    image: mysql:5.7.22 #指定镜像路径
    container_name: mysql #指定容器名称
    ports:                
      - 3306:3306         #指定端口号的映射
    environment: 
      MYSQL_ROOT_PASSWORD: 123456   #指定MySQL的ROOT用户登录密码
      TZ: Asia/Shanghai           #指定时区
    volumes: 
      - /opt/docker_mysql_tomcat/mysql_data:/var/lib/mysql    #映射数据卷
  tomcat:
    restart: always
    image: tomcat:9.0.27
    container_name: tomcat
    ports: 
      - 8080:8080
    environment: 
      TZ: Asia/Shanghai
    volumes: 
      - /opt/docker_mysql_tomcat/tomcat_webapps:/usr/local/tomcat/webapps
      - /opt/docker_mysql_tomcat/tomcat_logs:/usr/local/tomcat/logs
```

## 5.3 使用docker-compose命令管理容器

> 在使用docker-compose的命令时，默认会在当前目录下去找docker-compose.yml文件

```sh
#1 基于docker-compose.yml启动管理的容器

docker-compose up -d
```

------

```sh
#2 关闭并删除容器

docker-compose down
```

------

```sh
#3 开启|关闭|重启已经存在的由docker-compose维护的容器

docker-compose start|stop|restart
```

---------------

```sh
#4 查看由docker-compose管理的容器

docker-compose ps
```

--------------------

```sh
#5 查看日志

docker-compose logs -f
```

## 5.4 docker-compose配置Dockerfile使用

> 使用docker-compose.yml文件以及Dockerfile文件在生成自定义镜像的同时启动当前镜像，并且有docker-compose去管理容器

```
# yml文件
version: '3.1'
services: 
  ssm: 
    restart: always
    build:                 #构建自定义镜像
      context: ../         #指定dockerfile文件d的所在路径
      dockerfile: Dockerfile  #指定Dockerfile文件名称
    images: ssm:1.0.1
    container_name: ssm
    ports:
      - 8081:8080
    envirnoment: 
      TZ: Asia/Shanghai
```

```sh
Dockerfile文件
   
  from tomcat:9.0.27
  copy ssm.war /usr/local/tomcat/webapps
 
```

```sh
#可以直接启动基于docker-compose.yml以及Dockerfile文件构建的自定义镜像

docker-compose up -d

#如果自定义镜像不存在，会帮助我们构建出自定义镜像，如果自定义镜像已经存在，会直接运行这个自定义镜像

#重新构建的话

#重新构建自定义镜像

docker-compose build

#运行前，重新构建

docker-compose up -d --build
```



# 六.Docker CI、CD 

## 6.1 引言

> 项目部署
>
>     1. 将项目通过maven进行编译打包
>        2. 将文件上传到指定的服务器中
>        3. 将war包放到tomcat的目录中
>        4. 通过Dockerfile将Tomcat和war包转化成一个镜像，由DockerCompose去运行容器
>
> 项目更新了
>
>    将上述流程再次的从头到尾的执行一次

## 6.2 CI介绍

> CI (continuous intergration) 持续集成
>
> 持续集成：编写代码时，完成了一个功能后，立即提交代码到Git仓库中，将项目重新的构建并且测试。
>
>       1. 快速发现错误
>          2. 防止代码偏离主分支

## 6.3 实现持续集成

###  6.3.1 搭建Gitlab服务器

> 1、创建一个全新的虚拟机，并且至少指定4G的运行内存

> 2、按照docker以及docker-compose

> 3、docker-compose.yml文件去安装gitlab

```
version: '3'
services:
    web:
      image: 'twang2218/gitlab-ce-zh'
      restart: always
      hostname: '192.168.190.128'
      environment:
        TZ: 'Asia/Shanghai'
        GITLAB_OMNIBUS_CONFIG: |
          external_url 'http://192.168.190.128'
          gitlab_rails['gitlab_shell_ssh_port'] = 2222
          unicorn['port'] = 8888
          nginx['listen_port'] = 80
      ports:
        - '80:80'
        - '8443:443'
        - '2222:22'
      volumes:
        - /usr/local/docker/gitlab/config:/etc/gitlab
        - /usr/local/docker/gitlab/data:/var/opt/gitlab
        - /usr/local/docker/gitlab/logs:/var/log/gitlab
```

### 6.3.2 搭建GitLab-Runner

查看资料中的gitlab-runner文件即可安装

### 6.3.3 整合项目入门测试

> ```
> 1、创建maven工程，添加web.xml文件，编写html页面
> ```

> 2、编写gitlab-ci.yml文件

```sh
  stages: 
    - test
    
  test: 
    stage: test
    script: 
      - echo first test ci   #输入的命令
```

> 3、将maven工程推送到gitlab中

> 4、可以在 gitlab中查看到gitlab-ci.yml编写的内容

### 6.4 CD介绍

> CD (持续交付，持续部署)
>
> 持续交付：将代码交付给专业的测试团队去测试
>
> 持续部署：将测试通过的代码，发布到生产环境

![image-20201117112715334](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201117112715334.png)

