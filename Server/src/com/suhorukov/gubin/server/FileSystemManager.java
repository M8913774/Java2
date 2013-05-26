package com.suhorukov.gubin.server;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileSystemManager {
    /*List<String> dirList = new LinkedList<>();
    List<String> dirTimeList = new LinkedList<>();
    List<String> fileList = new LinkedList<>();
    List<String> fileTimeList = new LinkedList<>();
    List<String> fileSizeList = new LinkedList<>();*/

    Map<String, String> fileMap = new HashMap<>();
    Map<String, String> dirMap = new HashMap<>();
    Map<String, String> fileSizeMap = new HashMap<>();
    File file;

    enum check {FILE, DIRECTORY, ERROR}

    public FileSystemManager(String path) {

        file = new File(path);
        System.out.println("FSM said: I'm here, boys!");
    }

    public check checkFile() {

        if (file.isDirectory()) return check.DIRECTORY;
        else if (file.isFile()) return check.FILE;
        else return check.ERROR;

    }

    public void listFiles() throws FileNotFoundException{
        if (checkFile() == check.DIRECTORY) {
            File[] list = file.listFiles();
            for (File s : list) {
                System.out.println("FSM said: " + s.getName());
                if (s.isDirectory()) {
                    dirMap.put(s.getName(), new Date(s.lastModified()).toString());
                } else {
                    fileMap.put(s.getName(), new Date(s.lastModified()).toString());
                    fileSizeMap.put(s.getName(), String.valueOf(s.length()));
                }
            }
        } else throw new FileNotFoundException();
    }

    public Map<String, String> getFileMap() {
        return fileMap;
    }

    public Map<String, String> getDirMap() {
        return dirMap;
    }

    public Map<String, String> getFileSizeMap() {
        return fileSizeMap;
    }
}
