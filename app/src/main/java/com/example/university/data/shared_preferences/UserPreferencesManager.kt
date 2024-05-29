package com.example.university.data.shared_preferences

import android.content.Context
import androidx.datastore.core.DataStore
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Inject
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        val USER_ID = intPreferencesKey("user_id")
    }

    suspend fun saveUserId(userId: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    val userIdFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ID]!!
        }
}
