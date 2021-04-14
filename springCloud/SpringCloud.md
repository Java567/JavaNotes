# SpringCloud

## 一、SpringCloud介绍

### 1.1 微服务架构

> 微服务架构的提出者：马丁福勒
>
> htttps://martinfowler.com/articles/microservices.html 

> 简而言之，微服务架构样式[[1\]](https://martinfowler.com/articles/microservices.html#footnote-etymology)是一种将单个应用程序开发为一组小服务的方法，每个小服务都在自己的进程中运行并与轻量级机制（通常是HTTP资源API）进行通信。这些服务围绕业务功能构建，并且可以由全自动部署机制独立部署。这些服务的集中管理几乎没有，它可以用不同的编程语言编写并使用不同的数据存储技术。
>
> 1、微服务架构只是一个样式，一个风格。
>
> 2、将一个完成的项目，拆分成多个模块去分别开发。
>
> 3、每一个模块都是单独的运行在自己的容器中的。
>
> 4、每一个模块都是需要相互通讯的。可以用 Http,RPC,MQ
>
> 5、每一个模块之间是没有依赖关系的，单独的部署。
>
> 6、可以用多种语言去开发不同的模块。
>
> 7、使用MYSQL数据库，Redis，ES去存储数据，也可以使用多个MYSQL数据库。
>
> 总结：将复杂臃肿的单体应用进行细粒度的划分，每个拆分出来的服务各自打包部署。

### 1.2 SpringCloud介绍

>  SpringCould是微服务架构落地的一套技术栈。
>
> SpringCould中的大多数技术都是基于Netflix公司的技术进行二次研发。
>
> 1、SpringCloud的中文社区网址：http://springcould.cn/
>
> 2、SpringCloud的中文网：http://springcould.cc/
>
> 八个技术点：
>
> - Eureka - 服务的注册与发现
> - Robbin - 服务之间的负载均衡
> - Feign - 服务间的t通讯
> - Hystrix - 服务的线程隔离及断路器
> - Zuul - 服务的网关
> - Stream - 实现MQ的使用
> - Config - 动态配置
> - Sleuth - 服务追踪

## 二、服务的注册与发现-Eureka

### 2.1 引言

> Eureka就是帮助我们维护所有服务的信息，以便服务之间相互调用

![ ](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\1.png)

### 2.2 Eureka的快速入门

#### 2.2.1 创建EurekaServer

> 创建一个父工程，并且在父工程中指定SpringCloud的版本，并且将packaing修改为pom

```xml
<packaging>pom</packaging>  

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Hoxton.SR4</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

>  2、创建eureka的server，创建SpringBoot工程，并且导入依赖，在启动类中添加注解,然后直接编写yml

> 2.1 导入依赖

```xml
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-eureka-client -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

> 2.2 添加启动类注解

```java
//@EnableEurekaServer在springboot 2.X可以不用写
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
```

> 2.3 编写yml配置文件

```yml
server:
  port: 8761    # 端口号

eureka:
  instance:
    hostname: localhost
  client:
    # 当前的eureka服务是单机版的
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

#### 2.2.2 创建EurekaClient

> 1、创建Maven工程，修改为SpringBoot  

> 2、导入依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

> 3、在启动类上添加注解

```java
@SpringBootApplication
@EnableEurekaClient
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class,args);
    }
}
```

> 4、编写配置文件

```yml
# 指定Eureka服务地址
eurka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/

# 指定服务的名称
spring:
  application:
    name: CUSTOMER
```

![2](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\2.png)

#### 2.2.3 测试Eurcka

> 1、创建了一个Search搜索模块，并且注册到Eureka

> 2、使用到EurekaClient的对象去获取服务信息

```java
@Autowired
private EurekaClient eurekaClient;
```



> 3、正常RestTemplate调用即可

```java
@GetMapping("/customer")
public String customer(){
    //1. 通过eurekaClient获取到SEARCH服务的信息
    InstanceInfo info = eurekaClient.getNextServerFromEureka("SEARCH", false);

    //2. 获取到访问的地址
    String url = info.getHomePageUrl();
    System.out.println(url);

    //3. 通过restTemplate访问
    String result = restTemplate.getForObject(url + "/search", String.class);
    //4. 返回
    return result;
}
```

### 2.3 Eureka的安全性

> 实现Eureka认证

> 1、导入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

> 2、编写配置类

```java
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
```

> 3、编写配置文件

```yml
# 指定安全认证的用户名和密码
spring:
  security:
    user:
      name: root
      password: root
