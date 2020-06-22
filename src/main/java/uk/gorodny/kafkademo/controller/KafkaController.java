package uk.gorodny.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gorodny.kafkademo.model.User;
import uk.gorodny.kafkademo.service.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Value("${kafka.topicName}")
    private String topicName;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/produce/{firstName}/{lastName}/{age}")
    public ResponseEntity<Void> produce(@PathVariable("firstName") String firstName,
                                        @PathVariable("lastName") String lastName,
                                        @PathVariable("age") int age) {
        User user = User.builder().firstName(firstName).lastName(lastName).age(age).build();
        kafkaProducerService.send(topicName, user);
        return ResponseEntity.ok().build();
    }
}
