package com.yfzm;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class WordLadderTest {

    private WordLadder wl = new WordLadder();

    @Test
    public void invokeCreateLadderReturnTrue(){
        assertTrue(wl.createLadder("code", "data"));
        assertEquals(5, wl.getLadderStep());

        assertTrue(wl.createLadder("ghost", "boo"));
        assertEquals(5, wl.getLadderStep());

        assertTrue(wl.createLadder("marty", "keith"));
        assertEquals(7, wl.getLadderStep());

        assertTrue(wl.createLadder("dog", "cat"));
        assertEquals(4, wl.getLadderStep());

        assertTrue(wl.createLadder("Work", "Play"));
        assertEquals(7, wl.getLadderStep());

        assertTrue(wl.createLadder("Sleep", "aWAKE"));
        assertEquals(9, wl.getLadderStep());

        assertTrue(wl.createLadder("jealous", "ZEALOUS"));
        assertEquals(2, wl.getLadderStep());

        assertTrue(wl.createLadder("ANGEL", "devil"));
        assertEquals(9, wl.getLadderStep());
    }

    @Test
    public void invokeCreateLadderReturnFalse(){
        assertFalse(wl.createLadder("metal", "azure"));
        assertEquals(0, wl.getLadderStep());

        assertFalse(wl.createLadder("kwyjibo", "fluxbar"));
        assertEquals(0, wl.getLadderStep());

        assertTrue(wl.createLadder("car", "banana"));
        assertEquals(6, wl.getLadderStep());
    }


        @Test
    public void givenFirstStringEmptyWhenInvokeCreateLadderReturnFalse(){
        assertFalse(wl.createLadder("", "data"));
        assertEquals(0, wl.getLadderStep());
    }

    @Test
    public void givenLastStringEmptyWhenInvokeCreateLadderReturnFalse(){
        assertFalse(wl.createLadder("partial", ""));
        assertEquals(0, wl.getLadderStep());
    }

    @Test
    public void givenLastStringEqualsFirstStringWhenInvokeCreateLadderReturnFalse() {
        assertFalse(wl.createLadder("kitty", "kitty"));
        assertEquals(0, wl.getLadderStep());
    }

    private int invokeInitWord(String s1, String s2) {

        try {
            Field first = wl.getClass().getDeclaredField("first"),
                    last = wl.getClass().getDeclaredField("last"),
                    reverse = wl.getClass().getDeclaredField("is_reverse");
            first.setAccessible(true);
            last.setAccessible(true);
            reverse.setAccessible(true);
            first.set(wl, s1);
            last.set(wl, s2);
            Method initWord = wl.getClass().getDeclaredMethod("initWords");
            initWord.setAccessible(true);

            initWord.invoke(wl);
            if ((boolean)reverse.get(wl)){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e){
            return -1;
        }
    }

    @Test
    public void setFirstAndLastShouldReverse(){
        assertEquals(1, invokeInitWord("sleep", "awake"));
    }

    @Test
    public void setFirstAndLastShouldNotReverse(){
        assertEquals(0, invokeInitWord("awake", "sleep"));
    }



}