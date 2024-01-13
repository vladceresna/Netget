package com.vladceresna.netget.support;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.vladceresna.netget.pojos.SitesRecordInBase;
import org.json.JSONObject;

import java.util.ArrayList;

public class SL {//SupportLib
    public static Object JtO(Object json, Class c) {//JSON to Object
        String str = String.valueOf(json);
        return new Gson().fromJson(str,c);
    }
    public static Object JtORiB(Object json) {//JSON to Object
        LinkedTreeMap jsonMap = (LinkedTreeMap) json;
        SitesRecordInBase sitesRecordInBase = new SitesRecordInBase();
        sitesRecordInBase.setTitle(String.valueOf(jsonMap.get("title")));
        sitesRecordInBase.setUrl(String.valueOf(jsonMap.get("url")));
        return sitesRecordInBase;
    }
    public static ArrayList<?> JtAO(Object json, TypeToken typeToken){//JSON to Array Object
        return (ArrayList<?>) new Gson().fromJson((String) json, typeToken);
    }
    public static Object OtJ(Object obj) {//Object to JSON
        return new Gson().toJson(obj);
    }
    public static Object OtPJ(Object obj) {//Object to Pretty JSON
        return new GsonBuilder().setPrettyPrinting().create().toJson(obj);
    }
    public static Object JtPJ(Object obj) {//JSON to Pretty JSON
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse((String) obj));
    }
}
