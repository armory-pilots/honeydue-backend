package cloud.honeydue.controller

import cloud.honeydue.model.AuthenticationException
import cloud.honeydue.model.JwtResponse
import cloud.honeydue.model.Login
import cloud.honeydue.model.User
import cloud.honeydue.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@RestController
@RequestMapping("/api/v1", produces = ["application/json"])
class UserController(@Autowired val userRepository: UserRepository) {
    var logger = LoggerFactory.getLogger(UserController::class.java)
    var secret = Base64.getDecoder().decode("NHi+orxnVHNclk17zy5oFc0znVXkUh3BOQwMEbP8308=")


    @PostMapping("/authenticate")
    fun authenticate(@RequestBody login: Login): JwtResponse {
        var user = userRepository.getByNickname(login.nickname)
        return if (user.password == login.password) {
            var jws = Jwts.builder().setIssuer("honeydue").setSubject(user.nickname).claim("name", user.firstName + " " + user.lastName).claim("scope", "users").setIssuedAt(
                Date.from(
                    Instant.now())).setExpiration(Date.from(Instant.now().plus(8, ChronoUnit.HOURS))).signWith(
                Keys.hmacShaKeyFor(secret)).compact()
            JwtResponse(jws, JwtResponse.Status.SUCCESS)
        } else {
            var exception : AuthenticationException = AuthenticationException("Username/Password combination incorrect")
            var jws = Jwts.builder().setIssuer("honeydue").setSubject(exception.message).compact()
            JwtResponse(jws, JwtResponse.Status.ERROR)
        }
    }

    @GetMapping("/users")
    fun allUsers(): MutableList<User> {
        return userRepository.findAll()
    }

    @GetMapping("/users/{nickname}")
    fun byNickname(@PathVariable nickname: String): User? {
        return userRepository.getByNickname(nickname)
    }

    @PostMapping("/users")
    fun newUser(@RequestBody user: User): User? {
        return userRepository.save(user)
    }

    @PutMapping("/users/{nickname}")
    fun updateUser(@RequestBody updatedUser: User, @PathVariable nickname: String): User? {
        var user = userRepository.getByNickname(nickname)
        user.emailAddress = updatedUser.emailAddress
        user.firstName = updatedUser.firstName
        user.lastName = updatedUser.lastName
        return userRepository.save(user)
    }

    @DeleteMapping("/users/{nickname}")
    fun deleteUser(@PathVariable nickname: String) {
        val user = userRepository.getByNickname(nickname)
        user.id?.let { userRepository.deleteById(it) }
    }

}