package com.adun;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class LinkedMultiValueMapTests {

    private LinkedMultiValueMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new LinkedMultiValueMap<String, String>();
    }

    @Test
    public void add() {
        map.add("key", "value1");
        map.add("key", "value2");
        assertEquals(1, map.size());
        List<String> expected = new ArrayList<String>(2);
        expected.add("value1");
        expected.add("value2");
        System.out.println(map);
        assertEquals(expected, map.get("key"));
    }

    @Test
    public void getFirst() {
        List<String> values = new ArrayList<String>(2);
        values.add("value1");
        values.add("value2");
        map.put("key", values);
        System.out.println(map.getFirst("key"));
        System.out.println(map.getFirst("other"));
        assertEquals("value1", map.getFirst("key"));
        assertNull(map.getFirst("other"));
    }

    @Test
    public void set() {
        map.set("key", "value1");
        map.set("key", "value2");
        assertEquals(1, map.size());
        assertEquals(Collections.singletonList("value2"), map.get("key"));
    }

    @Test
    public void equals() {
        map.set("key1", "value1");
        assertEquals(map, map);
        MultiValueMap<String, String> o1 = new LinkedMultiValueMap<String, String>();
        o1.set("key1", "value1");
        assertEquals(map, o1);
        assertEquals(o1, map);
        Map<String, List<String>> o2 = new HashMap<String, List<String>>();
        o2.put("key1", Collections.singletonList("value1"));
        assertEquals(map, o2);
        assertEquals(o2, map);
    }
}
