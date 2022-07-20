package com.jolin.itrip;

import org.junit.Test;

import java.util.*;

/**
 * @auth jolinmao
 * @date 2022 07 16
 */
public class MapperTest {

    @Test
    public void testMapper() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        List<String> list = Arrays.asList("a", "b", "c");

        for (String key: map.keySet()) {
            System.out.println(key + map.get(key));
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }
    }
}
