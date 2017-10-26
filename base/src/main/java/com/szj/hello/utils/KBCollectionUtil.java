package com.szj.hello.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


import com.szj.hello.utils.entity.KeyValuePair;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class KBCollectionUtil {

    /**
     * 从列表中抽出某个属性列表
     *
     * @param items        原始列表，
     * @param propertyName 属性名称
     * @return list of one properties
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> extractPropertyToList(List items, String propertyName)
            throws NoSuchFieldException, IllegalAccessException {
        if (CollectionUtils.isEmpty(items)) {
            return Lists.newArrayList();
        }
        List<T> targetList = Lists.newArrayListWithExpectedSize(items.size());

        for (Object item : items) {
            Field field = null;
            Class clazz = item.getClass();
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(propertyName);
                } catch (Exception e) {
                }
            }
            if (field != null) {
                field.setAccessible(true);
            }
            targetList.add((T) field.get(item));
        }
        return targetList;
    }

    /**
     * 从列表中抽出某个属性，构成属性为key，item为value的映射
     * 注意：map的key和value一定为1对1关系
     *
     * @param items
     * @param propertyName
     * @return
     */
    public static <T, V> Map<T, V> mappingPropertyToKey(List<V> items, String propertyName)
            throws NoSuchFieldException, IllegalAccessException {
        if (CollectionUtils.isEmpty(items)) {
            return Maps.newHashMap();
        }
        Map<T, V> targetMap = Maps.newHashMapWithExpectedSize(items.size());

        for (V item : items) {
            Field field = null;
            Class clazz = item.getClass();
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(propertyName);
                } catch (Exception e) {
                }
            }
            if (field != null) {
                field.setAccessible(true);
            }
            targetMap.put((T) field.get(item), item);
        }

        return targetMap;
    }

    /**
     * 从列表中抽出某个属性，构成属性为key，item列表为value的M映射
     * 注意：map的key和value一定为1对n关系
     *
     * @param items
     * @param propertyName
     * @return
     */
    public static <T, V> Map<T, List<V>> mappingPropertyToValueList(List<V> items, String propertyName)
            throws NoSuchFieldException, IllegalAccessException {
        if (CollectionUtils.isEmpty(items)) {
            return Maps.newHashMap();
        }
        Map<T, List<V>> targetMap = Maps.newHashMapWithExpectedSize(items.size());

        for (V item : items) {
            Field field = null;
            Class clazz = item.getClass();
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(propertyName);
                } catch (Exception e) {
                }
            }
            if (field != null) {
                field.setAccessible(true);
            }
            T key = (T) field.get(item);
            List<V> valueList = targetMap.get(key);
            if (valueList == null) {
                valueList = Lists.newArrayList();
            }
            valueList.add(item);
            targetMap.put((T) field.get(item), valueList);
        }

        return targetMap;
    }

    /**
     * 将map转换成value列表
     *
     * @param map
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<V> mapToValueList(Map<T, V> map) {
        List<V> valueList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(map)) {
            for (T key : map.keySet()) {
                valueList.add(map.get(key));
            }
        }
        return valueList;

    }

    /**
     * 拼接串用, |做分隔符
     *
     * @param list 待拼接的列表
     * @param list
     * @return
     */
    public static String concatString(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String item : list) {
            builder.append(item).append("|");
        }
        return builder.substring(0, builder.length() - 1);
    }


    /**
     * 拼接串用, |做分隔符
     *
     * @param list        待拼接的列表
     * @param concatField 字段
     * @param <T>
     * @return
     */
    public static <T> String concatField(List<T> list, String concatField) {
        return concatField(list, concatField, " | ");
    }

    /**
     * 拼接串用
     *
     * @param list        待拼接的列表
     * @param concatField 字段
     * @param seperator   分隔符
     * @param <T>
     * @return
     */
    public static <T> String concatField(List<T> list, String concatField, String seperator) {
        try {
            StringBuilder builder = new StringBuilder();
            if (CollectionUtils.isEmpty(list)) {
                return "";
            }
            for (Object item : list) {
                Field field = null;
                Class clazz = item.getClass();
                for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                    try {
                        field = clazz.getDeclaredField(concatField);
                    } catch (Exception e) {
                    }
                }
                if (field != null) {
                    field.setAccessible(true);
                }
                builder.append(field.get(item)).append(seperator);
            }
            int size = builder.length();
            return builder.toString().substring(0, size - 3);
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 去除list重复的值
     *
     * @param list
     * @return
     */
    public static List removeDuplicateList(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    public static void main(String[] args) throws Exception {
        KeyValuePair<String, String> kp = new KeyValuePair<>("1213", "123");
        KeyValuePair<String, String> kp2 = new KeyValuePair<>("12123", "123");
        List<KeyValuePair<String, String>> or = Lists.newArrayList(kp, kp2);

        List<String> new_one = extractPropertyToList(or, "key");
        System.out.println(new_one);

        System.out.println(concatField(or, "key", ""));

        Map<String, KeyValuePair<String, String>> new_map = mappingPropertyToKey(or, "value");
        System.out.println(new_map);

        Map map = Maps.newHashMap();
        List list = mapToValueList(map);
        System.out.println(list);

        List duplicateIntList = Lists.newArrayList(1, 1, 2, 3, 4, 4);
        System.out.println(removeDuplicateList(duplicateIntList));
        List duplicateStrList = Lists.newArrayList("abc", "bcd", "bc", "abc");
        System.out.println(removeDuplicateList(duplicateStrList));

        Map<String, List<KeyValuePair<String, String>>> keyValueListMap = KBCollectionUtil.mappingPropertyToValueList(or, "value");
        System.out.println(keyValueListMap);

    }
}
