package hand.writing.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手写一个HashMap
 * Created by admin on 2017/6/6.
 */
public class MyHashMap<K,V> implements MyMap<K,V> {
    //定义数组长度（2的倍数）
    private static Integer defaultLength  = 2<<3;
    //定义一个负载因子，超过这个因子就会扩容
    private static double defaultLoad = 0.75;
    //定义一个数组，用来存放Entry对象
    private Entry<K,V>[] entryArr;
    //定义一个常量用来存放数组大小
    private int size= 0;

    public MyHashMap() {
        this(defaultLength,defaultLoad);
    }

    /*
     * 定义一个构造函数
     */
    public MyHashMap(int defaultLength, double defaultLoad) {
        this.defaultLength = defaultLength;
        this.defaultLoad = defaultLoad;
        //定义一个默认数组，长度就是传过来的长度
        this.entryArr = new Entry[defaultLength];
    }

    public V put(K key, V value) {
        //得到要放的数据的位置，也就是数组的下标
        int index = this.getIndex(key);
        //根据下标判断是否有数据
        Entry<K,V> entry = entryArr[index];
        if(null == entry){
            entryArr[index] = new Entry<K,V>(key,value,null,index);
            //数组长度+1
            size++;
        }else{
            Entry newEntry = new Entry<K,V>(key,value,entry,index);
            entryArr[index] = newEntry;
        }
        return entryArr[index].getValue();
    }

    /*
     * 找数组下标的方法
     */
    private int getIndex(K key) {
        int m = this.defaultLength-3;
        return key.hashCode() % m;
    }

    public V get(K key) {
        int index = getIndex(key);// 得到数组下标
        return entryArr[index]==null?null:entryArr[index].getValue();
    }

    public int size() {
        return size;
    }

    /**
     * 定义数组，用来存储
     * @param <K>
     * @param <V>
     */
    class Entry<K,V> implements MyMap.Entry<K,V>{
        K key;
        V value;
        Entry<K,V> next;
        int index; //下标

        public Entry(K key, V value, Entry<K, V> next, int index) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.index = index;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }


    public static void  main(String[] args){
        MyMap map = new MyHashMap();
        map.put("name","Tom");
        System.out.println("PUT==="+map.put("name","Tom"));
        System.out.println("GET==="+map.get("name"));
    }
}
