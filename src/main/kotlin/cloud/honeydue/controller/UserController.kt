package cloud.honeydue.controller

import cloud.honeydue.model.User
import cloud.honeydue.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1", produces = ["application/json"])
class UserController(@Autowired val userRepository: UserRepository) {

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