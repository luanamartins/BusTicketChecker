package busticketchecker.database.dao;

public class BusCardDAO
{
    public static final String TABLE = "Card";

    public static final String KEY_ID = "card_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE = "type";

    private int id;
    private String name;
    private String type;

    public BusCardDAO(){}

    public BusCardDAO(String name, String type)
    {
        this.name = name;
        this.type = type;
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

}
