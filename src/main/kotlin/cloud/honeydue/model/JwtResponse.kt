package cloud.honeydue.model

import com.fasterxml.jackson.annotation.JsonInclude
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws


@JsonInclude(JsonInclude.Include.NON_NULL)
class JwtResponse {
    var message: String? = null
    var status: Status? = null
    var exceptionType: String? = null
    var jwt: String? = null
    var jws: Jws<Claims>? = null

    enum class Status {
        SUCCESS, ERROR
    }

    constructor() {}
    constructor(jwt: String?, status: Status) {
        this.jwt = jwt
        this.status = status
    }

    constructor(jws: Jws<Claims>?, status: Status) {
        this.jws = jws
        this.status = status
    }
}