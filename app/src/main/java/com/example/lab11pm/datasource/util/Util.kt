package com.example.lab11pm.datasource.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

const val preferences_name = "settings"
const val key_email = "email"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = preferences_name)

suspend fun DataStore<Preferences>.savePreferencesValue(key: String, value: String) {
    val dataStoreKey = stringPreferencesKey(key)
    edit { settings ->
        settings[dataStoreKey] = value
    }
}

suspend fun DataStore<Preferences>.getPreferencesValue(key: String): String? {
    val dataStoreKey = stringPreferencesKey(key)
    val preferences = data.first()
    return preferences[dataStoreKey]
}

suspend fun DataStore<Preferences>.removePreferencesValue(key: String) {
    val dataStoreKey = stringPreferencesKey(key)
    edit { settings ->
        settings.remove(dataStoreKey)
    }
}