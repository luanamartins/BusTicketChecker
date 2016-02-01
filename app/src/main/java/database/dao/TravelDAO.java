package database.dao;

import java.util.Date;

public class TravelDAO
{
    public static final String TABLE = "Travel";

    public static final String KEY_ID = "travel_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "type";
    public static final String KEY_CARD_NAME = "card_name";
    public static final String KEY_CARD_TYPE = "card_type";
    public static final String KEY_TAX_NAME = "tax_name";
    public static final String KEY_COST = "cost";

    private int id;
    private Date date;
    private String type;
    private String cardName;
    private String cardType;
    private String taxName;
    private float cost;

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public void setCardName(String cardName)
    {
        this.cardName = cardName;
    }

    public String getCardName()
    {
        return cardName;
    }

    public void setCardType(String cardType)
    {
        this.cardType = cardType;
    }

    public String getCardType()
    {
        return cardType;
    }

    public void setTaxName(String taxName)
    {
        this.taxName = taxName;
    }

    public String getTaxName()
    {
        return taxName;
    }

    public void setCost(float cost)
    {
        this.cost = cost;
    }

    public float getCost()
    {
        return cost;
    }
}
