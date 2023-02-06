package es.rudo.rickandmortyapp.app.data.source.remote.ws.interceptor

import es.rudo.rickandmortyapp.app.data.source.preferences.AppPreferences
import es.rudo.rickandmortyapp.app.data.source.remote.ws.api.Config.HTTP_CLIENT_AUTHORIZATION
import es.rudo.rickandmortyapp.app.data.source.remote.ws.api.Config.TYPE_ITEM_AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val preferences: AppPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = preferences.getAccessToken()
        val request = newRequestWithAccessToken(chain.request(), accessToken.toString())
        return chain.proceed(request)
    }

    private fun newRequestWithAccessToken(request: Request, accessToken: String): Request {
        return request.newBuilder()
            .header(TYPE_ITEM_AUTHORIZATION, "$HTTP_CLIENT_AUTHORIZATION $accessToken")
            .build()
    }
}
