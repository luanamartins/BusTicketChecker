package busticketchecker.database.SQLite.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import busticketchecker.database.SQLite.DBHelper;
import busticketchecker.database.dao.BusTaxDAO;

public class TaxRepo
{
    private DBHelper helper;

    public TaxRepo(Context context)
    {
        helper = new DBHelper(context);
    }

    public int insert(BusTaxDAO taxDAO)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BusTaxDAO.KEY_NAME, taxDAO.getName());
        contentValues.put(BusTaxDAO.KEY_COST, taxDAO.getCost());

        long id = db.insert(BusTaxDAO.TABLE, null, contentValues);
        db.close();

        return (int) id;
    }

    public void delete(int id)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string

        db.delete(BusTaxDAO.TABLE, BusTaxDAO.KEY_ID + "= ?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void delete(String name)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string

        db.delete(BusTaxDAO.TABLE, BusTaxDAO.KEY_NAME + "= ?", new String[]{String.valueOf(name)});

        db.close();
    }

    public void update(BusTaxDAO taxDAO)
    {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BusTaxDAO.KEY_NAME, taxDAO.getName());
        values.put(BusTaxDAO.KEY_COST, taxDAO.getCost());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update("card", values, BusTaxDAO.KEY_ID + "= ?", new String[]{String.valueOf(taxDAO.getId())});
        db.close(); // Closing busticketchecker.database connection
    }

    public ArrayList<HashMap<String, String>> getTaxList()
    {
        //Open connection to read only
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                BusTaxDAO.KEY_ID + "," +
                BusTaxDAO.KEY_NAME + "," +
                BusTaxDAO.KEY_COST +
                " FROM " + BusTaxDAO.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> taxList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst())
        {
            do
            {
                HashMap<String, String> cards = new HashMap<String, String>();
                cards.put(BusTaxDAO.KEY_ID, cursor.getString(cursor.getColumnIndex(BusTaxDAO.KEY_ID)));
                cards.put(BusTaxDAO.KEY_NAME, cursor.getString(cursor.getColumnIndex(BusTaxDAO.KEY_NAME)));
                cards.put(BusTaxDAO.KEY_COST, cursor.getString(cursor.getColumnIndex(BusTaxDAO.KEY_COST)));
                taxList.add(cards);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taxList;

    }

    public BusTaxDAO getTaxById(int id)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                BusTaxDAO.KEY_ID + "," +
                BusTaxDAO.KEY_NAME + "," +
                BusTaxDAO.KEY_COST +
                " FROM " + BusTaxDAO.TABLE + " WHERE " +
                BusTaxDAO.KEY_ID + "=?";
        // It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        BusTaxDAO taxDAO = new BusTaxDAO();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst())
        {
            do
            {
                taxDAO.setId(cursor.getInt(cursor.getColumnIndex(BusTaxDAO.KEY_ID)));
                taxDAO.setName(cursor.getString(cursor.getColumnIndex(BusTaxDAO.KEY_NAME)));
                taxDAO.setCost(cursor.getFloat(cursor.getColumnIndex(BusTaxDAO.KEY_COST)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taxDAO;
    }


}
