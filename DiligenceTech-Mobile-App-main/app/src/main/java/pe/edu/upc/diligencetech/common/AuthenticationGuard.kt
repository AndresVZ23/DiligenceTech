package pe.edu.upc.diligencetech.common

import android.content.Context
import android.content.SharedPreferences
import java.util.prefs.Preferences

class AuthenticationGuard(var token: String? = null) {
    private fun isAuthenticated(): Boolean {
        return token != null
    }

    fun signIn(id: Long, username: String, token: String) {
        this.token = token
        Constants.id = id
        Constants.username = username
        Constants.token = token
    }

    fun guard(onNotAuthenticatedTask: () -> Unit) {
        if (!isAuthenticated()) {
            onNotAuthenticatedTask()
        }
    }
}