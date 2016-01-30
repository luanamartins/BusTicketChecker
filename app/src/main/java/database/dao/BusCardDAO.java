package database.dao;

public class BusCardDAO
{
    private String name;

    public BusCardDAO()
    {
    }

    public BusCardDAO(String name)
    {
        this.name = name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

}
