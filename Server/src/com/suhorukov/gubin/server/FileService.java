package com.suhorukov.gubin.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileService {
    String command;
    String path = "";
    String filePath = "";
    String pathRoot = "";
    boolean makeRoot;
    Map<String, String> params = new HashMap<String, String>();
    Map<String, String> dirMap = new HashMap<String, String>();
    Map<String, String> fileMap = new HashMap<String, String>();
    Map<String, String> fileSizeMap = new HashMap<String, String>();

    public FileService(PathRequestHandler requestHandler, String args) throws IllegalArgumentException {
        command = requestHandler.getCommand();
        if (!(command.equals("GET")) && !(command.equals("HEAD"))) throw new IllegalArgumentException();
        System.out.println("FS ask PRH: give me path!");
        String[] arrayPath = requestHandler.getPath();

        if (!arrayPath[0].equals("favicon.ico") && arrayPath != null) {
            for (String s : arrayPath) {
                path += "/" + s;
                filePath += File.separator + s;
            }
            filePath = args + filePath;
            if (arrayPath.length == 0) {

                makeRoot = false;
                pathRoot = "";

            } else {

                makeRoot = true;
                int i = 1;
                for (String s : arrayPath) {
                    if (i < arrayPath.length) pathRoot = "/" + s;
                    i++;
                }
            }
            System.out.println("FS create arguments:\n makeRoot = " + makeRoot +
                    "\n path = " + path + "\n filePath = " + filePath + "\n pathRoot = " + pathRoot);
        }
    }

    public String createDoc() {
        String result = "";
        switch (command) {
            case "GET":
                FileSystemManager fileSystemManager = new FileSystemManager(filePath);
                switch (fileSystemManager.checkFile()) {
                    case DIRECTORY:
                        params.put("status", "200 OK");
                        try {
                            fileSystemManager.listFiles();
                        } catch (FileNotFoundException fe) {
                            throw new IllegalArgumentException();
                        }
                        dirMap = fileSystemManager.getDirMap();
                        fileSizeMap = fileSystemManager.getFileSizeMap();

                        final List<String> listD = new ArrayList<>(dirMap.keySet());
                        Collections.sort(listD, new Comparator<String>() {
                            public int compare(String o1, String o2) {
                                return (dirMap.get(o2)).compareTo((dirMap.get(o1)));
                            }
                        });

                        final List<String> listF = new ArrayList<>(fileMap.keySet());
                        Collections.sort(listF);
                        Collections.sort(listF, new Comparator<String>() {
                            public int compare(String o1, String o2) {
                                return (fileMap.get(o2)).compareTo((fileMap.get(o1)));
                            }
                        });


                        if (fileMap.keySet().contains("index.html")) {


                        } else {
                            params.put("Content-Type:", "text/html");
                            result = new ContentBuilder().create(dirMap, fileMap, fileSizeMap, path, pathRoot, makeRoot);

                        }
                        return result;


                    case FILE:
                        return result;

                    case ERROR:
                        return result;

                }

                break;
            case "HEAD":
                return result;

        }
        return result;
    }
}
