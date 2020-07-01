package ultramodern.activity.RECYCLERTEST;

public class Planet {
    private int diameter;

    private int distancefromsun;

    private int gravity;

    private String planetname;

    public Planet(String paramString, int paramInt1, int paramInt2, int paramInt3) {
        this.planetname = paramString;
        this.distancefromsun = paramInt1;
        this.gravity = paramInt2;
        this.diameter = paramInt3;
    }

    public int getDiameter() { return this.diameter; }

    public int getDistancefromsun() { return this.distancefromsun; }

    public int getGravity() { return this.gravity; }

    public String getPlanetname() { return this.planetname; }

    public void setDiameter(int paramInt) { this.diameter = paramInt; }

    public void setDistancefromsun(int paramInt) { this.distancefromsun = paramInt; }

    public void setGravity(int paramInt) { this.gravity = paramInt; }

    public void setPlanetname(String paramString) { this.planetname = paramString; }
}
