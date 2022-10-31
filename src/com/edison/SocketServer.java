package com.edison;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        byte[] data = new byte[128];

        {
            try {
                serverSocket = new ServerSocket(2000);
                System.out.println("server套接字创建成功,等待客户端连接");
                while(true){
                    Socket accept = serverSocket.accept();
                    System.out.println("有客户端接入");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                InputStream inputStream = accept.getInputStream();
                                inputStream.read(data);
                                System.out.println("读到消息:" + new String(data));
                                OutputStream outputStream = accept.getOutputStream();
                                outputStream.write("服务器读到消息了".getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
