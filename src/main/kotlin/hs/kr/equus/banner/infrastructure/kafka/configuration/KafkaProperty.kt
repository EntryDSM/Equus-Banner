package hs.kr.equus.banner.infrastructure.kafka.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kafka")
class KafkaProperty(
    val serverAddress: String
)