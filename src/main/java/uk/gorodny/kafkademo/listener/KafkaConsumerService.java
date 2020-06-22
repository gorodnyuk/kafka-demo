package uk.gorodny.kafkademo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gorodny.kafkademo.model.User;

@Service
@Slf4j
public class KafkaConsumerService {

    /// Для получения сообщений добавить @KafkaListener

    //     @KafkaListener(topics = "mytopic", groupId = "group_id", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(User user) {
        log.info(user.toString());
    }
}
