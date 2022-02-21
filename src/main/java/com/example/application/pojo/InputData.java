package com.example.application.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class InputData{
    @Setter @Getter private List<String> fields;
    @Setter @Getter private List<List<Object>> values;
}