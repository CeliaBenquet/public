/* Example from Refactoring to Patterns */

import java.util.Date;

/**
 * This class represents a Loan in a bank. There are different types of Loans: term loan, revolver loan and RCTL loan.
 * Several constructors are provided to create different loans of different types.
 */
public class Loan{

    CapitalStrategy capitalStrategy;
    static double commitment;
    double outstanding;
    //int customerRating;
    static Date maturity;
    Date expiry;
    static int riskTaking;

    public Loan(CapitalStrategy capitalStrategy, double commitment, double outstanding, int riskTaking, Date maturity, Date expiry){
        this.commitment = commitment;
        this.outstanding = outstanding;
        this.riskTaking = riskTaking;
        this.maturity = maturity;
        this.expiry = expiry;
        this.capitalStrategy = capitalStrategy;

        if (capitalStrategy == null){
            if(expiry == null)
                this.capitalStrategy = new CapitalStrategyTermLoan();
            else if (maturity == null)
                this.capitalStrategy = new CapitalStrategyRevolver();
            else
                this.capitalStrategy = new CapitalStrategyRCTL();
        }
    }

    public static Loan createTermLoan(double commitment, int riskTaking, Date maturity){
        return new Loan(null, commitment, 0.00, riskTaking, maturity, null);
    }

    public static Loan createRevolverLoan(double commitment, double outstanding, int customerRating, Date maturity, Date expiry){
        return new Loan(null, commitment, 0.00, riskTaking, maturity, null);
    }

    // Constructor for Revolver loan
    public Loan createRevolverLoan(double commitment, int riskTaking, Date maturity, Date expiry){
        return new Loan(null, commitment, 0.00, riskTaking, maturity, expiry);
    }

    public Loan createRCTLLoan(CapitalStrategy capitalStrategy, double commitment, int riskTaking, Date maturity, Date expiry){
        return new Loan(null, commitment, 0.00, riskTaking, maturity, null);
    }

}

// The following classes are for illustration purpose of this exercise only. Don't do this.
class CapitalStrategy {
    
}

class CapitalStrategyTermLoan extends CapitalStrategy{
    // fully paid by the maturity date

}

class CapitalStrategyRevolver extends CapitalStrategy{
    // revolving credit : spending limit and expiry date

    
}

class CapitalStrategyRCTL extends CapitalStrategy{
    // revolver that transform into a term loan when the revolver expires

}

