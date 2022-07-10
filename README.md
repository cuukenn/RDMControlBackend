#### 项目模块

- core：核心包;公有工具定义，协议定义等

- server：服务端；转发客户端傀儡端请求与响应，请求转发，心跳保持等

- puppet：傀儡端；

- client：控制端；

#### 架构思想：

- DDD（ps：别名【领域驱动模型设计】，目前理解程度很浅,待完善）

#### 技术栈

- springboot：java bean管理

- netty：NIO网络通信

- javafx：UI展示与交互

- protostuf：高效的运行时序列化协议

- maven：依赖管理工具

#### 注意事项

- jdk版本：>=11

- 已使用protostuf替换protobuf,之前.proto文件生成的java类已经不再使用，需自行删除

- 命令行运行mvn报错
  
  - 无效的标记: --release
  
  - 无效的发行版：11
    
    以上错误为全局jdk版本不匹配导致,可以使用以下方法解决
    
    - 使用idea的build和maven界面进行相关操作
    
    - 升级环境变量的jdk版本，>=11
