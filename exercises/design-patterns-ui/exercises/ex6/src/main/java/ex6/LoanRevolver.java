package ex6;

import java.util.Date;

public class LoanRevolver extends Loan {

    // revolver loan
    public LoanRevolver(double commitment, int riskTaking, Date maturity, Date expiry){
        super(null, commitment, 0.00, riskTaking, maturity, expiry);
    }

    // revolver loan
    public LoanRevolver(double commitment, double outstanding, int customerRating, Date maturity, Date expiry){
        super(null, commitment, outstanding, customerRating, maturity, expiry);
    }


}


