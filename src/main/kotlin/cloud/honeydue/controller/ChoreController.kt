package cloud.honeydue.controller

import cloud.honeydue.model.Chore
import cloud.honeydue.repository.ChoreRepository
import cloud.honeydue.repository.UserRepository
import io.jsonwebtoken.Jwts
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1", produces = ["application/json"])
class ChoreController(@Autowired val userRepository: UserRepository, @Autowired val choreRepository: ChoreRepository) {
    var logger = LoggerFactory.getLogger(ChoreController::class.java)
    var signingKey = "NHi+orxnVHNclk17zy5oFc0znVXkUh3BOQwMEbP8308=".encodeToByteArray()

    private fun parseJwtToken(token: String) : String {
        var claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token)
        return claims.body.subject
    }

    @GetMapping("/chores")
    fun findChoresForNickname(@RequestHeader("Authorization") jwtToken:String): MutableSet<Chore>? {
        var user = userRepository.getByNickname(parseJwtToken(jwtToken))
        logger.info(user.nickname)
        return choreRepository.findByUserId(user.id)
    }

    @PostMapping("/chores")
    fun addChoreByNickname(@RequestHeader("Authorization") jwtToken:String, @RequestBody chore: Chore): MutableSet<Chore>? {
        var user = userRepository.getByNickname(parseJwtToken(jwtToken))
        logger.info(user.nickname)
        chore.user = user
        choreRepository.save(chore)
        return choreRepository.findByUserId(user.id)
    }

}