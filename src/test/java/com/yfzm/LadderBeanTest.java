package com.yfzm;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LadderBeanTest {

    private LadderBean null_bean;
    private LadderBean full_bean;
    private ArrayList<String> list;

    private static Object getValue(Object obj, String fieldName) {

        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            return null;
        }
    }

    @Before
    public void setUp() throws Exception {
        null_bean = new LadderBean();
        list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        full_bean = new LadderBean("begin", "end", 5, list);
    }

    @Test
    public void getBegin() {
        assertEquals("begin", full_bean.getBegin());
    }

    @Test
    public void setBegin() {
        null_bean.setBegin("testbegin");
        assertEquals("testbegin", getValue(null_bean, "begin"));
    }

    @Test
    public void getEnd() {
        assertEquals("end", full_bean.getEnd());
    }

    @Test
    public void setEnd() {
        null_bean.setEnd("testend");
        assertEquals("testend", getValue(null_bean, "end"));
    }

    @Test
    public void getLength() {
        assertEquals(5, full_bean.getLength());
    }

    @Test
    public void setLength() {
        null_bean.setLength(5);
        assertEquals(5, getValue(null_bean, "length"));
    }

    @Test
    public void getLadder() {
        assertEquals(list, full_bean.getLadder());
    }

    @Test
    public void setLadder() {
        null_bean.setLadder(list);
        assertEquals(list, getValue(null_bean, "ladder"));
    }
}