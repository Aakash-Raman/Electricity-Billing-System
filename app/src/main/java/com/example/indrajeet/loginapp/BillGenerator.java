package com.example.indrajeet.loginapp;



/**
 * Created by indrajeet on 13/03/18.
 */

public class BillGenerator {
   private  static double uc,amt,famt,tax;
    public BillGenerator(double uc1) {
        uc= uc1;
    }


    public static double main () {
        //declare float


        //Declare input as scanner

        //Take inputs

        //calculate
        if (uc >= 0 && uc <= 100)
            amt = uc * 1;
        else {
            if (uc > 100 && uc <= 200)
                amt = ((uc - 100) * 2) + 100;
            else {
                if (uc > 200 && uc <= 300)
                    amt = ((uc - 200) * 3) + 300;
                else {
                    if (uc > 300 && uc <= 500)
                        amt = ((uc - 300) * 4) + 600;
                    else
                        amt = ((uc - 500) * 5) + 1400;
                }
            }
        }

        //add tax.
        tax = (amt * 10) / 100;
        famt = amt + 50 + tax;

        //print
       /* System.out.println("-----------------------");
        System.out.println("Electricity Bill");
        System.out.println("-----------------------");
        System.out.println("Units Charge "+amt);
        System.out.println("Meter Charge  50");
        System.out.println("Tax          "+tax);
        System.out.println("-----------------------");
        System.out.println("Total        "+famt);
        System.out.println("-----------------------");*/


         return  famt;
    }


}
