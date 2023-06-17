package com.moya_utoma.color_data.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val DEFAULT_RESULT_VALUE = 0
private const val PREFERENCES_NAME = "quiz_results"

private const val QUIZ_MAX_RESULT_KEY = "QUIZ_MAX_RESULT_KEY"

@Singleton
class ColorQuizSharedPrefs @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

    private val MAX_RESULT_KEY = intPreferencesKey(QUIZ_MAX_RESULT_KEY)

    private val dataStore: DataStore<Preferences> get() = context.dataStore

    suspend fun saveMaxResult(result: Int) {
        dataStore.edit { preferences ->
            val maxResult = preferences[MAX_RESULT_KEY] ?: DEFAULT_RESULT_VALUE
            if (result > maxResult) {
                preferences[MAX_RESULT_KEY] = result
            }
        }
    }

    fun getMaxResult(): Flow<Int> {
        val getResult = dataStore.data.map { preferenses ->
            preferenses[MAX_RESULT_KEY] ?: DEFAULT_RESULT_VALUE
        }
        return getResult
    }
}