```

> 4、其他服务想注册到Eureka上需要填写用户名和密码

```yml
# 指定Eureka服务地址
eureka:
  client:
    service_url:
      defaultZone: http://用户名:密码@localhost:8761/eureka
```

### 2.4 Eureka的高可用

> 如果程序正在运行，突然Eureka宕机了。
>
> 1、如果调用方访问过一次被调用方了，Eureka的宕机不会影响到功能。
>
> 2、如果调用方没有访问过被调用方，Eureka的宕机就会造成当前功能不可用。

-------

> 搭建Eureka高可用

> 1、准备多台Eureka

```
采用了复制的方式,删除iml和target文件,并且修改pom.xml中的项目名称,再给父工程添加一个moudel
```

> 2、让服务注册到多台Eureka

```yml
# 指定Eureka服务地址
eureka:
  client:
    service_url:
      defaultZone: http://root:root@localhost:8761/eureka,http://root:root@localhost:8762/eureka
```

> 3、让多台Eureka之间相互通讯

```yml
eureka:
  client:
    registerWithEureka: true   # 是否注册自己的信息到EurekaServer上，默认是true
    fetchRegistry: true  # 是否拉取其它服务的信息，默认是true
    serviceUrl:
      defaultZone: http://root:root@localhost:8762/eureka/
```

### 2.5 Eureka的细节

> 1、EurekaClient启动时，将自己的信息注册到EurekaServer上，EurekaServer就会存储上EurekaClient的注册信息。

> 2、当EurekaClient调用服务时，本地没有注册信息的缓存时，去EurekaServer中去获取注册信息。

> 3、EurekaClient会通过心跳的方式去和EurekaServer进行连接，（默认30sEurekaClient会发送一次心跳请求，如果超过了90s还没有发送心跳请求的话，EurekaServer就认为你宕机了，将当前EurekaClient从注册表中移除）

```yml
eureka:
  instance:
    lease-renewal-interval-in-seconds: 30  #  心跳的间隔
    lease-expiration-duration-in-seconds: 90  # 多久没发送，就认为你宕机了
```

> 4、 EurekaClient会每隔30s去EurekaServer中去更新本地的注册表

```yml
eureka:
  client:
    registry-fetch-interval-seconds: 30  # 每隔多久去更新一下本地的注册表缓存信息
```

> 5、Eureka的自我保护机制，统计15分钟内，如果一个服务的心跳发送比例低于85%，EurekaServer就会开启自我保护机制 
>
> 1. 不会从EurekaServer中去移除长时间没有收到心跳的服务。
> 2. EurekaServer还是可以正常提供服务的。
> 3. 网络比较稳定时，EurekaServer才会开始将自己的信息被其他节点同步过去

```yml
eureka:
  server:
    enable-self-preservation: true  # 开启自我保护机制
```

> 6、CAP定理，C-一致性，A-可用性，P-分区容错性，这三个特性在分布式环境下，只能满足2个，而且分区容错性在分布式环境下，时必须要满足的，只能在AC之间进行权衡。
>
> 如果选择CP，保证了一致性，可能会造成你系统在一定时间内是不可用的，如果你同步数据的时间比较长，造成的损失大。
>
> Eureka就是一个AP的效果，高可用的集群，Eureka集群是无中心，Eureka即便宕机几个也不会影响系统的使用，不需要重新去推举一个master，也会导致一个时间内数据是不一致的。

## 三、服务间的负载均衡-Robbin

### 3.1 引言

> Robbin是帮助我们实现服务和服务负载均衡，Robbin属于客户端负载均衡。
>
> 客户端负载均衡：customer客户模块，将2个Search模块信息全部拉取到本地的缓存，在custmoer中自己做一个负载均衡的策略，选中某一个服务。
>
> 服务端负载均衡：在注册中心，直接根据你指定的负载均衡策略，帮你选中指定的服务信息，并返回。

![3](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\3.png)

### 3.2 Robbin的快速入门

> 1、启动两个search模块

> 2、在customer导入robbin依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

> 3、配置整合RestTemplate和Rabbin

```java
@Bean
@LoadBalanced
public RestTemplate restTemplate(){
    return new RestTemplate();
}
```

> 4、在customer中去访问serach

```java
@GetMapping("/customer")
public String customer(){
    String result = restTemplate.getForObject("http://SEARCH/search", String.class);
    //4. 返回
    return result;
}
```

### 3.3 Robbin配置负载均衡策略

> 负载均衡策略
>
> - RandomRule: 随机策略
> - RoundRobbinRule: 轮询策略
> - WeightedResponseTimeRule: 默认会采用轮询的策略，后续会根据服务的响应时间，自动给你分配权重
> - BestAvailableRule: 根据被调用方并发数最小的去分配

> 采用注解的方式

```java
@Bean
public IRule robbinRole(){
    return new RandomRule();
}
```

> 配置文件去指定负载均衡的策略（推荐）

```yml
# 指定具体服务的负载均衡策略
SEARCH:    # 编写服务名称
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.WeightedResponseTimeRule

