package test10;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket client;

    private InputStream in;

    private OutputStream out;

    public ServerThread(Socket client) throws IOException {
        this.client = client;
        init();
    }

    private void init() throws IOException {
        in = client.getInputStream();
        out = client.getOutputStream();
    }

    @Override
    public void run() {

        int len = 0;
        byte[] b = new byte[1024];
        while (true){
            try {
                if (!((len = in.read(b)) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(new String(b ,0 ,len));
        }
    }
}
