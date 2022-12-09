package com.mrm.helpers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Request {
    public static JsonObject getParamsFromPost(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();

        try {
            String line;
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        JsonObject obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
        return obj;
    }
}
