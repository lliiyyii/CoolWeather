package com.example.administrator.weather;

import android.text.TextUtils;


import android.text.TextUtils;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class HttpUtility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(DBCoolWeather dbCoolWeather, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    MProvince province = new MProvince();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);      // 将解析出来的数据存储到Province表
                    dbCoolWeather.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**   * 解析和处理服务器返回的市级数据   */
    public static boolean handleCitiesResponse(DBCoolWeather dbCoolWeather, String response,int provinceId){
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    MCity city = new MCity();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);      // 将解析出来的数据存储到City表
                    dbCoolWeather.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(DBCoolWeather dbCoolWeather, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    MCountry country = new MCountry();
                    country.setCountryCode(array[0]);
                    country.setCountryName(array[1]);
                    country.setCityId(cityId);      // 将解析出来的数据存储到County表
                    dbCoolWeather.saveCountry(country);
                }
                return true;
            }
        }
        return false;
    }
}
