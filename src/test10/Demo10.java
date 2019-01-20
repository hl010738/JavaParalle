package test10;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
    简易web服务器
 */
public class Demo10 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);

        while(!Thread.interrupted()){
            Socket client = server.accept();

            new Thread(new ServerThread(client)).start();

        }
    }
}
