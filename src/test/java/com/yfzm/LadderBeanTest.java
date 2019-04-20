package com.yfzm;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LadderBeanTest {

    private LadderBean null_bean;
    private LadderBean full_bean;
    ArrayList<String> list;

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
        assertEquals("testbegin", null_bean.getBegin());
    }

    @Test
    public void getEnd() {
        assertEquals("end", full_bean.getEnd());
    }

    @Test
    public void setEnd() {
        null_bean.setEnd("testend");
        assertEquals("testend", null_bean.getEnd());
    }

    @Test
    public void getLength() {
        assertEquals(5, full_bean.getLength());
    }

    @Test
    public void setLength() {
        null_bean.setLength(5);
        assertEquals(5, null_bean.getLength());
    }

    @Test
    public void getLadder() {
        assertEquals(list, full_bean.getLadder());
    }

    @Test
    public void setLadder() {
        null_bean.setLadder(list);
        assertEquals(list, null_bean.getLadder());
    }
}