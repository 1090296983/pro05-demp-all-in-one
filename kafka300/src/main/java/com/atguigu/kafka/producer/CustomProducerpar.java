package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomProducerpar {



    public static void main(String[] args) {


        Properties pro = new Properties();

        //lieanjie


        pro.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"vmcentos6:9092,vmcentos62:9092,vmcentos63:9092");


        //xuliehua
        pro.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        pro.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        pro.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.atguigu.kafka.producer.mypar");

        KafkaProducer<String, String> kafkapu = new KafkaProducer<>(pro);

        for (int i = 0; i < 5; i++) {

          //  kafkapu.send(new ProducerRecord<>("first","zzy1" + String.valueOf(i)));
//

            kafkapu.send(new ProducerRecord<String,String>("first","zy33"), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {

                    System.out.println(metadata.topic());
                    System.out.println(metadata.partition());

                }
            });

        }

        kafkapu.close();









    }





}
