package com.suhorukov.gubin.server;


public class PathRequestHandler {

    String[] path;
    String command;

    public PathRequestHandler(String request) throws IllegalArgumentException {

        String[] aRequest = request.split(" ");
        if (aRequest[0].length() == 0) throw new IllegalArgumentException();
        command = aRequest[0];
        aRequest[1] = aRequest[1].substring(1, aRequest[1].length()).trim();

        if (aRequest[1].length() != 0) {
            path = aRequest[1].split("/");
        }
    }

    public final String[] getPath() {
        return path;
    }

    public final String getCommand() {
        return command;
    }
}
