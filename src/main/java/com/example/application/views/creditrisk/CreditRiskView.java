package com.example.application.views.creditrisk;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import java.util.ArrayList;
import java.util.List;

import com.example.application.pojo.InputData;
import com.example.application.pojo.PredictionDTO;
import com.example.application.pojo.PredictionInput;
import com.example.application.pojo.PredictionWMLResponse;
import com.example.application.service.CreditRiskService;
import com.example.application.utils.Populizer;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Mono;

@Route(value = "creditrisk", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Credit Risk")
public class CreditRiskView extends Div {

    private Select<String> checkingStatus = new Select<>();
    private TextField loanDuration = new TextField("Loan Duration (Month)");
    private Select<String> creditHistory = new Select<>();
    private Select<String> loanPurpose = new Select<>();
    private TextField loanAmount = new TextField("Loan Amount (USD)");
    private Select<String> existingSaving = new Select<>();
    private Select<String> employementDuration = new Select<>();
    private Select<Integer> installmentPercent = new Select<>();
    private Select<String> gender = new Select<>();
    private Select<String> othersOnLoan = new Select<>();
    private Select<Integer> currentResidenceDuration = new Select<>();
    private Select<String> ownsProperty = new Select<>();
    private TextField age = new TextField("Age");
    private Select<String> installmentPlans = new Select<>();
    private Select<String> housing = new Select<>();
    private Select<Integer> existingCreditCount = new Select<>();
    private Select<Integer> dependents = new Select<>();
    private Select<String> telephone = new Select<>();
    private Select<String> foreignWorker = new Select<>();
    private Select<String> job = new Select<>();

    private TextField result = new TextField("Predicted Outcome");
    private TextField score = new TextField("Confidence");


    private Button cancel = new Button("Cancel");
    private Button submit = new Button("Predict");
    private Button randomize = new Button("Randomize");

    Populizer populizer = Populizer.getInstance();

    @Autowired
    private CreditRiskService service;

    public CreditRiskView() {
        addClassName("credit-risk-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        add(createResultLayout());

        cancel.addClickListener(e -> {
            Notification.show("Not implemented");
        });
        submit.addClickListener(e -> {
            PredictionDTO dto = new PredictionDTO();

            InputData inputData = new InputData();
            List<String> listOfFields = new ArrayList<>();
            listOfFields.add("CheckingStatus");
            listOfFields.add("LoanDuration");
            listOfFields.add("CreditHistory");
            listOfFields.add("LoanPurpose");
            listOfFields.add("LoanAmount");
            listOfFields.add("ExistingSavings");
            listOfFields.add("EmploymentDuration");
            listOfFields.add("InstallmentPercent");
            listOfFields.add("Sex");
            listOfFields.add("OthersOnLoan");
            listOfFields.add("CurrentResidenceDuration");
            listOfFields.add("OwnsProperty");
            listOfFields.add("Age");
            listOfFields.add("InstallmentPlans");
            listOfFields.add("Housing");
            listOfFields.add("ExistingCreditsCount");
            listOfFields.add("Job");
            listOfFields.add("Dependents");
            listOfFields.add("Telephone");
            listOfFields.add("ForeignWorker");

            List<List<Object>> listOfValuesHolder = new ArrayList<>();
            List<Object> listOfValues = new ArrayList<>();

            listOfValues.add(checkingStatus.getValue());
            listOfValues.add(Integer.valueOf(loanDuration.getValue()));
            listOfValues.add(creditHistory.getValue());
            listOfValues.add(loanPurpose.getValue());
            listOfValues.add(Integer.valueOf(loanAmount.getValue()));
            listOfValues.add(existingSaving.getValue());
            listOfValues.add(employementDuration.getValue());
            listOfValues.add(installmentPercent.getValue());
            listOfValues.add(gender.getValue());
            listOfValues.add(othersOnLoan.getValue());
            listOfValues.add(currentResidenceDuration.getValue());
            listOfValues.add(ownsProperty.getValue());
            listOfValues.add(Integer.valueOf(age.getValue()));
            listOfValues.add(installmentPlans.getValue());
            listOfValues.add(housing.getValue());
            listOfValues.add(existingCreditCount.getValue());
            listOfValues.add(job.getValue());
            listOfValues.add(dependents.getValue());
            listOfValues.add(telephone.getValue());
            listOfValues.add(foreignWorker.getValue());

            listOfValuesHolder.add(listOfValues);

            inputData.setFields(listOfFields);
            inputData.setValues(listOfValuesHolder);

            List<InputData> listInput = new ArrayList<>();
            listInput.add(inputData);

            PredictionInput predictionInput = new PredictionInput();
            predictionInput.setInput_data(listInput);

            // dto.setCheckingStatus(checkingStatus.getValue());
            // dto.setLoanDuration(Integer.valueOf(loanDuration.getValue()));
            // dto.setCreditHistory(creditHistory.getValue());
            // dto.setLoanPurpose(loanPurpose.getValue());
            // dto.setLoanAmount(Integer.valueOf(loanAmount.getValue()));
            // dto.setExistingSavings(existingSaving.getValue());
            // dto.setEmploymentDuration(employementDuration.getValue());
            // dto.setInstallmentPercent(installmentPercent.getValue());
            // dto.setSex(gender.getValue());
            // dto.setOthersOnLoan(othersOnLoan.getValue());
            // dto.setCurrentResidenceDuration(currentResidenceDuration.getValue());
            // dto.setOwnsProperty(ownsProperty.getValue());
            // dto.setAge(Integer.valueOf(age.getValue()));
            // dto.setInstallmentPlans(installmentPlans.getValue());
            // dto.setHousing(housing.getValue());
            // dto.setExistingCreditsCount(existingCreditCount.getValue());
            // dto.setJob(job.getValue());
            // dto.setDependents(dependents.getValue());
            // dto.setTelephone(telephone.getValue());
            // dto.setForeignWorker(foreignWorker.getValue());

            try {
                Mono<PredictionWMLResponse> res = service.getPrediction(predictionInput);

                PredictionWMLResponse predict = res.block();
                List<Object> predictionValues = predict.getPredictions().get(0).getValues().get(0);
                List<Double> listOfScores = (List<Double>) predictionValues.get(1);
                result.setValue(predictionValues.get(0).toString());
                score.setValue(String.valueOf(listOfScores.get(0)));    
            } catch (Exception err) {
                //TODO: handle exception
                err.printStackTrace();
            }
            


        });

        randomize.addClickListener(e -> {
            checkingStatus.setValue(populizer.getRandomStrItems("CheckingStatus"));
            creditHistory.setValue(populizer.getRandomStrItems("CreditHistory"));
            loanPurpose.setValue(populizer.getRandomStrItems("LoanPurpose"));
            existingSaving.setValue(populizer.getRandomStrItems("ExistingSavings"));
            employementDuration.setValue(populizer.getRandomStrItems("EmploymentDuration"));
            installmentPercent.setValue(populizer.getRandomIntItems("InstallmentPercent"));
            othersOnLoan.setValue(populizer.getRandomStrItems("OthersOnLoan"));
            gender.setValue(populizer.getRandomStrItems("Gender"));
            currentResidenceDuration.setValue(populizer.getRandomIntItems("CurrentResidenceDuration"));
            ownsProperty.setValue(populizer.getRandomStrItems("OwnsProperty"));
            installmentPlans.setValue(populizer.getRandomStrItems("InstallmentPlans"));
            housing.setValue(populizer.getRandomStrItems("Housing"));
            existingCreditCount.setValue(populizer.getRandomIntItems("ExistingCreditsCount"));
            dependents.setValue(populizer.getRandomIntItems("Dependents"));
            telephone.setValue(populizer.getRandomStrItems("Telephone"));
            foreignWorker.setValue(populizer.getRandomStrItems("ForeignWorker"));
            job.setValue(populizer.getRandomStrItems("Job"));
            age.setValue(String.valueOf(populizer.getRandomIntItems("Age")));
            loanAmount.setValue(String.valueOf(populizer.getRandomIntItems("Amount")));
            loanDuration.setValue(String.valueOf(populizer.getRandomIntItems("Duration")));
        });
    }

    private Component createTitle() {
        return new H3("Credit Risk");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        
        checkingStatus.setPlaceholder("CheckingStatus");
        checkingStatus.setLabel("Checking Status");
        checkingStatus.setItems(populizer.getStrItems("CheckingStatus"));

        creditHistory.setLabel("Credit History");
        creditHistory.setPlaceholder("CreditHistory");
        creditHistory.setItems(populizer.getStrItems("CreditHistory"));
        
        loanPurpose.setLabel("Loan Purpose");
        loanPurpose.setPlaceholder("LoanPurpose");
        loanPurpose.setItems(populizer.getStrItems("LoanPurpose"));

        existingSaving.setLabel("Existing Savings (USD)");
        existingSaving.setPlaceholder("ExistingSavings");
        existingSaving.setItems(populizer.getStrItems("ExistingSavings"));

        employementDuration.setLabel("Employment Duration (year)");
        employementDuration.setPlaceholder("EmploymentDuration");
        employementDuration.setItems(populizer.getStrItems("EmploymentDuration"));

        installmentPercent.setLabel("Installlment Percent");
        installmentPercent.setPlaceholder("InstallmentPercent");
        installmentPercent.setItems(populizer.getIntItems("InstallmentPercent"));

        othersOnLoan.setLabel("Others on Loan");
        othersOnLoan.setPlaceholder("OthersOnLoan");
        othersOnLoan.setItems(populizer.getStrItems("OthersOnLoan"));

        gender.setLabel("Gender");
        gender.setPlaceholder("Gender");
        gender.setItems(populizer.getStrItems("Gender"));

        currentResidenceDuration.setLabel("Current Residence Duration");
        currentResidenceDuration.setPlaceholder("Current Residence Duration");
        currentResidenceDuration.setItems(populizer.getIntItems("CurrentResidenceDuration"));

        ownsProperty.setLabel("Owns Property");
        ownsProperty.setPlaceholder("OwnsProperty");
        ownsProperty.setItems(populizer.getStrItems("OwnsProperty"));

        installmentPlans.setLabel("Installment Plans");
        installmentPlans.setItems(populizer.getStrItems("InstallmentPlans"));
        installmentPlans.setPlaceholder("InstallmentPlans");

        housing.setLabel("Housing");
        housing.setPlaceholder("Housing");
        housing.setItems(populizer.getStrItems("Housing"));

        existingCreditCount.setLabel("Existing Credits Count");
        existingCreditCount.setPlaceholder("ExistingCreditsCount");  
        existingCreditCount.setItems(populizer.getIntItems("ExistingCreditsCount"));

        dependents.setLabel("Dependents");
        dependents.setPlaceholder("Dependents");
        dependents.setItems(populizer.getIntItems("Dependents"));

        telephone.setLabel("Telephone");
        telephone.setPlaceholder("Telephone");
        telephone.setItems(populizer.getStrItems("Telephone"));

        foreignWorker.setLabel("Foreign Worker");
        foreignWorker.setPlaceholder("ForeignWorker");
        foreignWorker.setItems(populizer.getStrItems("ForeignWorker"));

        job.setLabel("Job");
        job.setPlaceholder("Job");
        job.setItems(populizer.getStrItems("Job"));

        formLayout.add(checkingStatus,loanDuration,creditHistory,loanPurpose,loanAmount,existingSaving,employementDuration,installmentPercent,
            gender,othersOnLoan,currentResidenceDuration,ownsProperty,age,installmentPlans,housing,existingCreditCount,dependents,
            telephone,foreignWorker,job);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        randomize.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        buttonLayout.add(submit);
        buttonLayout.add(cancel);
        buttonLayout.add(randomize);
        return buttonLayout;
    }

    private Component createResultLayout(){
        HorizontalLayout resultLayout = new HorizontalLayout();

        result.setReadOnly(true);
        result.setWidthFull();
        result.setHeight("100px");
        result.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        score.setReadOnly(true);
        score.setWidthFull();
        score.setHeight("100px");
        score.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);


        resultLayout.add(result);
        resultLayout.add(score);

        return resultLayout;
    }

}
