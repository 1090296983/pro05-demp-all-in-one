package com.atguigu.zk;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class zkClient {


    private String connectstring  = "vmcentos6:2181,vmcentos62:2181,vmcentos63:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zooKeeper = null;

    //String connectstring = "vmcentos6:2181,vmcentos62:2181,vmcentos63:2181";
        //int sessionTimeout = 2000;
    @Test
    public void init() throws IOException {
        zooKeeper = new ZooKeeper(connectstring, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

                List<String> children = null;
                try {
                    children = zooKeeper.getChildren("/", true);
                    for (String child : children) {

                        System.out.println(child);
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    @Test
    public void cj() throws KeeperException, InterruptedException {

        String s = zooKeeper.create("/atguigu", "zzy".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);


    }

    @Test
    public void jt() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", true);

        Thread.sleep(Long.MAX_VALUE);


    }



}
