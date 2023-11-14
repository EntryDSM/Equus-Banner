package hs.kr.equus.banner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class EquusBannerApplication

fun main(args: Array<String>) {
	runApplication<hs.kr.equus.banner.EquusBannerApplication>(*args)
}
