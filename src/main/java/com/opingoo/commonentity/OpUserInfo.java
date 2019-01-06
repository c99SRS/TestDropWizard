package com.opingoo.commonentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by SmrutiRanjan
 */

public class OpUserInfo {

        @Getter
        @Setter
        private String id;

        @Getter
        @Setter
        private int type;

        @Getter
        @Setter
        @JsonProperty("user_type")
        private int userType;

        @Getter
        @Setter
        @JsonProperty("is_op_user")
        private boolean isOpUser;

        @Getter
        @Setter
        @JsonProperty("is_expert")
        private boolean expert;

        @Getter
        @Setter
        @JsonProperty("expert_type")
        private int expertType;  // Expert Type


        @Getter
        @Setter
        private String name;

        @Getter
        @Setter
        @JsonProperty("display_title")
        private String displayTitle;

        @Getter
        @Setter
        @JsonProperty("display_text")
        private String displayText;

        @Getter
        @Setter
        @JsonProperty("profession")
        private String profession;

        @Getter
        @Setter
        @JsonProperty("occupation")
        private String occupation;


        @Getter
        @Setter
        @JsonProperty("gender")
        private String gender;

        @Getter
        @Setter
        @JsonProperty("city")
        private String city;

        @Getter
        @Setter
        @JsonProperty("pic_url")
        private String picUrl;

        @Getter
        @Setter
        @JsonProperty("connection_status")
        private String connectionStatus;

        @Getter
        @Setter
        @JsonProperty("mobile")
        private String mobile;


        @Getter
        @Setter
        @JsonProperty("email")
        private String email;

}
