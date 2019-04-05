package patternsPackage;

public class PersonalLoanCalculator {

	private int ammountBorrowed;
	private int paymentFrequency;
	private double interestRate;
	private int timePeriod;
	private double answer;

	public PersonalLoanCalculator() {
	}

	public void setAmmountBorrowd(int borrowed) {
		this.ammountBorrowed = borrowed;
	}

	public void setPaymentFrequency(int frequency) {
		this.paymentFrequency = frequency;
	}

	public void setInterestRate(double rate) {
		this.interestRate = rate;
	}

	public void setTimePeriod(int time) {
		this.timePeriod = time;
	}

	public int getAmmountBorrowed() {
		return this.ammountBorrowed;
	}

	public int getPaymentFrequency() {
		return this.paymentFrequency;
	}

	public double getInterestRate() {
		return this.interestRate;
	}

	public double getAnswer() throws Exception {
		// M = P(J/(1 - (1 + J)^-N))
		// M = Payment
		// P = borrowed money
		// J = Interest Rate / Number of payments (period * number of years)
		// N = period * number of years
		
		try{
		this.answer = 0;
		double N = (this.timePeriod * this.paymentFrequency);
		double JTest = (this.interestRate / 100);
		double J = (JTest / N);
		double finish = Math.pow((1 + J), (-1 * N));

		this.answer = this.ammountBorrowed * (J / (1 - finish));

		return this.answer;
		}
		
		catch(Exception ex){
			throw new Exception("Exception",ex);
		}
	}

}
