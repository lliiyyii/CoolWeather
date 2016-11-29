package com.example.administrator.weather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class DBCoolWeather {
    public static final String DB_NAME = "cool_weather";
    public static final int VERSION = 1;
    private static DBCoolWeather dbCoolWeather;
    private SQLiteDatabase db;

    private DBCoolWeather(Context context){
        DBOpenHelper dbHelper = new DBOpenHelper(context,DB_NAME,null,VERSION);
        db= dbHelper.getWritableDatabase();
    }
    public synchronized static DBCoolWeather getInstance(Context context){
        if (dbCoolWeather==null){
            dbCoolWeather = new DBCoolWeather(context);

        }
        return dbCoolWeather;
    }

    public void saveProvince (MProvince province){
        if (province!=null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }
    public List<MProvince> loadProvince(){
        List<MProvince> list  = new ArrayList<>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                MProvince province = new MProvince();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public void saveCity (MCity city){
        if (city!=null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }
    public List<MCity> loadCity(int provinceId){
        List<MCity> list  = new ArrayList<>();
        Cursor cursor=db.query("City",null,"province_id = ? ",new String[]{String.valueOf(provinceId)},null,null,null);
        if (cursor.moveToFirst()){
            do{
                MCity city = new MCity();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public void saveCountry (MCountry country){
        if (country!=null){
            ContentValues values = new ContentValues();
            values.put("country_name",country.getCountryName());
            values.put("country_code",country.getCountryCode());
            values.put("cityId",country.getCityId());
            db.insert("Country",null,values);
        }
    }
    public List<MCountry> loadCountry(int cityId){
        List<MCountry> list  = new ArrayList<>();
        Cursor cursor=db.query("Country",null,"city_id=?",
                new String[]{String.valueOf(cityId)},null,null,null);
        if (cursor.moveToFirst()){
            do{
                MCountry country = new MCountry();
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
                country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
                country.setCityId(cityId);
                list.add(country);
            }while (cursor.moveToNext());
        }
        return list;
    }
}

