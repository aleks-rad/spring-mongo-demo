package ru.aleksrad.springmongodemo.model;

import lombok.Data;

@Data
public class Settings {

    private String userName;
    private String lang;
    private boolean isActive;

}
