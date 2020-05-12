package com.zudbs.project.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {

    private int id;

    private String userID;

    private String password;

    private String name;

    private char gender;

    private Date brith;
}
