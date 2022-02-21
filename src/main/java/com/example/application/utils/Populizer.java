package com.example.application.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Populizer {

    private Map<String,List<String>> strItems;
    private Map<String,List<Integer>> intItems;

    private static Populizer instance;

    private Populizer(){
        strItems = new HashMap<>();
        intItems = new HashMap<>();

        // init strItems
        List<String> checkingStatusList = new ArrayList<>();
        checkingStatusList.add("less_0");
        checkingStatusList.add("0_to_200");
        checkingStatusList.add("no_checking");
        checkingStatusList.add("greater_200");
        strItems.put("CheckingStatus",checkingStatusList);

        List<String> creditHistoryList = new ArrayList<>();
        creditHistoryList.add("all_credit_paid_back");
        creditHistoryList.add("credits_paid_to_date");
        creditHistoryList.add("no_credits");
        creditHistoryList.add("outstanding_credit");
        creditHistoryList.add("prior_payments_delayed");
        strItems.put("CreditHistory", creditHistoryList);

        List<String> loanPurposeList = new ArrayList<>();
        loanPurposeList.add("appliances");
        loanPurposeList.add("business");
        loanPurposeList.add("car_new");
        loanPurposeList.add("car_used");
        loanPurposeList.add("education");
        loanPurposeList.add("furniture");
        loanPurposeList.add("other");
        loanPurposeList.add("radio_tv");
        loanPurposeList.add("repairs");
        loanPurposeList.add("retraining");
        loanPurposeList.add("vacation");
        strItems.put("LoanPurpose", loanPurposeList);

        List<String> existingSavingList = new ArrayList<>();
        existingSavingList.add("100_to_500");
        existingSavingList.add("500_to_1000");
        existingSavingList.add("greater_1000");
        existingSavingList.add("less_100");
        existingSavingList.add("unknown");
        strItems.put("ExistingSavings", existingSavingList);

        List<String> employmentDurationList = new ArrayList<>();
        employmentDurationList.add("1_to_4");
        employmentDurationList.add("4_to_7");
        employmentDurationList.add("greater_7");
        employmentDurationList.add("less_1");
        employmentDurationList.add("unemployed");
        strItems.put("EmploymentDuration", employmentDurationList);

        List<Integer> installmentPercentList = new ArrayList<>();
        installmentPercentList.add(1);
        installmentPercentList.add(2);
        installmentPercentList.add(3);
        installmentPercentList.add(4);
        installmentPercentList.add(5);
        installmentPercentList.add(6);
        intItems.put("InstallmentPercent", installmentPercentList);

        List<String> othersOnLoanList = new ArrayList<>();
        othersOnLoanList.add("co-applicant");
        othersOnLoanList.add("guarantor");
        othersOnLoanList.add("none");
        strItems.put("OthersOnLoan", othersOnLoanList);

        List<String> genderList = new ArrayList<>();
        genderList.add("female");
        genderList.add("male");
        strItems.put("Gender", genderList);

        List<Integer> currentResidenceDurationList = new ArrayList<>();
        currentResidenceDurationList.add(1);
        currentResidenceDurationList.add(2);
        currentResidenceDurationList.add(3);
        currentResidenceDurationList.add(4);
        currentResidenceDurationList.add(5);
        currentResidenceDurationList.add(6);
        intItems.put("CurrentResidenceDuration", currentResidenceDurationList);

        List<String> ownsPropertyList = new ArrayList<>();
        ownsPropertyList.add("car_other");
        ownsPropertyList.add("real_estate");
        ownsPropertyList.add("savings_insurance");
        ownsPropertyList.add("unknown");
        strItems.put("OwnsProperty", ownsPropertyList);

        List<String> installmentPlansList = new ArrayList<>();
        installmentPlansList.add("bank");
        installmentPlansList.add("stores");
        installmentPlansList.add("none");
        strItems.put("InstallmentPlans", installmentPlansList);

        List<String> housingList = new ArrayList<>();
        housingList.add("free");
        housingList.add("own");
        housingList.add("rent");
        strItems.put("Housing", housingList);

        List<Integer> existingCreditCountList = new ArrayList<>();
        existingCreditCountList.add(1);
        existingCreditCountList.add(2);
        existingCreditCountList.add(3);
        existingCreditCountList.add(4);
        intItems.put("ExistingCreditsCount", existingCreditCountList);

        List<Integer> dependentsList = new ArrayList<>();
        dependentsList.add(1);
        dependentsList.add(2);
        dependentsList.add(3);
        intItems.put("Dependents", dependentsList);

        List<String> telephoneList = new ArrayList<>();
        telephoneList.add("yes");
        telephoneList.add("none");
        strItems.put("Telephone", telephoneList);

        List<String> foreignWorkerList = new ArrayList<>();
        foreignWorkerList.add("yes");
        foreignWorkerList.add("no");
        strItems.put("ForeignWorker", foreignWorkerList);

        List<String> joblist = new ArrayList<>();
        joblist.add("management_self-employed");
        joblist.add("skilled");
        joblist.add("unemployed");
        joblist.add("unskilled");
        strItems.put("Job", joblist);


    }

    public static Populizer getInstance(){
        if(instance==null){
            instance = new Populizer();
        }
        return instance;
    }

    public List<String> getStrItems(String key){
        return strItems.get(key);
    }

    public List<Integer> getIntItems(String key){
        return intItems.get(key);
    }

    public String getRandomStrItems(String key){
        List<String> items = strItems.get(key);
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }

    public Integer getRandomIntItems(String key){
        Random random = new Random();
        if(key.equals("Age")){
            return random.nextInt(60);
        }else if(key.equals("Amount")){
            return random.nextInt(50000);
        }else if(key.equals("Duration")){
            return random.nextInt(36);
        }

        List<Integer> items = intItems.get(key);
        return items.get(random.nextInt(items.size()));
    }


    
}
