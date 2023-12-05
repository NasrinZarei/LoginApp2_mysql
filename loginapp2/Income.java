package loginapp2;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * A class to represent income information.
 *
 */
public class Income implements Serializable {

    private int id;
    private String Userid;
    private LocalDate LocalDate;
    private float incomePrice;
    private String incomeSubject;
    private String typeCost;
    private float priceCost;
    private String costSubject;
    public int count = 1;

    public Income() {
    }

    /**
     * Constructor that takes all of the income information as parameters.
     *
     * @param Userid The identifier of the user to whom the income belongs.
     * @param LocalDate The date on which the income was received.
     * @param incomePrice The amount of the income.
     * @param incomeSubject A description of the income.
     * @param typeCost A category for the cost associated with the income.
     * @param priceCost The amount of the cost associated with the income.
     * @param costSubject A description of the cost associated with the income.
     */
    public Income(String Userid, LocalDate LocalDate, float incomePrice, String incomeSubject, String typeCost, float priceCost, String costSubject) {
        id = count;
        count++;
        this.Userid = Userid;
        this.LocalDate = LocalDate;
        this.incomePrice = incomePrice;
        this.incomeSubject = incomeSubject;
        this.typeCost = typeCost;
        this.priceCost = priceCost;
        this.costSubject = costSubject;

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String Userid) {
        this.Userid = Userid;
    }

    public LocalDate getLocalDate() {
        return LocalDate;
    }

    public float getIncomePrice() {
        return incomePrice;
    }

    public String getIncomSubject() {
        return incomeSubject;
    }

    public String getTypeCost() {
        return typeCost;
    }

    public float getPriceCost() {
        return priceCost;
    }

    public String getCostSubject() {
        return costSubject;
    }

    public void setLocalDate(LocalDate LocalDate) {
        this.LocalDate = LocalDate;
    }

    public void setIncomePrice(float incomePrice) {
        this.incomePrice = incomePrice;
    }

    public void setIncomeSubject(String incomeSubject) {
        this.incomeSubject = incomeSubject;
    }

    public void setTypeCost(String typeCost) {
        this.typeCost = typeCost;
    }

    public void setPriceCost(float priceCost) {
        this.priceCost = priceCost;
    }

    public void setCostSubject(String costSubject) {
        this.costSubject = costSubject;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
