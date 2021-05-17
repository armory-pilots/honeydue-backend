package cloud.honeydue.model

import java.lang.Exception

class AuthenticationException(errorMessage: String?) : Exception(errorMessage)