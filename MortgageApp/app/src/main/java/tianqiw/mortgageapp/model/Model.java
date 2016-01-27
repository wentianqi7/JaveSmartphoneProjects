package tianqiw.mortgageapp.model;

public class Model {
    private int purchasePrice;
    private int downPayment;
    private int mortgageTerm;
    private double interestRate;
    private int firstPaymentYear;
    private int firstPaymentMonth;
    private String time;
    private double totalMonthPay;
    private double totalPayment;
    private int payOffYear;
    private int payOffMonth;

    // getters and setters for purchase price
    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    // getters and setters for down payment
    public int getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(int downPayment) {
        this.downPayment = downPayment;
    }

    // getters and setters for mortgage term
    public int getMortgageTerm() {
        return mortgageTerm;
    }

    public void setMortgageTerm(int mortgageTerm) {
        this.mortgageTerm = mortgageTerm;
    }

    // getters and setters for interest rate
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // getters and setters for first payment year
    public int getFirstPaymentYear() {
        return firstPaymentYear;
    }

    public void setFirstPaymentYear(int firstPaymentYear) {
        this.firstPaymentYear = firstPaymentYear;
    }

    // getters and setters for first payment month
    public int getFirstPaymentMonth() {
        return firstPaymentMonth;
    }

    public void setFirstPaymentMonth(int firstPaymentMonth) {
        this.firstPaymentMonth = firstPaymentMonth;
    }

    // getters and setters for total month pay
    public double getTotalMonthPay() {
        return totalMonthPay;
    }

    public void setTotalMonthPay(double totalMonthPay) {
        this.totalMonthPay = totalMonthPay;
    }

    // getters and setters for total pay
    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    // getters and setters for payoff year
    public int getPayOffYear() {
        return payOffYear;
    }

    public void setPayOffYear(int payOffYear) {
        this.payOffYear = payOffYear;
    }

    // getters and setters for payoff month
    public int getPayOffMonth() {
        return payOffMonth;
    }

    public void setPayOffMonth(int payOffMonth) {
        this.payOffMonth = payOffMonth;
    }

    // getters and setters for time
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
