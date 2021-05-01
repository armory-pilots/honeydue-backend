package cloud.honeydue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class HoneyDueApplication

fun main(args: Array<String>) {
	runApplication<HoneyDueApplication>(*args)
}
