package busticketchecker.database.dao;

public class BusCardDAO
{
    public static final String TABLE = "Card";

    public static final String KEY_ID = "card_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE = "type";
    public static final String KEY_AMOUNT = "amount";

    private int id;
    private String name;
    private String type;
    private float amount;

    public BusCardDAO(){}

    public BusCardDAO(String name, String type, float amount)
    {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public void setAmount(float amount)
    {
        this.amount = amount;
    }

    public float getAmount()
    {
        return amount;
    }

}
