package es.rudo.rickandmortyapp.app.data.models

object Empty : Error()

sealed class Error(val description: String? = null) : Exception() {
    class NetworkException(description: String?) : Error(description)
    class NotFound(description: String?) : Error(description)
    class BadRequest(description: String?) : Error(description)
    class ClientTimeout(description: String?) : Error(description)
    class InternalError(description: String?) : Error(description)
    class AccessDenied(description: String?) : Error(description)
    class ServiceUnavailable(description: String?) : Error(description)
    class EmptyBody(description: String?) : Error(description)
    class Unknown(description: String?) : Error(description)
}
