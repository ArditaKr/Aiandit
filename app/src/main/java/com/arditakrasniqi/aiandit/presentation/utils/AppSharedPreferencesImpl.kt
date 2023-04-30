package com.arditakrasniqi.aiandit.presentation.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppSharedPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : AppSharedPreferences {
    private val appPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

    override fun deletePrefs() {
        appPreferences.edit().clear().apply()
    }

    override var isLoggedIn: Boolean
        get() = appPreferences.getBoolean(LOGGED_IN, false)
        set(value) = appPreferences.edit().putBoolean(LOGGED_IN, value).apply()

    override var userName: String?
        get() = appPreferences.getString(USER_NAME, "")
        set(value) = appPreferences.edit().putString(USER_NAME, "$value").apply()

    override var token: String?
        get() = appPreferences.getString(TOKEN, null)
        set(value) = appPreferences.edit().putString(TOKEN, "$BEARER $value").apply()

    override var id: String?
        get() = appPreferences.getString(USER_ID, null)
        set(value) = appPreferences.edit().putString(USER_ID, "$value").apply()

    companion object {
        private const val SHARED_PREFERENCES = "reading_shared_preferences"
        private const val TOKEN = "token"
        private const val BEARER = "Bearer"
        private const val USER_ID = "id"
        private const val USER_NAME = "userName"
        private const val LOGGED_IN = "loggedIn"
    }
}

