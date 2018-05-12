package com.ytb.letvencrypt.http;

import com.letv.core.utils.EncryptUtils;
import com.sun.net.httpserver.HttpServer;
import com.ytb.letvencrypt.util.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyServer {
    public static void main(String[] args) {
        // parse args
        int port = 5656;
        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (arg.startsWith("-p") && arg.length() > 2) {
                    String portStr = arg.substring(2);
                    port = Utils.string2Int(portStr);
                }
            }
        }

        // start server
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/letvEncrypt", new LetvEncryptHandler());
            server.start();

            System.out.println("LetvEncrypt running...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
