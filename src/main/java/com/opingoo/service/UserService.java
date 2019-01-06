package com.opingoo.service;

import com.opingoo.BO.User;
import com.opingoo.dao.UserDAO;
import com.opingoo.request.RegistrationResponse;
import com.opingoo.request.UserRegistrationRequest;
import com.opingoo.utils.AppLogger;
import com.opingoo.utils.BaseUtils;
import com.opingoo.utils.DateUtils;
import com.opingoo.utils.GeneralConstants;

public class UserService {

    private static AppLogger logger = AppLogger.getLogger();
    private static final String CLASSNAME = UserService.class.getName();


    public static User createUserProfile(UserRegistrationRequest registrationPostRequest){

        User user = new User();
        try {
            String userId = BaseUtils.generateNewUUID();

            long nowTs = DateUtils.getTimeStamp();


            user.setId(userId);
            user.setName(registrationPostRequest.getName());
            user.setTitle(registrationPostRequest.getTitle());
            user.setEmail(registrationPostRequest.getEmail());
            user.setMobile(registrationPostRequest.getMobile());
            user.setAddress(registrationPostRequest.getAddress());
            user.setCity(registrationPostRequest.getCity());
            user.setCountry(registrationPostRequest.getCountry());
            user.setAge(registrationPostRequest.getAge());

            user.setUpdatedAt(nowTs);
            user.setCreatedAt(nowTs);


            user.setPasswordHash(registrationPostRequest.getPassword());

            // TODO - Approving it as of now
            user.setStatus(GeneralConstants.ActivityStatus.ACTIVE.getValue());

            UserDAO.insert(user);

        }catch (Exception ex){
            ex.printStackTrace();
            logger.logError(CLASSNAME,"Error in createUserProfile()  !!!");
        }

        return user ;
    }


    public static RegistrationResponse prepareObjResponse(User user) {

        if (user == null)
            return null;

        RegistrationResponse response = new RegistrationResponse();

        try {
            response.setId(user.getId());
            response.setName(user.getName());
            response.setDisplayTitle(user.getTitle());
            response.setGender(user.getSex());
            response.setEmail(user.getEmail());
            response.setMobile(user.getMobile());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return response;

    }
}
