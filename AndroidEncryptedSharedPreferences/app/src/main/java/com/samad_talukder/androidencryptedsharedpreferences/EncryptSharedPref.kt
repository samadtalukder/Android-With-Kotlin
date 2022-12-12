package com.samad_talukder.androidencryptedsharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson


class EncryptSharedPref(context: Context) {
    companion object {
        const val ENCRYPTED_SHARED_PREFS_FILE = "encrypt_shared_prefs"
        const val PREFS_TOKEN = "token"
    }

    private var sharedPreferences: SharedPreferences? = null
    private var sharedPreferencesEdit: SharedPreferences.Editor? = null

    private var masterKey: MasterKey = MasterKey.Builder(
        context,
        MasterKey.DEFAULT_MASTER_KEY_ALIAS
    )
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private var encryptedSharedPreferences = EncryptedSharedPreferences.create(
        context,
        ENCRYPTED_SHARED_PREFS_FILE,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private var sharedPref =
        context.getSharedPreferences(ENCRYPTED_SHARED_PREFS_FILE, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        encryptedSharedPreferences.edit().putString(PREFS_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return encryptedSharedPreferences.getString(PREFS_TOKEN, "")
    }

    fun saveString(key: String, value: String) {
        sharedPreferences = if (BuildConfig.DEBUG) {
            sharedPref
        } else {
            encryptedSharedPreferences
        }

        sharedPreferences!!.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        sharedPreferences = if (BuildConfig.DEBUG) {
            sharedPref
        } else {
            encryptedSharedPreferences
        }
        return sharedPreferences!!.getString(key, "").toString()
    }

    fun saveObject(serializeObjectKey: String, `object`: Any) {
        sharedPreferences = if (BuildConfig.DEBUG) {
            sharedPref
        } else {
            encryptedSharedPreferences
        }

        val gson = Gson()
        val serializeObjectValue = gson.toJson(`object`)

        sharedPreferences!!.edit().putString(serializeObjectKey, serializeObjectValue).apply()
    }

    fun <T> getSaveObject(
        serializeObjectKey: String,
        classType: Class<T>
    ): T? {
        sharedPreferences = if (BuildConfig.DEBUG) {
            sharedPref
        } else {
            encryptedSharedPreferences
        }

        if (sharedPreferences!!.contains(serializeObjectKey)) {
            val gson = Gson()
            return gson.fromJson(sharedPreferences!!.getString(serializeObjectKey, ""), classType)
        }

        return null
    }

    fun clearPreference() {
        sharedPreferences!!.edit()?.clear()?.apply()
    }


}