package com.suhorukov.gubin.htmlGenerator;


import org.apache.commons.io.*;

import javax.activation.MimetypesFileTypeMap;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SrvHtml {

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
        private String args;
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
                String data[] = (sb.toString()).split(" ");
                System.out.println("Request is: " + sb.toString());
                String subDir = "";

                switch (data[0]) {
                    case "GET":
                        data[1] = data[1].substring(1, data[1].length()).trim();
                        String[] subdirectory = null;

                        for (String s : data) {
                            System.out.println("[" + s + "]");
                        }
                        String path;
                        if (!data[1].equals("favicon.ico")) {

                            if (data[1].length() == 0) {
                                System.out.println("SubDirectory is unknown!");
                                path = args;
                                data[1] = null;

                            } else {
                                System.out.println("SubDirectory is specified!");
                                subdirectory = data[1].split("/");

                                for (String sdir : subdirectory) {
                                    System.out.println(sdir);
                                    subDir += (sdir + File.separator);
                                }
                                System.out.println("subDir: " + subDir);
                                path = args + File.separator + subDir;
                            }

                            System.out.println("Directory is " + path);
                            String indexPath = path + File.separator + "index.html";

                            File fIndex = new File(indexPath);
                            File file = new File(path);

                            if (!file.exists()) {

                                params.put("status", "404 Not Found");
                                params.put("Content-Type", "text/html");
                                params.put("Content-Length", "100");
                                header();

                            } else if (fIndex.exists()) {

                                System.out.println("File index.html is exist, and is used.");
                                readIndex(file);

                            } else if (file.isDirectory()) {

                                params.put("status", "200 OK");
                                params.put("Content-Type", "text/html");

                                HtlmGen htlmGen = new HtlmGen(path);
                                System.out.println("parent is " + subDir);
                                String[] m = htlmGen.genHtml(subdirectory);

                                long length = 0;
                                for (String stringr : m) {
                                    if (stringr != null) {
                                        length += stringr.length();
                                    }
                                }
                                params.put("Content-Length", String.valueOf(length));
                                System.out.println(params.get("Content-Length"));
                                header();

                                for (String stringr : m) {
                                    output.write((stringr + "\n").getBytes());
                                }
                                output.flush();
                            } else if (file.isFile()) {
                                loadFile(file);
                            } else {
                                header();
                            }
                        } else {
                            System.out.println("Request favicon.ico");
                        }

                        break;
                    case "HEAD":
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void readIndex(File file) {

            String fName = null;
            try {
                fName = file.getCanonicalPath() + "\\index.html";
            } catch (IOException e) {
                e.printStackTrace();
            }
            File index = new File(fName);

            try (InputStream fromFile = new FileInputStream(index.getAbsolutePath())) {
                IOUtils.copy(fromFile, output);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void loadFile(File file) {

            params.put("status", "200 OK");
            params.put("Content-Type", new MimetypesFileTypeMap().getContentType(file));
            params.put("Mime type", file.getName() + " is " + params.get("Content-Type"));
            params.put("Content-Length", String.valueOf(file.length()));
            header();

            try {

                InputStream inFile = new FileInputStream(file.getAbsolutePath());
                IOUtils.copy(inFile, output);
                output.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void header() {

            String head = "<head>\n" +
                    "<title></title>\n" +
                    "</head>\n" +
                    "<body>\n";

            String row = "</body>\n" +
                    "</html>";
            try {
                output.write(("HTTP/1.0 " + params.get("status") + "\r\n").getBytes());
                if (params.containsKey("Content-Type")) {
                    output.write(("Content-Type: " + params.get("Content-Type") + "\r\n").getBytes());
                }
                if (params.containsKey("Mime type")) {
                    output.write(("Mime type of " + params.get("Mime type") + "\r\n").getBytes());
                }
                if (params.containsKey("Content-Length")) {
                    output.write(("Content-Length: " + params.get("Content-Length") + "\r\n").getBytes());
                }
                output.write("\r\n".getBytes());

                if (!(params.get("status").equals("200 OK"))) {
                    System.out.println("Status is: " + params.get("status"));
                    output.write((head + params.get("status") + row).getBytes());
                }
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}