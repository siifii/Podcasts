package com.siifii.thamanyah.data.datasource.resources.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.siifii.thamanyah.BuildConfig

class SharedPreferencesDataSource(val context: Context, val gson: Gson) : PreferencesDataSource {
    private val pref: SharedPreferences =
        context.getSharedPreferences(BuildConfig.SHARED_PREF, Context.MODE_PRIVATE)
    private val PREF_LANG_KEY = "lang"

    companion object {
        val FCM_TOKEN = "FCMtoken"
        val REGISTRATION_ID = "registrationID"
    }

    override fun clearPrefs() {
        pref.edit().clear().apply()
    }

    override fun logoutLocally() {
        val editor = pref.edit()
        pref.all.keys.filter {
            it != PREF_LANG_KEY && it != FCM_TOKEN
        }.forEach {
            editor.remove(it)
        }

        editor.apply()
    }


    override fun saveStringToPreferences(key: String, value: String?) {
        pref.edit().putString(key, value).apply()
    }


    override fun saveStringToArrayStringsPreferences(key: String, value: String?) {
        val list = pref.getStringSet(key, emptySet())?.toMutableSet()?.apply { add(value) }
        pref.edit().putStringSet(key, list).apply()
    }


    override fun saveIntToPreferences(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    override fun saveFloatToPreferences(key: String, value: Float) {
        pref.edit().putFloat(key, value).apply()
    }


    override fun clearPreferenceValue(key: String) {
        pref.edit().remove(key).apply()
    }

    @SuppressLint("ApplySharedPref")
    override fun saveBooleanToPreferences(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    override fun deleteStringFromPreferences(value: String) {
        with(pref) {
            edit()
                .remove(value)
                .apply()
        }
    }

    override fun getStringFromPreferences(key: String): String? {
        return pref.getString(key, null)
    }

    override fun getStringSetFromPreferences(key: String): Set<String> {
        return pref.getStringSet(key, emptySet()) ?: emptySet()
    }

    override fun getStringFromPreferences(key: String, default: String): String {
        return pref.getString(key, default)?.let { it } ?: default
    }


    override fun deleteStringFromSetPreferences(key: String, value: String) {
        val list = pref.getStringSet(key, emptySet())?.toMutableSet()?.apply { remove(value) }
        pref.edit().putStringSet(key, list).apply()
    }

    override fun getIntFromPreferences(key: String): Int? {
        return pref.getInt(key, -1)
    }

    override fun getFloatFromPreferences(key: String, default: Float): Float {
        return pref.getFloat(key, -1.0f)
    }

    override fun getIntFromPreferences(key: String, default: Int): Int {
        return pref.getInt(key, default)
    }

    override fun getBooleanFromPreferences(key: String): Boolean {
        return pref.getBoolean(key, false)
    }

    override fun <T> saveObject(key: String?, `object`: T) {
        val objectString = gson.toJson(`object`)
        val editor = pref.edit()
        editor.putString(key, objectString)
        editor.apply()
    }

    override fun <T> getObject(key: String, classType: Class<T>?): T? {
        if (isKeyExists(key)) {
            val objectString = pref.getString(key, null)
            if (objectString != null) {
                return gson.fromJson(objectString, classType)
            }
        }
        return null
    }

    override fun <T> saveObjectsList(key: String?, objectList: List<T>?) {
        val objectString = gson.toJson(objectList)
        val editor = pref.edit()
        editor.putString(key, objectString)
        editor.apply()
    }

    override fun <T> getObjectsList(key: String, classType: Class<T>?): List<T>? {
        if (isKeyExists(key)) {
            val objectString = pref.getString(key, null)
            if (objectString != null) {
                val t: ArrayList<T> =
                    gson.fromJson(objectString, object : TypeToken<List<T>?>() {}.type)
                val finalList: MutableList<T> = ArrayList()
                for (i in t.indices) {
                    val s = t[i].toString()
                    finalList.add(gson.fromJson(s, classType))
                }
                return finalList
            }
        }
        return null
    }

    override fun isKeyExists(key: String): Boolean {
        val res = pref.all.keys.toList().filter {
            it == key
        }

        return res.isNotEmpty()
    }
}