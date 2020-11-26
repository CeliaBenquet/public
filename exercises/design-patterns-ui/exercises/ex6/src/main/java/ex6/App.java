package ex6;

import static ex6.CapitalStrategyName.TERM;

public class App {

    public static void main(String[] args) {
        LoanFactory factory = new LoanFactory();
        Loan loan = factory.getLoan(TERM, );
    }

}
