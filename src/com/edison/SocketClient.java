package com.edison;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        Socket connect;
        {
            try {
                connect = connect();
                inputStream = connect.getInputStream();
                outputStream = connect.getOutputStream();
                service(outputStream,inputStream);
            } catch (Exception e) {
                e.printStackTrace();
                connect = connect();
                outputStream = connect.getOutputStream();
                inputStream = connect.getInputStream();
                System.out.println("已重置连接！");
                service(outputStream,inputStream);
            }
        }
    }

    private static void service(OutputStream outputStream, InputStream inputStream) throws IOException {
        while(true){
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.next();
            outputStream.write(msg.getBytes());

            byte[] data = new byte[128];
            inputStream.read(data);
            System.out.println("读到消息:" + new String(data));
            Socket connect = connect();
            inputStream = connect.getInputStream();
            outputStream = connect.getOutputStream();
        }
    }
    private static Socket connect() throws IOException {
        Socket client = new Socket("127.0.0.1",2000);
        return client;
    }
}
