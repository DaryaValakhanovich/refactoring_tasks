package com.epam.engx.cleancode.dry.task1;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        double interest = 0;
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            if (AGE <= accountDetails.getAge()) {
                interest = getInterest(accountDetails, SENIOR_PERCENT);
            } else {
                interest = getInterest(accountDetails, INTEREST_PERCENT);
            }
        }
        return BigDecimal.valueOf(interest);
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private int durationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(from);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(to);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (endCalendar.get(Calendar.DAY_OF_YEAR) < startCalendar.get(Calendar.DAY_OF_YEAR))
            return diffYear - 1;
        return diffYear;
    }

    private double getInterest(AccountDetails accountDetails, double annualInterestRate) {
            //interest = (PrincipalAmount * DurationInYears * AnnualInterestRate) / 100
             return  accountDetails.getBalance().doubleValue()
                     * durationSinceStartDateInYears(accountDetails.getStartDate()) * annualInterestRate / 100;
    }

    private int durationSinceStartDateInYears(Date startDate) {
        return durationBetweenDatesInYears(startDate, new Date());
    }
}


