package com.mrm.helpers;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Response {
    private int statusCode;
    private String message;
    private String data;

    public int getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = "\"message\":\"" + message +"\"";
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage(){ return message;}
    public String getData() {
        return "{ "+ message + ", \"data\":" + data + "}";
    }

    public void returnResponse(HttpServletResponse response, Response responseBody) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(responseBody.getData());
        out.flush();
    }
}
