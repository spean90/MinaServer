MinaServer
==========

学习使用mina框架

SocketServer.java为原生的serverSocket创建的socket服务器、实现与多个客户端socket实时通信；

由于随着客户端数量的增加；原生的serversocket不能很好的管理与各个客户端连接的socket和内存消耗的问题；
改用网络通信框架、目前比较流行的又netty、mina；由于主要作者是同一个人所以差别不是很大、这边选择mina学习；

minaserver为采用mina搭建的基本服务器；这边使用了自带的简单TextLineCodesFactory;也自定义了了一个factory;加深理解；
