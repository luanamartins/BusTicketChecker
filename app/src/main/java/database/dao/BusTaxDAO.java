package database.dao;

public class BusTaxDAO
{
    private String name;
    private float value;

    public BusTaxDAO(String name, float value)
    {
        this.name = name;
        this.value = value;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setValue(float value)
    {
        this.value = value;
    }

    public float getValue()
    {
        return value;
    }

}
