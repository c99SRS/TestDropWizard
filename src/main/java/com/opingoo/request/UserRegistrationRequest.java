package com.opingoo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class UserRegistrationRequest {


    @Getter
    @Setter
    @JsonProperty(value = "name")
    private String name;

    @Getter
    @Setter
    @JsonProperty(value = "title")
    private String title;


    @Getter
    @Setter
    @JsonProperty(value = "age")
    private int age;

    @Getter
    @Setter
    @JsonProperty(value = "sex")
    private String sex;

    @Getter
    @Setter
    @JsonProperty(value = "email")
    private String email;

    @Getter
    @Setter
    @JsonProperty(value = "mobile")
    private String mobile;

    @Getter
    @Setter
    @JsonProperty(value = "password")
    private String password;

    @Getter
    @Setter
    @JsonProperty(value = "address")
    private String address;

    @Getter
    @Setter
    @JsonProperty(value = "city")
    private String city;

    @Getter
    @Setter
    @JsonProperty(value = "state")
    private String state;


    @Getter
    @Setter
    @JsonProperty(value = "country")
    private String country;

    @Getter
    @Setter
    @JsonProperty(value = "pin")
    private String postalCode;

    @Getter
    @Setter
    @JsonProperty(value ="location")
    private String location;



}
