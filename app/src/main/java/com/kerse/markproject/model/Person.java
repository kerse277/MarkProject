package com.kerse.markproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
public class Person
{

    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    private String uniqueID;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String work;

    @Getter
    @Setter
    private String gender;

    @Getter
    @Setter
    private boolean status;

    @Getter
    @Setter
    private String profileDesc;

    @Getter
    @Setter
    private int activeMarkCount;

    @Getter
    @Setter
    private int passiveMarkCount;

    @Getter
    @Setter
    private String auhtToken;

    @Getter
    @Setter
    private String profilePic;

    @Getter
    @Setter
    private String instagram;

    @Getter
    @Setter
    private String deviceID;

    @Setter
    @Getter
    private List<String> collectedMarks = new ArrayList<>();

    @Getter
    @Setter
    private List<String> collection = new ArrayList<>();

    @Getter
    @Setter
    private List<String> collectedCollection = new ArrayList<>();

    @Getter
    @Setter
    private List<String>  collectedPersonMessageStatus = new ArrayList<>();

    @Getter
    @Setter
    private int popularPoint;
}
