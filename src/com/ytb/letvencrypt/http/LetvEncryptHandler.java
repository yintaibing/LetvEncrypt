package com.ytb.letvencrypt.http;

import com.alibaba.fastjson.JSON;
import com.letv.core.utils.EncryptUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.ytb.letvencrypt.entity.EncryptResult;
import com.ytb.letvencrypt.util.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class LetvEncryptHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().getQuery();
        System.out.println(query);

        Map<String, Object> params = Utils.parseQueryParameters(httpExchange.getRequestURI());

        String inputUrl = (String) params.get("inputUrl");
        long seconds = Utils.string2Long(String.valueOf(params.get("seconds")));
        if (seconds <= 0L) {
            seconds = System.currentTimeMillis() / 1000L;
        }
        boolean jnative = Utils.string2Boolean(String.valueOf(params.get("jnative")));
        String so = String.valueOf(params.get("so"));

        EncryptResult result = new EncryptResult("", seconds);

        if (!Utils.isEmpty(inputUrl)) {
            try {
                result.output = EncryptUtils.callLetvEncrypt(seconds, inputUrl, jnative, so);
            } catch (Exception e) {
                e.printStackTrace();
                result.output = e.getMessage();
            }
        }

        httpExchange.sendResponseHeaders(200, 0);
        OutputStream out = httpExchange.getResponseBody();
        out.write(JSON.toJSONString(result).getBytes("UTF-8"));
        out.close();
    }
}