```

## 四、服务间的调用-Feign

### 4.1 引言

> Fegin可以帮助我们实现面向接口编程，就直接调用其他的服务，简化开发。

### 4.2 Feign的快速入门

> 1、导入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

> 2、添加一个注解

```java
@EnableFeignClients
```

> 3、创建一个接口，并且和Search模块做映射

```java
@FeignClient("SEARCH")  //指定的是服务名称
public interface SearchClient {

    //value->目标服务的请求路径，method -> 映射请求方式
   @RequestMapping(value = "/search",method = RequestMethod.GET)
    String search();
}
```

> 4、测试使用

```java
@Autowired
private SearchClient searchClient;


@GetMapping("/customer")
public String customer(){
    String result = searchClient.search();
    return result;
}
```

### 4.3 Fegin的传递参数方式

> 1、注意事项
>
> 1. 如果你传递的参数，比较复杂时，默认会采用POST的请求方式。
> 2. 传递单个参数时，推荐使用@PathVariable，如果传递的单个参数比较多，这里也可以采用@RequestParam，不要省略value属性。
> 3. 传递对象信息时，统一采用json的方式，添加@RequestBody
> 4. Client接口必须采用@RequestMapping

> 2、在Search模块下准备三个接口

```java
@GetMapping("/search/{id}")
public Customer findById(@PathVariable Integer id){
    return new Customer(1,"张三",23);
}



@GetMapping("/getCustomer")  //会自动转换位POST请求  405
public Customer getCustomer(@RequestParam Integer id,
                            @RequestParam String name){
    return new Customer(id,name,23);
}


@PostMapping("/save")
public Customer save(@RequestBody Customer customer){
    return customer;
}
```

> 3、封装Customer模块下的Controller

```java
@GetMapping("/customer/{id}")
public Customer findById(@PathVariable Integer id){
    return searchClient.findById(id);
}



@GetMapping("/getCustomer")
public Customer getCustomer(@RequestParam Integer id,
                            @RequestParam String name){
    return searchClient.getCustomer(id,name);
}


@GetMapping("/save")  //会自动转换位POST请求  405
public Customer save( Customer customer){
    return searchClient.save(customer);
}

```

> 4、再封装Client接口

```java
@RequestMapping(value = "/search/{id}",method = RequestMethod.GET)
Customer findById(@PathVariable Integer id);



@RequestMapping(value = "/getCustomer",method = RequestMethod.GET)
Customer getCustomer(@RequestParam(value = "id") Integer id,
                     @RequestParam(value = "name") String name);


//会自动转换位POST请求  405
@RequestMapping(value = "/save",method = RequestMethod.POST)
Customer save(@RequestBody Customer customer);
```



> 5、测试



### 4.4 Feign的Fallback

> Fallback可以帮助我们在使用Feign去调用另外一个服务时，如果出现了问题，走服务降级，返回一个错误数据，避免功能以为一个服务出现问题，全部失效。
>

> 1、创建一个POJO类，实现client接口。

```java
@Component
public class SearchClientFallBack implements SearchClient {
    @Override
    public String search() {
        return "出现问题了！！！ ";
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Customer getCustomer(Integer id, String name) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }
}
```

> 2、修改Client接口中的注解，添加一个属性。

```java
@FeignClient(value = "SEARCH",fallback = SearchClientFallBack.class)  
```

> 3、添加一个配置文件

```yml
# feign和hystrix组件整合
feign:
  hystrix:
    enabled: true
```



> 调用方无法知道具体的错误信息是什么，通过FallBackFactory的方式去实现这个功能

> 1 、FallBackFactory基于FallBack

> 2、创建一个POJO类，实现FallBackFactory<Client>

```java
@Component
public class SearchClientFallBackFactory implements FallbackFactory<SearchClient> {

    @Resource
    private SearchClientFallBack searchClientFallBack;

    @Override
    public SearchClient create(Throwable throwable) {
        throwable.printStackTrace();
        return searchClientFallBack;
    }
}
```

> 3、修改Client接口的属性

```java
@FeignClient(value = "SEARCH"
             ,fallbackFactory = SearchClientFallBackFactory.class)  //指定的是服务名称
