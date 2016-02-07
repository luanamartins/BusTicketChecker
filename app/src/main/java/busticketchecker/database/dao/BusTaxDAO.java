package busticketchecker.database.dao;

public class BusTaxDAO
{
    public static final String TABLE = "BusTax";

    public static final String KEY_ID = "tax_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_COST = "cost";

    private int id;
    private String name;
    private float cost;

    public BusTaxDAO()
    {
    }


    public BusTaxDAO(String name, float cost)
    {
        this.name = name;
        this.cost = cost;
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

    public void setCost(float cost)
    {
        this.cost = cost;
    }

    public float getCost()
    {
        return cost;
    }

}
