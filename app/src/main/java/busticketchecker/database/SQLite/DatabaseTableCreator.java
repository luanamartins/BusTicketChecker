package busticketchecker.database.SQLite;

import android.database.sqlite.SQLiteDatabase;

public class DatabaseTableCreator
{

    public void createTables(SQLiteDatabase db)
    {
        String createTableCardQuery = "CREATE TABLE Card (card_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, type TEXT)";
        String createTableTaxQuery = "CREATE TABLE Tax (tax_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, value FLOAT)";
        String createTableTravelQuery = "CREATE TABLE Travel (travel_id INTEGER PRIMARY KEY AUTOINCREMENT, date DATE, card_name TEXT, card_type TEXT, tax_name TEXT, tax_value FLOAT, value FLOAT)";

        db.execSQL(createTableCardQuery);
        db.execSQL(createTableTaxQuery);
        db.execSQL(createTableTravelQuery);
    }
}
