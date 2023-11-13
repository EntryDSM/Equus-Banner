package hs.kr.equus.banner.infrastructure.fegin.configuration

import feign.codec.ErrorDecoder
import hs.kr.equus.banner.infrastructure.fegin.FeignClientErrorDecoder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["hs.kr.equus.banner"])
@Configuration
class FeignConfig {
    @Bean
    @ConditionalOnMissingBean(value = [ErrorDecoder::class])
    fun commonFeignErrorDecoder(): FeignClientErrorDecoder = FeignClientErrorDecoder()
}
