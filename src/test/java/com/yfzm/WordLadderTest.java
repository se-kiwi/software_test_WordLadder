package com.yfzm;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.Assert.*;

public class WordLadderTest {

    private WordLadder wl = new WordLadder();

    @Test
    public void invokeCreateLadderReturnTrue() {
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
    public void invokeCreateLadderReturnFalse() {
        assertFalse(wl.createLadder("metal", "azure"));
        assertEquals(0, wl.getLadderStep());

        assertFalse(wl.createLadder("kwyjibo", "fluxbar"));
        assertEquals(0, wl.getLadderStep());

        assertTrue(wl.createLadder("car", "banana"));
        assertEquals(6, wl.getLadderStep());
    }


    @Test
    public void givenFirstStringEmptyWhenInvokeCreateLadderReturnFalse() {
        assertFalse(wl.createLadder("", "data"));
        assertEquals(0, wl.getLadderStep());
    }

    @Test
    public void givenLastStringEmptyWhenInvokeCreateLadderReturnFalse() {
        assertFalse(wl.createLadder("partial", ""));
        assertEquals(0, wl.getLadderStep());
    }

    @Test
    public void givenLastStringEqualsFirstStringWhenInvokeCreateLadderReturnFalse() {
        assertFalse(wl.createLadder("kitty", "kitty"));
        assertEquals(0, wl.getLadderStep());
    }

    private void invokeInitWord(String s1, String s2) throws Exception {
        Field first = wl.getClass().getDeclaredField("first"),
                last = wl.getClass().getDeclaredField("last");
        first.setAccessible(true);
        last.setAccessible(true);
        Method initWord = wl.getClass().getDeclaredMethod("initWords");
        initWord.setAccessible(true);

        first.set(wl, s1);
        last.set(wl, s2);
        initWord.invoke(wl);
    }

    private boolean invokeInitWordReturnSameLen(String s1, String s2) throws Exception {
        invokeInitWord(s1, s2);
        Field sameLen = wl.getClass().getDeclaredField("same_len");
        sameLen.setAccessible(true);
        return (Boolean) sameLen.get(wl);


    }

    private boolean invokeInitWordReturnReverse(String s1, String s2) throws Exception {
        invokeInitWord(s1, s2);
        Field reverse = wl.getClass().getDeclaredField("is_reverse");
        reverse.setAccessible(true);
        return (boolean) reverse.get(wl);
    }

    @Test
    public void setFirstAndLastShouldReverse() throws Exception {
        assertTrue(invokeInitWordReturnReverse("sleep", "awake"));
    }

    @Test
    public void setFirstAndLastShouldNotReverse() throws Exception {
        assertFalse(invokeInitWordReturnReverse("awake", "sleep"));
    }

    @Test
    public void setSameLengthFirstAndLastShouldSameLenEqualTrue() throws Exception {
        assertTrue(invokeInitWordReturnSameLen("awake", "sleep"));
    }

    @Test
    public void setDifferentLengthFirstAndLastShouldSameLenEqualFalse() throws Exception {
        assertFalse(invokeInitWordReturnSameLen("wake", "sleep"));
    }

    private Set<String> setSameLenAndInvokeGetNeighbourWords(Boolean b, String s) throws Exception {
        Field sameLen = wl.getClass().getDeclaredField("same_len"),
                neighbourWords = wl.getClass().getDeclaredField("neighbourWords");
        neighbourWords.setAccessible(true);
        sameLen.setAccessible(true);
        sameLen.set(wl, b);

        Method getNeighbourWords = wl.getClass().getDeclaredMethod("getNeighbourWords", String.class);
        getNeighbourWords.setAccessible(true);
        getNeighbourWords.invoke(wl, s);
        //noinspection unchecked
        return (Set<String>) neighbourWords.get(wl);
    }

    @Test
    public void setSameLenTrueAndGivenStringInvokeGetNeighbourWords() throws Exception {
        Set<String> s = setSameLenAndInvokeGetNeighbourWords(true, "awake");
        assertEquals(3, s.size());

    }

    @Test
    public void setSameLenFalseAndGivenStringInvokeGetNeighbourWords() throws Exception {
        Set<String> s = setSameLenAndInvokeGetNeighbourWords(false, "awake");
        assertEquals(7, s.size());
    }
}