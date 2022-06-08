package com.cherry.guessthething.data

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cherry.guessthething.data.local.CartoonDao
import com.cherry.guessthething.data.local.CartoonDatabase
import com.cherry.guessthething.data.remote.ResponseService
import com.cherry.guessthething.data.remote.ResponseServiceImpl
import com.cherry.guessthething.domain.Repository
import com.cherry.guessthething.domain.parseHtmlToCartoonList
import com.cherry.guessthething.model.Cartoon

class RepositoryImpl(
    private val application: Application,
    private val responseService: ResponseService = ResponseService.create() as ResponseServiceImpl,
    private val cartoonDao: CartoonDao = CartoonDatabase.getInstance(application).cartoonDao()
) : Repository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "quiz_result")

    private val MAX_RESULT_KEY = intPreferencesKey("max_result")

    private var cartoonsList: List<Cartoon> = emptyList()

    override suspend fun loadCartoonsList() {
        cartoonsList = parseHtmlToCartoonList(trimmedHtmlCode = responseService.getPosts())
        insertToDb()
    }

    private suspend fun insertToDb() {
        if (cartoonDao.getCartoons().containsAll(cartoonsList)) {
            return
        } else {
            cartoonDao.insertAllCartoons(cartoonsList)
        }
    }

    override suspend fun getCartoons(): List<Cartoon> = cartoonDao.getCartoons()


    override suspend fun saveMaxResult(result: Int) {
        application.dataStore.edit { preferences ->
            val maxResult = preferences[MAX_RESULT_KEY] ?: 0
            if (result > maxResult) {
                preferences[MAX_RESULT_KEY] = result
            }
        }
    }

    override suspend fun getMaxResult(): Int {
        var max = 0
        application.dataStore.data.collect { preferenses ->
            max = preferenses[MAX_RESULT_KEY] ?: 0
        }
        return max
    }

}