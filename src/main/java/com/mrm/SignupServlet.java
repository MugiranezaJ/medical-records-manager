package com.mrm;

import com.google.gson.JsonObject;
import com.mrm.helpers.Request;
import com.mrm.helpers.Response;
import com.mrm.user.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonObject userData = Request.getParamsFromPost(request);
        Response responseBody = new Response();
        String email = userData.get("email").toString();

        // check if user exists
        if(userExists(email)){
            responseBody.setStatusCode(409);
            responseBody.setMessage("user already exists");
        }else {

            try {

                String role = userData.get("role").getAsString();

                if (role.equals("admin")) {

                    Admin admin = new Admin();
                    responseBody = admin.signup(userData);

                } else if (role.equals("pharmacist")) {

                    Pharmacist pharmacist = new Pharmacist();
                    responseBody = pharmacist.signup(userData);

                } else if (role.equals("patient")) {

                    Patient patient = new Patient();
                    responseBody = patient.signup(userData);

                } else if (role.equals("physician")) {

                    Physician physician = new Physician();
                    responseBody = physician.signup(userData);

                } else {
                    responseBody.setStatusCode(400);
                    responseBody.setMessage("Invalid role");
                }

                // respond to the request
                response.setStatus(responseBody.getStatusCode());
                responseBody.returnResponse(response, responseBody);

            } catch (Exception e) {
                response.setStatus(500);
                responseBody.setMessage("An error occurred while signing up");
                responseBody.returnResponse(response, responseBody);
            }
        }
    }
    public Boolean userExists(String email){
        if(User.dataStore.get(email) != null)
            return true;
        return false;
    }
}
