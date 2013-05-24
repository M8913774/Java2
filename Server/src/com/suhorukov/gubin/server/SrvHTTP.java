package com.suhorukov.gubin.server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SrvHTTP {

    public static void main(String[] args) throws IOException {
        int port;
        String src = args[1];
        try {
            port = Integer.parseInt(args[0]);
            if (port < 2049 || port > 65536) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Parameter 'port' is not correctly.");
            port = 4040;
        }
        try (ServerSocket srv = new ServerSocket(port)) {
            while (true) {
                Socket client = srv.accept();
                System.out.println("Connect: " + client.getInetAddress() + ":" + client.getPort());
                new Thread(new Start(client, src)).start();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static class Start implements Runnable {
        String args;
        Map<String, String> params = new HashMap<String, String>();
        private InputStream input;
        private OutputStream output;

        Start(Socket srv, String args) throws Throwable {
            this.args = args;
            this.input = srv.getInputStream();
            this.output = srv.getOutputStream();
        }

        public void run() {

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                StringBuilder sb = new StringBuilder();

                int line;
                while ((line = in.read()) != -1 && line != 10 && line != 13) {
                    sb.append((char) line);
                }

                String data = sb.toString();
                System.out.println("Request is: " + data);

                try {
                    PathRequestHandler request;
                    request = new PathRequestHandler(data);
                    FileService fileService = new FileService(request, args);
                    String answer = fileService.createDoc();

                    output.write("HTTP/1.0 200 OK\r\n".getBytes());
                    output.write("Content-Type: text/html\r\n".getBytes());
                    output.write(("Content-Length: " + answer.length() + "\r\n").getBytes());
                    output.write("\r\n".getBytes());
                    output.write(answer.getBytes());
                    output.flush();
                } catch (IllegalArgumentException e) {
                    System.out.println("Server said: IllegalArgumentException");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


