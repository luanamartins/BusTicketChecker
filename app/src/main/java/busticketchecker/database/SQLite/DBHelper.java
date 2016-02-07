package busticketchecker.database.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    private DatabaseTableCreator creator;

    public DBHelper(Context context)
    {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
        creator = new DatabaseTableCreator();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        creator.createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
//        // Drop older table if existed, all data will be gone!!!
//        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE);
//
//        // Create tables again
//        onCreate(db);
    }

}
