# start_kafka_broker

docker run -d -p 9092 --name broker apache/kafka:latest

# open_a_shell_in_the_broker_container

docker exec --workdir /opt/kafka/bin/ -it broker sh

# create_a_topic_called

./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic mikes-topic

# Write_two_string_events

./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic mikes-topic

// This command will wait for input at a > prompt. Enter hello, press Enter, then world, and press Enter again. Enter

Ctrl+C to exit the console producer.

# Now read the events

./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning# apache-kafka-demo
