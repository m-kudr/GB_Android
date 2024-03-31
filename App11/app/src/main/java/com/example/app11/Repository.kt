package com.example.app11

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.FileInputStream
import java.io.IOException

private const val PREFERENCE_NAME = "preference_name"
private const val SHARED_PREFS_KEY = "shared_prefs_key"
private const val FILE_NAME = "testfile.txt"

class Repository {
    fun loadText(context: Context): String {
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference(context) != null -> getDataFromSharedPreference(context)!!
            loadFromFile(context) != null -> loadFromFile(context)!!
            else -> "No one source doesn't contain string"
        }
    }

    fun saveText(context: Context): Boolean{
        return false
    }

    private fun getDataFromSharedPreference(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        return prefs.getString(SHARED_PREFS_KEY, null)
    }

    private fun loadFromFile(context: Context): String? {
        var fin: FileInputStream? = null
        return try {
            fin = context.openFileInput(FILE_NAME)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            String(bytes)
        } catch (ex: IOException) {
            null
        } finally {
            fin?.close()
        }
    }

    private fun getDataFromLocalVariable(): String? {
        val localValue: String? = "from local val"
        return localValue
    }

    private fun saveText(test: String): {

    }

    fun clearText() {

    }

    fun getTex(): String? {

    }
}