package ultramodern.activity.besha;

import android.widget.ImageView;

public class BudgetModel {
    String name;
    String category;
    String amount;
    String fromdate;
    String todate;
    ImageView image;

    public BudgetModel(String name) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.fromdate = fromdate;
        this.todate = todate;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
