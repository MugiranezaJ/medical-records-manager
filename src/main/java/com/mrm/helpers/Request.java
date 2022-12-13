package com.mrm.helpers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class Request {
    public static JsonObject getParamsFromPost(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        JsonObject obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
        return obj;
    }
}
