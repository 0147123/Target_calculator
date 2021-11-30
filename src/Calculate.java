public class Calculate {
    private DataStore valid;

    public Calculate(DataStore valid){
        this.valid = valid;
    }

    //cal cgpa

    //cal tar
    public String tar(){
        String string;
        double ans;
        ans = (valid.getDesire_cgpa()*(valid.getRemainCred()+valid.getCompleteCred())*1.0-(valid.getCurrCgpa()*valid.getCompleteCred()))/valid.getRemainCred();
        string = String.format("%s%.2f", "You need this cgpa in remaining cred: ",  ans);
        if (ans <4.3)
            return string;
        else
            return "give up la!\nYou can't achieve the goals";
    }
}
