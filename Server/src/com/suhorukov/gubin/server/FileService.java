package com.suhorukov.gubin.server;

import java.io.File;
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
        if (!arrayPath[0].equals("favicon.ico")) {
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
                        dirMap = fileSystemManager.getDirMap();
                        fileSizeMap

                        List<String> dirList = fileSystemManager.getDirList();
                        List<String> dirTimeList = fileSystemManager.getDirTimeList();
                        int i = 0;
                        for (String s : dirList) {
                            dirMap.put(s, dirTimeList.get(i));
                            i++;
                        }
                        final List<String> listD = new ArrayList<>(dirMap.keySet());
                        Collections.sort(listD, new Comparator<String>() {
                            public int compare(String o1, String o2) {
                                return (dirMap.get(o2)).compareTo((dirMap.get(o1)));
                            }
                        });

                        List<String> fileList = fileSystemManager.getFileList();
                        List<String> fileTimeList = fileSystemManager.getFileTimeList();
                        List<String> fileSizeList = fileSystemManager.getFileSizeList();
                        i = 0;
                        for (String s : fileList) {
                            fileMap.put(s, fileTimeList.get(i));
                            i++;
                        }
                        i = 0;
                        for (String s : fileList) {
                            fileSizeMap.put(s, fileSizeList.get(i));
                            i++;
                        }
                        final List<String> listF = new ArrayList<>(fileMap.keySet());
                        Collections.sort(listF, new Comparator<String>() {
                            public int compare(String o1, String o2) {
                                return (fileMap.get(o2)).compareTo((fileMap.get(o1)));
                            }
                        });


                        if (fileList.contains("index.html")) {


                        } else {
                            params.put("Content-Type:", "text/html");
                            result = new ContentBuilder().create(dirMap, fileMap, fileSizeMap, path, filePath, pathRoot, makeRoot);

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
