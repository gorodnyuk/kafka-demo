package uk.gorodny.kafkademo;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gorodny.kafkademo.model.User;
import uk.gorodny.kafkademo.service.KafkaConsumerService;
import uk.gorodny.kafkademo.service.KafkaProducerService;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
public class KafkaTest {

    @Value("${kafka.topic.simpleMessageTopic}")
    private String topicName;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @ClassRule
    public static EmbeddedKafkaRule kafkaEmbedded = new EmbeddedKafkaRule(1, false, "testTopic");

    @Test
    public void testSendReceive() throws Exception {
        User user = User.builder().firstName("John").lastName("Dou").age(30).build();
        kafkaProducerService.send(topicName, user);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(user, user);
    }
}
