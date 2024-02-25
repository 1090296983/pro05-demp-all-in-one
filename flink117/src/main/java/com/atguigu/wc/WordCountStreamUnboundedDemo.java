package com.atguigu.wc;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class WordCountStreamUnboundedDemo {

    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        DataStreamSource<String> vmcentos6 = env.socketTextStream("vmcentos6", 7777);

        SingleOutputStreamOperator<Tuple2<String, Integer>> sum1 = vmcentos6
                .flatMap((String value, Collector<Tuple2<String, Integer>> out) -> {

                            String[] s = value.split("");
                            for (String s1 : s) {
                                out.collect(Tuple2.of(s1, 1));
                                //System.out.println("111111111111111111111");


                            }
                        }

                )
                .returns(Types.TUPLE(Types.STRING,Types.INT))
                .keyBy(value -> value.f0)
                .sum(1);


        sum1.print();
        //System.out.println("222222222222222222222222222");


        env.execute();


        //













    }





}
