package com.opingoo.services;


import com.opingoo.BO.User;
import com.opingoo.dao.UserDAO;
import com.opingoo.request.RegistrationResponse;
import com.opingoo.request.UserRegistrationRequest;
import com.opingoo.service.UserService;
import com.opingoo.sessions.SessionsCore;
import com.opingoo.utils.AppLogger;
import com.opingoo.utils.BaseUtils;
import com.opingoo.utils.GeneralConstants;
import com.wordnik.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Author @SmrutiRanjan
 */

@Path("/api")
@Api(value = "/api/users/", tags = "api/users/")
public class UserWebServices {

    private static AppLogger logger= AppLogger.getLogger();
    private static final String CLASSNAME = "UserWebservices";



    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Sign up admin user into the system")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Mandatory Parameters missing"),
    @ApiResponse(code = 500, message = "Error"), @ApiResponse(code = 200, message = "Success") })
    public Response UserLogin(@Context HttpServletRequest request,
                                 @FormParam("email") String email,
                                 @FormParam("mobile") String mobile,
                                 @FormParam("password") String password) {

        if ((email == null && mobile == null) || password == null)
            return BaseUtils.sendInfoResponse("Mandatory Parameters missing", 400, GeneralConstants.responseTypeJSON, request);

        // Check if user is already logged in
        String userId = SessionsCore.getUserIdFromSession(request.getSession());

        try {

            User user = UserDAO.getUserByEmailOrMobileOrId(email,mobile,null);

            if (user == null)
                return BaseUtils.sendInfoResponse("User not found", 401, GeneralConstants.responseTypeJSON, request);

                RegistrationResponse response = UserService.prepareObjResponse(user);

                return BaseUtils.sendInfoResponse(response, 200, GeneralConstants.responseTypeJSON, request);
        } catch (Exception e) {
            logger.logError(CLASSNAME, "Error Authenticating user with email " + email + " error = " + e.getMessage());
            e.printStackTrace();
            return BaseUtils.sendInfoResponse("User not found", 401, GeneralConstants.responseTypeJSON, request);
        } finally {
        }
    }




    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Sign up new user into the system")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Mandatory Parameters missing"),
    @ApiResponse(code = 500, message = "Error"),
    @ApiResponse(code = 200, message = "Success") })
    public Response mobileRegister(@Context HttpServletRequest request,
                                   @ApiParam(value = "B2B User Signup Post Request", required = true) UserRegistrationRequest registrationRequest){


        if (registrationRequest == null)
            return BaseUtils.sendInfoResponse("Mandatory Parameters missing", 400, GeneralConstants.responseTypeJSON, request);

        if(registrationRequest.getMobile() == null ||  registrationRequest.getMobile().isEmpty())
            return BaseUtils.sendInfoResponse("Mandatory parameter missing(mobile) !!",400,GeneralConstants.responseTypeJSON, request);

        if(registrationRequest.getEmail() == null ||  registrationRequest.getEmail().isEmpty())
            return BaseUtils.sendInfoResponse("Mandatory parameter missing(email) !!",400,GeneralConstants.responseTypeJSON, request);


        String email = registrationRequest.getEmail();
        String mobile = registrationRequest.getMobile();

        try {

            User user = UserDAO.getUserByEmailOrMobileOrId(email,mobile,null);
            if (user != null)
                return BaseUtils.sendInfoResponse("Mobile number/Email is already registered", 400, GeneralConstants.responseTypeJSON, request);

            UserService.createUserProfile(registrationRequest);


        }catch (Exception ex){
            ex.printStackTrace();
            logger.logError(CLASSNAME,"Error while adding user "+ex.getMessage());
            return BaseUtils.sendInfoResponse("Error while adding user!!!",500,GeneralConstants.responseTypeJSON,request);
        }
        return BaseUtils.sendInfoResponse("", 200, GeneralConstants.responseTypeJSON, request);
    }





}
