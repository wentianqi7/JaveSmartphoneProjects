package tianqiw.mortgageapp.model;

/**
 * this class is used to calculate the mortgage
 */
public class Arithmetic {
    private final int NUM_MONTH = 12;

    /**
     * calculate the monthly payment amount
     * @param loan loan amount
     * @param term mortgage term
     * @param rate interest rate
     * @return monthly payment
     */
    private double getMonthlyPay(
            int loan, int term, double rate) {
        rate /= (100.0 * (double) NUM_MONTH);
        int monthlyTerm = term * NUM_MONTH;
        double monthlyRate =
                (loan * rate) /
                        (1 - Math.pow(rate + 1, -monthlyTerm));
        return monthlyRate;
    }

    /**
     * calculate mortgage
     * @param model mortgage model
     * @return a mortgage model with calculated value
     */
    public Model calculateMortgage(Model model) {
        if (model == null) {
            return null;
        }

        int loan = model.getPurchasePrice() - model.getDownPayment();
        int term = model.getMortgageTerm();
        double rate = model.getInterestRate();
        double monthlyRate = getMonthlyPay(loan, term, rate);
        int month = model.getFirstPaymentMonth() - 1;

        if (month > 0) {
            model.setPayOffMonth(month);
            model.setPayOffYear(model.getFirstPaymentYear() + model.getMortgageTerm());
        } else {
            model.setPayOffMonth(NUM_MONTH);
            model.setPayOffYear(model.getFirstPaymentYear() + model.getMortgageTerm() - 1);
        }

        model.setTotalMonthPay(monthlyRate);
        model.setTotalPayment(monthlyRate * NUM_MONTH * model.getMortgageTerm());

        return model;
    }
}
