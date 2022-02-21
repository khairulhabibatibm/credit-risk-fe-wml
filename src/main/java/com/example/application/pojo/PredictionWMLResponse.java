package com.example.application.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PredictionWMLResponse {

    @Setter @Getter private List<Prediction> predictions;
    
}