```



## 五、服务的隔离及断路器-Hystrix

### 5.1 引言

![4](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\4.png)

### 5.2 降级机制实现

> 1、导入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

> 2、添加一个注解

```java
@EnableCircuitBreaker
public class CustomerApplication {
}
```

> 3、针对某一个接口去编写它的降级方法

```java
@GetMapping("/customer/{id}")
@HystrixCommand(fallbackMethod = "findByIdFallBack")
public Customer findById(@PathVariable Integer id){
    int i= 1/0;
    return searchClient.findById(id);
}


// findById的降级方法，方法的描述和接口一致
public Customer findByIdFallBack(Integer id){
    return new Customer(-1,"",0);
}
```

> 4、在接口上添加注解

```java
@HystrixCommand(fallbackMethod = "findByIdFallBack")
```

> 5、测试一下

### 5.3 线程隔离

> 如果使用Tomcat的线程池去接送用户的请求，使用当前线程去执行其他服务的功能，如果某一个服务出现了故障，导致tomcat的线程大量的堆积，导致Tomcat无法处理其他业务功能。
>
> 1、使用Hystrix的线程池（默认）。接收用户请求采用tomcat的线程(执行业务代码)，调用其他服务采用Hystrix的线程池。
>
> 2、信号量，使用的还是tomcat的线程池，帮助我们去管理tomcat的线程池。



> 1、Hystrix的线程池的配置（具体的配置name属性需要去查看HystrixCommandProperties类）
>
> 1. 线程隔离策略: name= `hystrix.command.default.execution.isolation.strategy`，value= `THREAD`，`SEMAPHORE`
> 2. 指定超时时间: name= `hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds`，value=` 1000`
> 3. 是否开启超时时间配置: name= `hystrix.command.default.execution.timeout.enabled`，value= `true`
> 4. 超时之后是否中断线程:name= `hystrix.command.default.execution.isolation.thread.interruptOnTimeout`，value=` true`
> 5. 取消任务后知否中断线程:name= `hystrix.command.default.execution.isolation.thread.interruptOnCancel`，value= `false`



> 2、信号量的配置信息
>
> 1. 线程隔离策略:
>
>     name= `hystrix.command.default.execution.isolation.strateg`，value= `THREAD`，`SEMAPHORE`
>
> 2. 指定信号量的最大并发请求数:
>    name= `hystrix.command.default.execution.isolation.semaphore. maxConcurrentRequests`，value=`10`

### 5.4 断路器

#### 5.4.1 介绍

> 在调用指定服务时，如果说这个服务的失败率达到你输入的一个阈值，将断路器从closed状态转变为open状态，指定服务时是无法被访问的，之后如果你访问，它就会直接走fallback方法，在一定时间内，open状态会再次转变为half open状态，允许一个请求发送到我的指定服务，如果成功，转变为closed，如果失败，服务再次转变为open状态，会再次循环到half open，直到断路器回到一个closed状态。

![5](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\5.png)

#### 5.4.2 配置断路器的监控画面

> 1、导入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
```

> 2、在启动类中添加注解

```java
@EnableHystrixDashboard
@ServletComponentScan("com.lj.servlet")
public class CustomerApplication {
}
```

> 3、配置一个Servlet，指定上Hystrix的Servlet

```java
@WebServlet("/hystrix.stream")
public class HystrixServlet extends HystrixMetricsStreamServlet {
}


//------------------
//在启动类上，添加扫描servlet的注解
@ServletComponentScan("com.lj.servlet")
```

> 4、测试一下
>
> 直接访问Http://host:port/hystrix

![6](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\6.png)

> 在当前位置输入映射好的servlet路径

![7](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\7.png)

#### 5.4.3 配置断路器的属性

> 断路器的属性（10s中之内）
>
> 1. 断路器的开关: 
>
>    name= `hystrix.command.default.circuitBreaker.enabled`，value= `true`
>
> 2. 失败阈值的总请求数:
>
>    name= `hystrix.command.default.circuitBreaker.requestVolumeThreshold`，
>    value=`20` 
>
> 3. 请求总数失败率达到%多少时:
>    name= `hystrix.command.default.circuitBreaker.errorThresholdPercentage`，value= `50`
>
> 4. 断路器open状态后，多少秒是拒绝请求的:
>    name= `hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds`，value= `5000`
>
> 5. 强制让服务拒绝请求: 
>
>    name= `hystrix.command.default.circuitBreaker.forceOpen`，value= `false`
>
> 6. 强制让服务接收请求: 
>
>    name= `hystrix.command.default.circuitBreaker.forceClosed`，value= `false`

