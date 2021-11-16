package xyz.esion.blog.global;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Esion
 * @since 2021/11/16
 */
@Data
public class KeyValue<K, V> implements Serializable {

    private static final long serialVersionUID = 1L;

    private K key;

    private V value;

}
