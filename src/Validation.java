import java.util.Scanner;
import java.util.regex.Pattern;

public class Validation {
    private double desire_cgpa;
    private double currCgpa;
    private int completeCred;
    private int remainCred;

    private boolean commonCheck(String buffer){

        //Validation check - null input
        //if user input null value, user need to re-enter the value
        if(buffer.isEmpty()){
            return false;
        }

        return true;
    }

    public boolean checkCgpa(String buffer, int id) {
        double douTemp;

        if (!this.commonCheck(buffer))
            return false;


        //validation check - data type
        //if user input wrong data type, user need to re-enter the value
        try{
            douTemp = Double.parseDouble(buffer);    //convert string type to integer type
        }
        catch (NumberFormatException ex){
            return false;
        }

        //validation check - number range
        //the input should larger than 0
        if ((douTemp >= 0) && (douTemp <= 4.3)) {
            switch (id){
                case 1:
                    desire_cgpa = douTemp;
                    break;
                case 2:
                    currCgpa = douTemp;
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean checkCred(String buffer, int id){
        int intTemp;
        if (!this.commonCheck(buffer))
            return false;

        //validation check - data type
        //if user input wrong data type, user need to re-enter the value
        try{
            intTemp = Integer.parseInt(buffer);    //convert string type to integer type
        }
        catch (NumberFormatException ex){
            return false;
        }

        //validation check - number range
        //the input should larger than 0
        if ((intTemp >= 0) && (intTemp <= 100)){
            switch (id){
                case 1:
                    completeCred = intTemp;
                    break;
                case 2:
                    remainCred = intTemp;
                    break;
            }
            return true;
        }
        else{
            System.out.println("\tInvalid input: please input a suitable range!");
            return false;
        }
    }

    public double getCurrCgpa() {
        return currCgpa;
    }

    public double getDesire_cgpa() {
        return desire_cgpa;
    }

    public int getCompleteCred(){
        return completeCred;
    }

    public int getRemainCred() {
        return remainCred;
    }
}
