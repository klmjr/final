package pkgApp.controller;



import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.apache.poi.ss.formula.functions.FinanceLib;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import javafx.beans.value.*;

import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

	private RetirementApp mainApp = null;
	@FXML
	private TextField txtSaveEachMonth;
	@FXML
	private TextField txtYearsToWork;
	@FXML
	private TextField txtAnnualReturnWorking;
	@FXML
	private TextField txtWhatYouNeedToSave;
	@FXML
	private TextField txtYearsRetired;
	@FXML
	private TextField txtAnnualReturnRetired;
	@FXML
	private TextField txtRequiredIncome;
	@FXML
	private TextField txtMonthlySSI;

	private HashMap<TextField, String> hmTextFieldRegEx = new HashMap<TextField, String>();

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Adding an entry in the hashmap for each TextField control I want to validate
		// with a regular expression
		// "\\d*?" - means any decimal number
		// "\\d*(\\.\\d*)?" means any decimal, then optionally a period (.), then
		// decmial
		hmTextFieldRegEx.put(txtYearsToWork, "^\\d*$");
		hmTextFieldRegEx.put(txtAnnualReturnWorking, "^[1-9]\\d*$");
		hmTextFieldRegEx.put(txtAnnualReturnRetired, "^[1-9]\\d*$");
		hmTextFieldRegEx.put(txtRequiredIncome, "^\\d*$");
		hmTextFieldRegEx.put(txtMonthlySSI, "^\\d*$");
		hmTextFieldRegEx.put(txtYearsRetired, "^\\d*$");
		

		// Check out these pages (how to validate controls):
		// https://stackoverflow.com/questions/30935279/javafx-input-validation-textfield
		// https://stackoverflow.com/questions/40485521/javafx-textfield-validation-decimal-value?rq=1
		// https://stackoverflow.com/questions/8381374/how-to-implement-a-numberfield-in-javafx-2-0
		// There are some examples on how to validate / check format

		Iterator it = hmTextFieldRegEx.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			TextField txtField = (TextField) pair.getKey();
			String strRegEx = (String) pair.getValue();

			txtField.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
						Boolean newPropertyValue) {
					// If newPropertyValue = true, then the field HAS FOCUS
					// If newPropertyValue = false, then field HAS LOST FOCUS
					if (!newPropertyValue) {
						if (!txtField.getText().matches(strRegEx)) {
							txtField.setText("");
							txtField.requestFocus();
						}
					}
				}
			});
		}

		//
		// TODO: Validate Working Annual Return %, accept only numbers and decimals
		// TODO: Validate Years retired, accepted only decimals
		// TODO: Validate Retired Annual Return %, accept only numbers and deciamls
		// TODO: Validate Required Income, accept only decimals
		// TODO: Validate Monthly SSI, accept only decimals
	}

	@FXML
	public void btnClear(ActionEvent event) {
		System.out.println("Clear pressed");

		// disable read-only controls
		txtSaveEachMonth.setDisable(true);
		txtWhatYouNeedToSave.setDisable(true);
		
		
		
		//hmTextFieldRegEx
		
		// Clear, enable txtYearsToWork
		//txtYearsToWork.clear();
		txtYearsToWork.setDisable(false);
		txtAnnualReturnWorking.setDisable(false);
		txtYearsRetired.setDisable(false);
		txtAnnualReturnRetired.setDisable(false);
		txtRequiredIncome.setDisable(false);
		txtMonthlySSI.setDisable(false);
		
		Iterator it = hmTextFieldRegEx.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			TextField txtField = (TextField) pair.getKey();
			String strRegEx = (String) pair.getValue();
			
			if(!txtField.isDisable()) {
				txtField.clear();
			}
			
		}
		
		
	    
		
		
		
		
		

		// TODO: Clear, enable the rest of the input controls. Hint! You already have a
		// HashMap of all the input controls....!!!!
	}
	
	
	

	@FXML
	public void btnCalculate() {
		double r = Double.parseDouble(txtAnnualReturnWorking.getText());
		double rr = Double.parseDouble(txtAnnualReturnRetired.getText());
		double yr = Double.parseDouble(txtYearsRetired.getText());
		double n = Double.parseDouble(txtYearsToWork.getText());
		double p = Double.parseDouble(txtMonthlySSI.getText());
		double f = Double.parseDouble(txtRequiredIncome.getText());
		boolean t = false;
		double y = FinanceLib.pmt(r, n, p, f, t);
		

		System.out.println("calculating");
		

		txtSaveEachMonth.setText(Double.toString(pkgCore.Retirement.MonthlySavings(n,r,yr,rr,f,p,t)));
		txtWhatYouNeedToSave.setText(Double.toString(pkgCore.Retirement.TotalAmountToSave(yr,rr,f,p,t)));

		// TODO: Calculate txtWhatYouNeedToSave value...
		// TODO: Then calculate txtSaveEachMonth, using amount from txtWhatYouNeedToSave
		// as input
	}
}