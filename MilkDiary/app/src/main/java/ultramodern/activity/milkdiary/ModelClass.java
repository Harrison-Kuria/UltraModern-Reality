package ultramodern.activity.milkdiary;

public class ModelClass {
    private int amount;
    private String SMS;
    private String date;


    public ModelClass(int amount, String SMS, String date){
        this.amount=amount;
        this.SMS=SMS;
        this.date=date;
    }


    //defining getters and setters
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount){

        this.amount=amount;
    }
    public String getSMS(){
        return SMS;
    }
    public String setSMS(String SMS){
        this.SMS=SMS;
        return SMS;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }

}

