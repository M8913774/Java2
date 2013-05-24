package com.suhorukov.gubin.server;


import java.io.File;
import java.util.Date;
import java.util.List;

public class FileSystemManager {
    List<String> dirList;
    List<String> dirTimeList;
    List<String> fileList;
    List<String> fileTimeList;
    List<String> fileSizeList;
    File file;

    enum check {FILE, DIRECTORY, ERROR}

    public FileSystemManager(String path) {

        file = new File(path);
        File[] list = file.listFiles();

        for (File s : list) {
            if (s.isDirectory()) {
                dirList.add(s.toString());
                dirTimeList.add(new Date(s.lastModified()).toString());
            } else {
                fileList.add(s.toString());
                fileTimeList.add(new Date(s.lastModified()).toString());
                fileSizeList.add(String.valueOf(s.length()));
            }
        }

    }

    public check checkFile() {

        if (file.isDirectory()) return check.DIRECTORY;
        else if (file.isFile()) return check.FILE;
        else return check.ERROR;

    }

    public List<String> getFileList() {
        return fileList;
    }
    public List<String> getDirTimeList() {
        return fileTimeList;
    }
    public List<String> getDirList() {
        return dirList;
    }
    public List<String> getFileTimeList() {
        return fileTimeList;
    }
    public List<String> getFileSizeList() {
        return fileSizeList;
    }
}
