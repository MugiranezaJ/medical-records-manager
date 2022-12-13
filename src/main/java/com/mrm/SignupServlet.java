package com.mrm;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mrm.data.DataStore;
import com.mrm.helpers.Request;
import com.mrm.helpers.Response;
import com.mrm.models.UserModel;
import com.mrm.user.*;
import com.mrm.validation.UserValidation;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class SignupServlet extends HttpServlet {
    Gson gson;
    Response responseBody;
    UserValidation validation;
    JsonObject userData;
    UserModel userModel;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("This route is only supported for POST ")
                .append(request.getServletPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        gson = new Gson();
        responseBody = new Response();
        validation = new UserValidation();

        userData = Request.getParamsFromPost(request.getReader());
        userModel = gson.fromJson(userData, UserModel.class);
        userModel.id = UUID.randomUUID().toString();

        try {
            List<String> errors = validation.validateSignup(userModel);

            // check if user exists
            if(!errors.isEmpty()) {
                responseBody.setStatusCode(404);
                responseBody.setMessage("validation error");
                responseBody.setData(gson.toJson(errors));
            }else if(userExists(userModel.email)) {
                responseBody.setStatusCode(409);
                responseBody.setMessage("user already exists");
            }else {

                switch (userModel.role.toLowerCase()){

                    case "admin":
                        Admin admin = new Admin();
                        responseBody = admin.signup(userModel);
                        break;
                    case "pharmacist":
                        Pharmacist pharmacist = new Pharmacist();
                        responseBody = pharmacist.signup(userModel);
                        break;
                    case "patient":
                        Patient patient = new Patient();
                        responseBody = patient.signup(userModel);
                        break;
                    case "physician":
                        Physician physician = new Physician();
                        responseBody = physician.signup(userModel);
                        break;
                    default:
                        responseBody.setStatusCode(400);
                        responseBody.setMessage("Invalid role");
                }

            }

            // respond to the request
            response.setStatus(responseBody.getStatusCode());
            responseBody.returnResponse(response, responseBody);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            responseBody.setMessage("An error occurred while signing up");
            responseBody.returnResponse(response, responseBody);
        }
    }
    public Boolean userExists(String email){
        if(DataStore.users.get(email) != null)
            return true;
        return false;
    }
}
