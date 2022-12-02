package com.example.broc.data.repository


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.broc.common.Constants
import com.example.broc.common.Resource
import com.example.broc.domain.repository.DataStoreRepo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)

// Used by use cases to put/get data from DataStore preferences
class DataStoreRepoImpl @Inject constructor(
    private val context: Context,
) : DataStoreRepo {
    override suspend fun putString(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getString(key: String): Resource<String> {
        try {
            val preferenceKey = stringPreferencesKey(key)
            val preference = context.dataStore.data.first()
            return Resource.Success(preference[preferenceKey] ?: "")
        } catch (e: Exception) {
        }
        return Resource.Error(message = "No Contents yet found in data store")
    }
}