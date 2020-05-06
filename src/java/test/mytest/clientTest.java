package mytest;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

/**
 * @author wangcan
 */
public class clientTest {

    @Test
    public void watchTest()throws Exception{
        // 默认的watch
        ZooKeeper client = new ZooKeeper("localhost:2181", 10000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("默认的watch:" + event.getType());
            }
        }, false);

        //        client.create("/luban", "lb".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //        client.create("/luban", "lb".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //        client.create("/luban2", "lb".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        //        client.create("/luban", "lb".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        // watcher的一致性
        client.getData("/luban", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("get绑定的watch");
            }
        }, null);


        //        System.out.println(new String(client.getData("/test", true, null))+"==========");

        // stat怎么用？stat代表的是节点信息，如果想获取出节点信息就可以
        //        Stat stat = new Stat();
        //        client.getData("/luban", false, stat);
        //        System.out.println(stat.getCzxid());

        //        client.setData("/luban", "2".getBytes(), stat.getVersion());
        //        client.setData("/luban", "2".getBytes(), -1);

        //        List<String> children = client.getChildren("/luban", false);
        //        System.out.println(children);

        //        Stat stat = client.exists("/luban1", false);
        //        System.out.println(stat);

        // 异步回调
        //        client.getData("/luban", false, new AsyncCallback.DataCallback() {
        //            @Override
        //            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        //                System.out.println("DataCallback");
        //            }
        //        }, null);
        //
        //        client.getChildren("/luban", false, new AsyncCallback.ChildrenCallback() {
        //            @Override
        //            public void processResult(int rc, String path, Object ctx, List<String> children) {
        //                System.out.println("ChildrenCallback");
        //            }
        //        }, null);


        System.in.read();
    }
}
