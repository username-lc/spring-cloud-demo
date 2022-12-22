# spring cloud 组件

## spring-cloud-alibaba-nacos

#### docker运行nacos-server

    docker pull nacos/nacos-server
    docker run -d --name nacos -p 8848:8848 -p 9848:9848 -e PREFER_HOST_MODE=hostname -e MODE=standalone nacos/nacos-server

#### 登录nacos控制台

    URL：localhost:8848/nacos
    USERNAME:nacos
    PASSWORD:nacos

