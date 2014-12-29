package com.spean.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		SocketClient socketClient = new SocketClient();
		socketClient.start();
	}
	
	public void start() {
		Socket socket = null;
		BufferedReader bufferedReader = null;
		BufferedReader consoleReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			socket = new Socket("127.0.0.1", 9090);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			consoleReader = new BufferedReader(new InputStreamReader(System.in));
			addServerListener(bufferedReader);
			String consoleString = null;
			while((consoleString=consoleReader.readLine())!=null){
				bufferedWriter.write(consoleString+"\n");
				bufferedWriter.flush();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
				bufferedWriter.close();
				consoleReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addServerListener(BufferedReader reader) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				String str = null;
				try {
					while ((str = reader.readLine())!=null) {
						System.out.println("接收到服务端消息："+str);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
}
