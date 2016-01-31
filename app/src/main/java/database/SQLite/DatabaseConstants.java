package database.SQLite;

public class DatabaseConstants
{

    public static final String DATABASE_NAME = "ticketbuschecker.db";

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE_CARD = "Card";
    public static final String DATABASE_TABLE_TAX = "Tax";
    public static final String DATABASE_TABLE_TRAVEL = "Travel";

}

