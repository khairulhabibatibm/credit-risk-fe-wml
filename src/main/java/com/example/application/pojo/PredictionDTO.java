package com.example.application.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class PredictionDTO {

    @JsonProperty("CheckingStatus")
    @Setter @Getter private String checkingStatus;
    @JsonProperty("LoanDuration")
    @Setter @Getter private Integer loanDuration;
    @JsonProperty("CreditHistory")
    @Setter @Getter private String creditHistory;
    @JsonProperty("LoanPurpose")
    @Setter @Getter private String loanPurpose;
    @JsonProperty("LoanAmount")
    @Setter @Getter private Integer loanAmount;
    @JsonProperty("ExistingSavings")
    @Setter @Getter private String existingSavings;
    @JsonProperty("EmploymentDuration")
    @Setter @Getter private String employmentDuration;
    @JsonProperty("InstallmentPercent")
    @Setter @Getter private Integer installmentPercent;
    @JsonProperty("Sex")
    @Setter @Getter private String sex;
    @JsonProperty("OthersOnLoan")
    @Setter @Getter private String othersOnLoan;
    @JsonProperty("CurrentResidenceDuration")
    @Setter @Getter private Integer currentResidenceDuration;
    @JsonProperty("OwnsProperty")
    @Setter @Getter private String ownsProperty;
    @JsonProperty("Age")
    @Setter @Getter private Integer age;
    @JsonProperty("InstallmentPlans")
    @Setter @Getter private String installmentPlans;
    @JsonProperty("Housing")
    @Setter @Getter private String housing;
    @JsonProperty("ExistingCreditsCount")
    @Setter @Getter private Integer existingCreditsCount;
    @JsonProperty("Job")
    @Setter @Getter private String job;
    @JsonProperty("Dependents")
    @Setter @Getter private Integer dependents;
    @JsonProperty("Telephone")
    @Setter @Getter private String telephone;
    @JsonProperty("ForeignWorker")
    @Setter @Getter private String foreignWorker;
    
}
