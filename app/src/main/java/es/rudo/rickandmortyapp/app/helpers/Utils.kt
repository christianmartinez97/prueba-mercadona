package es.rudo.rickandmortyapp.app.helpers

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import es.rudo.rickandmortyapp.app.R
import retrofit2.HttpException
import es.rudo.rickandmortyapp.app.data.models.Error
import es.rudo.rickandmortyapp.app.helpers.Constants.CHARACTER_UNKNOWN
import java.io.IOException
import java.net.HttpURLConnection
import java.net.UnknownHostException

object Utils {

    fun getError(exception: Exception): Error {
        return when (exception) {
            is IOException -> Error.NetworkException(exception.message)
            is UnknownHostException -> Error.NetworkException(exception.message)
            is HttpException -> {
                when (exception.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> Error.NotFound(exception.message)
                    HttpURLConnection.HTTP_BAD_REQUEST -> Error.BadRequest(exception.message)
                    HttpURLConnection.HTTP_CLIENT_TIMEOUT -> Error.ClientTimeout(exception.message)
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> Error.InternalError(exception.message)
                    HttpURLConnection.HTTP_FORBIDDEN -> Error.AccessDenied(exception.message)
                    HttpURLConnection.HTTP_UNAVAILABLE -> Error.ServiceUnavailable(exception.message)
                    else -> Error.Unknown(exception.message)
                }
            }
            else -> Error.Unknown(exception.message)
        }
    }

    fun setCharacterStatus(
        status: String?,
        imageCharacter: ImageView,
        textView: TextView
    ) {
        status?.let {
            when (status) {
                Constants.CHARACTER_ALIVE -> {
                    textView.setTextColor(ContextCompat.getColor(textView.context, R.color.green))
                    textView.text = it
                    imageCharacter.alpha = 1.0f
                }
                Constants.CHARACTER_DEAD -> {
                    textView.setTextColor(ContextCompat.getColor(textView.context, R.color.red))
                    textView.text = it
                    imageCharacter.alpha = 0.5f
                }
                else -> {
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.gray
                        )
                    )
                    textView.text = it
                    imageCharacter.alpha = 1.0f
                }
            }
        } ?: kotlin.run {
            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.gray))
            textView.text = CHARACTER_UNKNOWN
            imageCharacter.alpha = 1.0f
        }
    }
}
