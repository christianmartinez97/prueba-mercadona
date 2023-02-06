package es.rudo.rickandmortyapp.app.data.source.preferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(
    val sharedPreferences: SharedPreferences?
) {

    private val ACCESS_TOKEN = "view_onboarding"

    fun getAccessToken(): String? {
        return sharedPreferences?.getString(ACCESS_TOKEN, null)
    }

    fun setAccessToken(accessToken: String) {
        sharedPreferences?.edit()?.putString(ACCESS_TOKEN, accessToken)?.apply()
    }

    fun clear() {
        sharedPreferences?.edit()?.clear()?.apply()
    }
}
