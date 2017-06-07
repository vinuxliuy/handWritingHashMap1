package hand.writing.hashmap;

/**
 * 自定义一个map接口
 * Created by admin on 2017/6/6.
 */
public interface MyMap<K,V> {
    V put(K key, V value);
    V get(K key);
    int size();

    /**
     * 定义一个内部接口，可以通过Entry拿到这个对象的key合value
     * @param <K>
     * @param <V>
     */
    interface Entry<K,V>{
        K getKey();
        V getValue();
    }
}
