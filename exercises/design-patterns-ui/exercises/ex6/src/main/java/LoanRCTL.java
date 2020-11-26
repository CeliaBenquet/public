package ex6;

import java.util.Date;

public class LoanRCTL extends Loan {

    // RCTL loan
    public LoanRCTL(CapitalStrategy capitalStrategy, double commitment, int riskTaking, Date maturity, Date expiry){
        super(capitalStrategy, commitment, 0.00, riskTaking, maturity, expiry);
    }

}
