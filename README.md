# spring cloud 组件

## spring-cloud-alibaba-nacos

#### 1、docker运行nacos-server

    docker pull nacos/nacos-server
    docker run -d --name nacos -p 8848:8848 -p 9848:9848 -e PREFER_HOST_MODE=hostname -e MODE=standalone nacos/nacos-server

#### 2、登录nacos控制台

    URL：localhost:8848/nacos
    USERNAME:nacos
    PASSWORD:nacos

#### 3、配置yaml

    创建命名空间,命名空间名称:dev,命名空间ID:dev
    将nacos文件夹中的yaml配置到nacos-server中
    dataId:文件名
    Group:SPRING_CLOUD_DEMO_GROUP

## spring-cloud-gateway

    