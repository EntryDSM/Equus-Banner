package hs.kr.equus.banner.infrastructure.kafka.consumer

import hs.kr.equus.banner.domain.banner.domain.repository.BannerLinkRepository
import hs.kr.equus.banner.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteBannerTableConsumer(
    private val bannerLinkRepository: BannerLinkRepository
) {
    @KafkaListener(
        topics = [KafkaTopics.DELETE_ALL_TABLE],
        groupId = "delete-all-table-banner",
        containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    fun execute() = bannerLinkRepository.deleteAll()
}
