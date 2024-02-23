package com.atguigu.case1;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DistributeServer {
    private String con = "vmcentos6:2181,vmcentos62:2181,vmcentos63:2181";
    private int timeout = 2000;
    private ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //con zk
        DistributeServer servrer = new DistributeServer();
        servrer.getConnect();

        //zhuce fuwu qi dao zk jiqun
        servrer.regist(args[0]);

        //qidongyewulongji


        servrer.yewu();










    }

    private void yewu() throws InterruptedException {

        Thread.sleep(Long.MAX_VALUE);



    }

    private void regist(String host) throws KeeperException, InterruptedException {

        String s = zooKeeper.create("/servers/" + host, host.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(s + "is online");


    }

    private void getConnect() throws IOException {

        zooKeeper = new ZooKeeper(con, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

    }


}
