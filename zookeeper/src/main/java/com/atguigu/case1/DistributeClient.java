package com.atguigu.case1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistributeClient {


    private String con = "vmcentos6:2181,vmcentos62:2181,vmcentos63:2181" ;
    private int timeout = 2000;
    private ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        DistributeClient client = new DistributeClient();
        //huoquzk lianjie
        client.getconnect();


        //jianting servers xiamian jeiidanbianhua

        client.getserverlist();


        //uewuluoji

        client.yewu();



    }

    private void yewu() throws InterruptedException {



        Thread.sleep(Long.MAX_VALUE);
    }

    private void getserverlist() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/servers", true);

        ArrayList<String> objects = new ArrayList<>();
        for (String child : children) {

            byte[] data = zooKeeper.getData("/servers/" + child, false, null);
            objects.add(new String(data));


        }
        System.out.println(objects);


    }

    private void getconnect() throws IOException {
        zooKeeper = new ZooKeeper(con, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

                try {
                    getserverlist();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });


    }
}
