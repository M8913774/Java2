package com.suhorukov.gubin.server;


import java.util.Map;

public class ContentBuilder {
    String result = "";
    String root = "http://localhost:8080";

    public String create(Map<String, String> dirMap, Map<String, String> fileMap, Map<String, String> fileSizeMap,
                         String path, String pathRoot, boolean makeRoot) {

        if (makeRoot) result = ("<tr><td><a href = '" + root + pathRoot + "'> .. </a></td><td></td><td></td>\n");

        for (String s : dirMap.keySet()) {
            result += ("<tr><td><a href = '" + root + path+"/" + s + "'>" + s + "</a></td><td></td><td>" +
                    dirMap.get(s) + "</td>\n");
        }

        for (String s : fileMap.keySet()) {
            result += ("<tr><td><a href = '" + root + path + "/"  + s + "'>" + s + "</a></td><td>" +
                    fileSizeMap.get(s) + "b </td><td>" + fileMap.get(s) + "</td>\n");
        }

        result = "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <td>Name</td>\n" +
                "        <td>Size</td>\n" +
                "        <td>Last Modified</td>\n" +
                "    </tr>" +
                result +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        return result;
    }

    /*private String header() {
        String head = "HTTP/1.0 " + params.get("status") + "\r\n";
        if (params.containsKey("Content-Type")) {
            head += "Content-Type: " + params.get("Content-Type") + "\r\n";
        }
        if (params.containsKey("Mime type")) {
            head += "Mime type of " + params.get("Mime type") + "\r\n";
        }
        if (params.containsKey("Content-Length")) {
            head += "Content-Length: " + params.get("Content-Length") + "\r\n";
        }
        head += "\r\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <td>Name</td>\n" +
                "        <td>Size</td>\n" +
                "        <td>Last Modified</td>\n" +
                "    </tr>";

        return head;
    }   */
}

