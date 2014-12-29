package com.spean.mina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/** 
* 原生的serversocket 搭建的服务器
* @author Huangsp
* @date 2014年12月29日 
*  
*/
public class SocketServer {

	public static void main(String[] args) {
		SocketServer socketServer = new SocketServer();
		socketServer.start();
	}
	
	/** 
	* @author Huangsp
	* @date 2014年12月29日 
	* 
	*/ 
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(9090);
			System.out.println("服务器启动..监听端口："+9090);
			while (true) {
				socket = serverSocket.accept();
				System.out.println("client:"+socket.hashCode()+"..建立连接");
				socketManager(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void socketManager(Socket socket) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				BufferedReader bufferedReader = null;
				BufferedWriter bufferedWriter = null;
				try {
					bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					String content = null;
					while ((content=bufferedReader.readLine())!=null) {
						System.out.println("client:"+socket.hashCode()+" say:"+content);
						bufferedWriter.write("server 接受到："+content+"\n");
						bufferedWriter.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						bufferedReader.close();
						bufferedWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
}
