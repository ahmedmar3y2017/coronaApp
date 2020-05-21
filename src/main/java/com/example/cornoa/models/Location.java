package com.example.cornoa.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Location {

    String state;
    String country;
    int totalCases;
    int previousCases;



}
