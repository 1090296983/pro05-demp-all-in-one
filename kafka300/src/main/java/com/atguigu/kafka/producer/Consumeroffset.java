package com.atguigu.kafka.producer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

public class Consumeroffset {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"vmcentos6:9092,vmcentos62:9092,vmcentos63:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());


        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"1006");



        KafkaConsumer<String, String> KafkaConsumer = new KafkaConsumer<>(properties);


        ArrayList<String> strings = new ArrayList<>();

        strings.add("first2");




        KafkaConsumer.subscribe(strings);


        Set<TopicPartition> assignment = KafkaConsumer.assignment();

        if(assignment.size() == 0){

            KafkaConsumer.poll(Duration.ofSeconds(1));

            assignment = KafkaConsumer.assignment();



        }


        HashMap<TopicPartition, Long> topicPartitionLongHashMap = new HashMap<>();


        for (TopicPartition topicPartition : assignment) {


            topicPartitionLongHashMap.put(topicPartition,System.currentTimeMillis()-1*24*3600*1000);
        }


        Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = KafkaConsumer.offsetsForTimes(topicPartitionLongHashMap);


        for (TopicPartition topicPartition : assignment) {

            topicPartitionOffsetAndTimestampMap.get(topicPartition);

            KafkaConsumer.seek(topicPartition,1);

        }











    while(true) {

        ConsumerRecords<String, String> poll = KafkaConsumer.poll(Duration.ofSeconds(1));
        System.out.println("222222222222222222222222222");

        for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {

            System.out.println(stringStringConsumerRecord.value());
            System.out.println("111111111111111111111111111111111");
            //System.out.println(stringStringConsumerRecord);


        }
    }



    }

}
