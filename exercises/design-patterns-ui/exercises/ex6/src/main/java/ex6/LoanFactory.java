package ex6;

import java.util.Date;

public class LoanFactory {

    public Loan getLoan(CapitalStrategyName strategy, CapitalStrategy capitalStrategy, double commitment, double outstanding, int riskTaking, Date maturity, Date expiry){
        switch (strategy) {
            case TERM:
                return new LoanTerm();
            case REVOLVER:
                return new LoanRevolver();
            case RCTL:
                return new LoanRCTL();
            default: throw new IllegalStateException("Loan doesn't exist.");
        }
    }
}
