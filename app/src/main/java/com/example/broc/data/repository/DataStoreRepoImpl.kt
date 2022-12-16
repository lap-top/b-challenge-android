package com.example.broc.data.repository


import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.broc.common.Constants
import com.example.broc.common.Resource
import com.example.broc.domain.model.EmailInvite
import com.example.broc.domain.repository.DataStoreRepo
import kotlinx.coroutines.flow.first
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.serializer
import kotlinx.serialization.serializer
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

    override suspend fun toggleInvite(key: String, value: String) {
        var contents = getSerializedContents(key)?: mutableListOf()
        val preferenceKey = stringPreferencesKey(key)

        var inviteToAdd = EmailInvite(email = value, active = true)
        val oldInviteIdx = contents.indexOfFirst { it.email == inviteToAdd.email }

        if (oldInviteIdx != -1) {
            contents[oldInviteIdx].apply { active = !active }
        } else {
            contents.add(inviteToAdd)
        }

        context.dataStore.edit { store ->
            store[preferenceKey] = inviteListToJson(contents)
        }
    }

    override suspend fun toggleInvites(emails: List<EmailInvite>) {
        var contents = getSerializedContents(Constants.EMAIL_HAS_SENT)
        val preferenceKey = stringPreferencesKey(Constants.EMAIL_HAS_SENT)
        contents?.forEach { inv ->
            inv.active = !inv.active
        }
        context.dataStore.edit { store ->
            store[preferenceKey] = Json.encodeToString(contents)
        }
    }

    override suspend fun getActiveInvites(): Resource<List<EmailInvite>> {
        var contents = getSerializedContents(Constants.EMAIL_HAS_SENT)
        return if (contents != null) {
            Resource.Success(contents.filter { it.active })
        } else {
            Resource.Error(message = "No Active emails")
        }
    }

    private suspend fun getSerializedContents(key: String): MutableList<EmailInvite>? {
        val preferenceKey = stringPreferencesKey(key)
        val preference = context.dataStore.data.first()
        val contents = preference[preferenceKey]
        return try {
            Json.decodeFromString(contents.toString())
        } catch (e: java.lang.Exception) {
            null
        }

    }

    private companion object {
        val inviteSerializer = ListSerializer(serializer<EmailInvite>())
        fun inviteListToJson(invites: MutableList<EmailInvite>): String {
                return Json.encodeToString(inviteSerializer, invites)
        }
    }
}

