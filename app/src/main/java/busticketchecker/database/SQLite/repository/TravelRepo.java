package busticketchecker.database.SQLite.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import busticketchecker.database.SQLite.DBHelper;
import busticketchecker.database.dao.TravelDAO;

public class TravelRepo
{
    private DBHelper helper;

    public TravelRepo(Context context)
    {
        helper = new DBHelper(context);
    }

    public int insert(TravelDAO travelDAO)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TravelDAO.KEY_DATE, travelDAO.getDate().toString());
        contentValues.put(TravelDAO.KEY_TYPE, travelDAO.getTaxName());
        contentValues.put(TravelDAO.KEY_CARD_NAME, travelDAO.getCardName());
        contentValues.put(TravelDAO.KEY_CARD_TYPE, travelDAO.getCardType());
        contentValues.put(TravelDAO.KEY_TAX_NAME, travelDAO.getTaxName());
        contentValues.put(TravelDAO.KEY_COST, travelDAO.getCost());

        long id = db.insert(TravelDAO.TABLE, null, contentValues);
        db.close();

        return (int) id;
    }

    public void delete(int id)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string

        db.delete(TravelDAO.TABLE, TravelDAO.KEY_ID + "= ?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void update(TravelDAO travel)
    {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TravelDAO.KEY_DATE, travel.getDate().toString());
        contentValues.put(TravelDAO.KEY_TYPE, travel.getTaxName());
        contentValues.put(TravelDAO.KEY_CARD_NAME, travel.getCardName());
        contentValues.put(TravelDAO.KEY_CARD_TYPE, travel.getCardType());
        contentValues.put(TravelDAO.KEY_TAX_NAME, travel.getTaxName());
        contentValues.put(TravelDAO.KEY_COST, travel.getCost());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(TravelDAO.TABLE, contentValues, TravelDAO.KEY_ID + "= ?", new String[]{String.valueOf(travel.getId())});
        db.close(); // Closing busticketchecker.database connection
    }

    public ArrayList<HashMap<String, String>> getTravelList()
    {
        //Open connection to read only
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                TravelDAO.KEY_ID + "," +
                TravelDAO.KEY_TYPE + "," +
                TravelDAO.KEY_CARD_NAME + "," +
                TravelDAO.KEY_CARD_TYPE + "," +
                TravelDAO.KEY_TAX_NAME + "," +
                TravelDAO.KEY_COST + "," +
                " FROM " + TravelDAO.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst())
        {
            do
            {
                HashMap<String, String> travels = new HashMap<String, String>();
                travels.put(TravelDAO.KEY_ID, cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_ID)));

                travels.put(TravelDAO.KEY_DATE, cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_DATE)));
                travels.put(TravelDAO.KEY_TYPE, cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_TYPE)));
                travels.put(TravelDAO.KEY_CARD_NAME, cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_CARD_NAME)));
                travels.put(TravelDAO.KEY_CARD_TYPE, cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_CARD_TYPE)));
                travels.put(TravelDAO.KEY_TAX_NAME, cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_TAX_NAME)));
                travels.put(TravelDAO.KEY_COST, cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_COST)));
                studentList.add(travels);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

    public TravelDAO getTravelById(int id)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                TravelDAO.KEY_ID + "," +
                TravelDAO.KEY_TYPE + "," +
                TravelDAO.KEY_CARD_NAME + "," +
                TravelDAO.KEY_CARD_TYPE + "," +
                TravelDAO.KEY_TAX_NAME + "," +
                TravelDAO.KEY_COST + "," +
                " FROM " + TravelDAO.TABLE + " WHERE " +
                TravelDAO.KEY_ID + "=?";
        // It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        TravelDAO travelDAO = new TravelDAO();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst())
        {
            do
            {
                travelDAO.setId(cursor.getInt(cursor.getColumnIndex(TravelDAO.KEY_ID)));
                Date date = createDateObjectFromDateString(cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_DATE)));
                travelDAO.setDate(date);
                travelDAO.setType(cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_TYPE)));
                travelDAO.setCardName(cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_CARD_NAME)));
                travelDAO.setCardType(cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_CARD_TYPE)));
                travelDAO.setTaxName(cursor.getString(cursor.getColumnIndex(TravelDAO.KEY_TAX_NAME)));
                travelDAO.setCost(cursor.getFloat(cursor.getColumnIndex(TravelDAO.KEY_COST)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return travelDAO;
    }

    private Date createDateObjectFromDateString(String dateString)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try
        {
            date = dateFormat.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

}