------------

> 具体配置方式

```java
@GetMapping("/customer/{id}")
@HystrixCommand(fallbackMethod = "findByIdFallBack",
commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "70"),
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
})
public Customer findById(@PathVariable Integer id) throws InterruptedException {
    System.out.println(Thread.currentThread().getName());
    if(id==1){
        int i=1/0;
    }
    return searchClient.findById(id);
}
```

### 5.5 请求缓存

#### 5.5.1 请求缓存介绍

> 1、缓存请求的声明周期是一次请求
>
> 2、请求缓存是缓存当前线程中的一个方法，将方法参数作为key，方法的返回结果作为value
>
> 3、在一次请求中，目标方法被调用过一次，以后就都会被缓存。

![8](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\8.png)

#### 5.5.2 请求缓存的实现

> 1、创建一个Service，在Service中调用Search服务

```java
@Service
public class CustomerService {

    @Resource
    private SearchClient searchClient;


    @CacheResult
    @HystrixCommand(commandKey = "findById")
    public Customer findById(@CacheKey Integer id) throws InterruptedException {
        return searchClient.findById(id);
    }


    @CacheRemove(commandKey = "findById")
    @HystrixCommand
    public void clearFindById(@CacheKey Integer id){
        System.out.println("findById被清空");
    }


}
```

> 2、使用请求缓存的注解

- @CacheResult：帮助我们缓存当前方法的返回结果（必须和@HystrixCommand配合使用）
-  @CacheRemove：帮助我们清楚某一个缓存信息（基于commandKey）
- @CacheKey：指定哪个方法参数作为缓存的标识

> 3、修改Search模块的返回结果

```java
@GetMapping("/search/{id}")
public Customer findById(@PathVariable Integer id){
    return new Customer(1,"张三",(int) (Math.random()*100000));
}
```

> 4、编写Filter，去构建HystrixRequestContext

```java
@WebFilter("/*")
public class HystrixRequestContextInitFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext.initializeContext();
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
```

> 5、修改了Controller

```java
public Customer findById(@PathVariable Integer id) throws InterruptedException {
    System.out.println(Thread.currentThread().getName());
    if(id==1){
        int i=1/0;
    }
    System.out.println(customerService.findById(id));
    System.out.println(customerService.findById(id));
    customerService.clearFindById(id);
    System.out.println(customerService.findById(id));
    System.out.println(customerService.findById(id));
    return searchClient.findById(id);
}
```

> 6、测试结果

![9](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\9.png)

## 六、服务的网关-Zuul

### 6.1 引言

> 如果不适用zuul，会出现什么情况
>
> 1、客户端维护大量的Ip和port信息，直接访问指定服务。
>
> 2、认证和授权操作，需要在每一个模块中都添加认证和授权的操作。
>
> 3、项目的迭代，服务要拆分，服务要合并，需要客户端进行大量的变化
>
> 4、我们还可以统一的把安全性认证都放在Zuul中

![10](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\10.png)

### 6.2 Zuul的快速入门

> 1、创建Maven项目，修改为SpringBoot

> 2、导入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

> 3、添加一个注解

```java
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }
}
```

> 4、编写配置文件

```yml
# 指定Eureka服务地址
eureka:
  client:
    service_url:
      defaultZone: http://root:root@localhost:8761/eureka,http://root:root@localhost:8762/eureka




# 指定服务的名称
spring:
  application:
    name: ZUUL


server:
  port: 80
```

> 5、开始测试

![11](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\11.png)

### 6.3 Zuul常用配置信息

#### 6.3.1 Zuul的监控画面

> 1、导入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

> 2、编写配置文件

```yml
# 查看zuul的监控画面(开发时配置为*，上线不要配置为*)
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

> 3、直接访问

![12](C:\Users\Administrator\Desktop\java笔记\springCloud\图片\12.png)

#### 6.3.2 忽略服务配置

```yml
# zuul的配置
zuul:
  # 基于服务名忽略服务，无法查看，如果要忽略全部的服务，"*",默认配置的全部路径都会被忽略掉（自定义服务配置无法忽略的）
  ignored-services: eureka
  # 监控界面依然可以查看，在访问的时候，404错误
  ignored-patterns: /**/search/**
```

#### 6.3.3 自定义服务配置









## 七、服务的信息传递-Stream



## 八、服务的动态配置-Config



## 九、服务的追踪-Sleuth

 