package com.arditakrasniqi.aiandit.presentation.utils

interface AppSharedPreferences {
    fun deletePrefs()

    var token: String?

    var id: String?

    var userName: String?

    var isLoggedIn: Boolean
}