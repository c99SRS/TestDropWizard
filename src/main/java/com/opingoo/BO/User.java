package com.opingoo.BO;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements  java.io.Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "id",nullable = false)
    private String id;

    @Column(name="name")
    @Getter
    @Setter
    private String name;

    @Column(name="title")
    @Getter
    @Setter
    private String title;

    @Column(name="pic_id")
    @Getter
    @Setter
    private String picId;

    @Column(name="sex")
    @Getter
    @Setter
    private String sex;

    @Column(name="age")
    @Getter
    @Setter
    private int age;


    @Column(name="email")
    @Getter
    @Setter
    private String email;

    @Column(name ="mobile")
    @Setter
    @Getter
    private String mobile;

    @Column(name ="password_hash")
    @Setter
    @Getter
    private String passwordHash;


    @Column(name="is_admin")
    @Getter
    @Setter
    private int isAdmin;


    @Column(name ="is_verified")
    @Setter
    @Getter
    private int isVerified;

    @Column(name="last_activity")
    @Getter
    @Setter
    private long lastActivity;


    @Column(name="years_of_exp")
    @Getter
    @Setter
    private float yearsOfExperience;

    @Column(name="postal_code")
    @Getter
    @Setter
    private String postalCode;

    @Column(name="locality")
    @Getter
    @Setter
    private String locality;

    @Column(name="city")
    @Getter
    @Setter
    private String city;

    @Column(name="state")
    @Getter
    @Setter
    private String state;

    @Column(name="country")
    @Getter
    @Setter
    private String country;

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @Column(name="user_type")
    @Getter
    @Setter
    private int userType;             // type to distinguish from diff users


    @Column(name ="status")
    @Setter
    @Getter
    private int status;


    @Column(name ="created_at")
    @Setter
    @Getter
    private long createdAt;

    @Column(name ="updated_at")
    @Setter
    @Getter
    private long updatedAt;

}
