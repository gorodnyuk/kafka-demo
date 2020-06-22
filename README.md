Демонстрация работы Kafka producer и consumer
*********************************************
1. Скачать Kafka - https://kafka.apache.org/downloads
Следующие шаги подразумевают, что путь до Kafka не был указан в переменной Path
Все команды запускаются из разархивированной папки (Windows)
Настройки стандартные
2. Запустить ZooKeeper - bin/windows/zookeeper-server-start config/zookeeper.properties
3. Запустить Kafka - bin/windows/kafka-server-start config/server.properties
*********************************************
Вспомогательные команды:

Посмотреть список запущенных топиков:
bin/windows/kafka-topics --bootstrap-server localhost:9092 --list

Создать топик:
bin/windows/kafka-topics --create --zookeeper localhost:2181 -partitions 1 --replication-factor 1 --topic {topicName}

Запустить слушателя (consumer):
bin/windows/kafka-console-consumer --bootstrap-server localhost:9092 --topic {topicName} --from-beginning