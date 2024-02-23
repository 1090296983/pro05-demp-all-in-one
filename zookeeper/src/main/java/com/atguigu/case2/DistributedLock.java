package com.atguigu.case2;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public  class DistributedLock {


    private final String con = "vmcentos6:2181,vmcentos62:2181,vmcentos63:2181";
    private final int timeout = 2000;
    private  ZooKeeper zooKeeper = null;
    private CountDownLatch conlatch = new CountDownLatch(1);
    private CountDownLatch lockdel = new CountDownLatch(1);
    private String oldchildren ;
    private String s;

    public DistributedLock() throws IOException, KeeperException, InterruptedException {
        zooKeeper = new ZooKeeper(con, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

//                if (event.getState() == Event.KeeperState.SyncConnected) {
//                    connectLatch.countDown();
//                }
//                // 发生了 waitPath 的删除事件
//                if (event.getType() ==Event.EventType.NodeDeleted && event.getPath().equals(waitPath))
//                {
//                    waitLatch.countDown();
//                }

                if(watchedEvent.getState() == Event.KeeperState.SyncConnected){

                    conlatch.countDown();

                }

                if(watchedEvent.getType() == Event.EventType.NodeDeleted && watchedEvent.getPath().equals(oldchildren)){

                    lockdel.countDown();


                }






            }
        });

        conlatch.await();
        Stat exists = zooKeeper.exists("/locks", false);
        if(exists == null){
            zooKeeper.create("/locks","locks cj".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);


        }


    }

    public void zklocks() throws InterruptedException, KeeperException {
        s = zooKeeper.create("/locks/" + "pr-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);

        Thread.sleep(1000);
        List<String> children = zooKeeper.getChildren("/locks", false);

        String substring = s.substring("/locks/".length());


        Collections.sort(children);

        int i = children.indexOf(substring);
        System.out.println(i);

        if ( i == 0 ){
//            oldchildren = "/locks/" + children.get(i);
//            System.out.println(oldchildren);
            return;
        }else {
            oldchildren = "/locks/" + children.get(i - 1);
            System.out.println(oldchildren);

            zooKeeper.getData(oldchildren,true,new Stat());

            lockdel.await();

            return;






        }


    }

    public void zklocksdel() throws KeeperException, InterruptedException {

        zooKeeper.delete(s,-1);




    }






}
