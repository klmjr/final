package pkgCore;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class Retirement {

	private static int iYearsToWork;
	private static double dAnnualReturnWorking;
	private static int iYearsRetired;
	private static double dAnnualReturnRetired;
	private static double dRequiredIncome;
	private static double dMonthlySSI;

	public Retirement(int iYearsToWork, double dAnnualReturnWorking, int iYearsRetired, double dAnnualReturnRetired,
			double dRequiredIncome, double dMonthlySSI) {
		super();
		this.iYearsToWork = iYearsToWork;
		this.dAnnualReturnWorking = dAnnualReturnWorking;
		this.iYearsRetired = iYearsRetired;
		this.dAnnualReturnRetired = dAnnualReturnRetired;
		this.dRequiredIncome = dRequiredIncome;
		this.dMonthlySSI = dMonthlySSI;
	}

	public boolean testMyWork(int iYearsToWork) {
		boolean a = false;
		if(iYearsToWork >= 0 && iYearsToWork <= 40) {
			a = true;
			
		}
		else {
			a = false;
		}
		return a;
	}
	
	public boolean testMyAnnual(double dAnnualReturnWorking){
		boolean a = false;
		if(dAnnualReturnWorking >= 0 && dAnnualReturnWorking <= .10 ) {
			a = true;
		}
		else {
			a = false;
		}
		return a;
	}
	public boolean testMyRetire(double iYearsRetired){
		boolean a = false;
		if(iYearsRetired >= 0 && iYearsRetired <= 20) {
			a = true;
		}
		else {
			a = false;
		}
		return a;
	}
	public boolean testMyRequired(double dRequiredIncome){
		boolean a = false;
		if(dRequiredIncome >= 2642 && dRequiredIncome <= 10000 ) {
			a = true;
		}
		else {
			a = false;
		}
		return a;
	}
	public boolean testMySSI(double dMonthlySSI){
		boolean a = false;
		if(dMonthlySSI >= 0 && dMonthlySSI <= 2642 ) {
			a = true;
		}
		else {
			a = false;
		}
		return a;
	}
	
	

	public static double MonthlySavings(double yWork, double wAnnualPct, double yRetire, double rAnnualPct, double rIncome, double mSSI, boolean t) {
		
		double pv = TotalAmountToSave(yRetire, rAnnualPct, rIncome, mSSI, t);
		
		double pmt = FinanceLib.pmt(wAnnualPct / 12, yWork * 12, pv, 0, t);
		pmt = Math.round(pmt * 100.0) / 100.0;
				
	    return pmt;
	}
	
	

	public static double TotalAmountToSave(double yRetire, double rAnnualPct, double rIncome, double mSSI, boolean t) {
		
		//TODO: Calculate the Total Amount Requried to save
		double pv = Math.round(FinanceLib.pv(rAnnualPct / 12, yRetire * 12, rIncome - mSSI, 0, t));
		
		pv = Math.round(pv * 100.0) / 100.0;
		return pv;
	}

	public static double PMT(double r, double n, double p, double f, boolean t) {
		//	r = Rate
		//	n = number of payments
		//	p = present value
		//	f = future value
		//	t = boolean... when interest is calculated... we're going to use FALSE
		return FinanceLib.pmt(r, n, p, f, t);
	}

	public static double PV(double r, double n, double y, double f, boolean t) {
		//	r = Rate.  7% would be expressed as...  0.07 / 12
		//	n = Number of payments.  Five years would be expressed as...  5 * 12...  or 60
		//	y = PMT amount
		//	f = Future value
		//	t = boolean... when interest is calculated... we're going to use FALSE
		return FinanceLib.pv(r / 12, n * 12, y, f, t);
	}

	public static int getiYearsToWork() {
		return iYearsToWork;
	}

	public void setiYearsToWork(int iYearsToWork) {
		this.iYearsToWork = iYearsToWork;
	}

	public static double getdAnnualReturnWorking() {
		return dAnnualReturnWorking;
	}

	public void setdAnnualReturnWorking(double dAnnualReturnWorking) {
		this.dAnnualReturnWorking = dAnnualReturnWorking;
	}

	public int getiYearsRetired() {
		return iYearsRetired;
	}

	public void setiYearsRetired(int iYearsRetired) {
		this.iYearsRetired = iYearsRetired;
	}

	public double getdAnnualReturnRetired() {
		return dAnnualReturnRetired;
	}

	public void setdAnnualReturnRetired(double dAnnualReturnRetired) {
		this.dAnnualReturnRetired = dAnnualReturnRetired;
	}

	public static double getdRequiredIncome() {
		return dRequiredIncome;
	}

	public void setdRequiredIncome(double dRequiredIncome) {
		this.dRequiredIncome = dRequiredIncome;
	}

	public static double getdMonthlySSI() {
		return dMonthlySSI;
	}

	public void setdMonthlySSI(double dMonthlySSI) {
		this.dMonthlySSI = dMonthlySSI;
	}
}
