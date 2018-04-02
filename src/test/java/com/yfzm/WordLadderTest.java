package com.yfzm;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordLadderTest {

    private WordLadder wl = new WordLadder();

    @Test
    public void getLadderStep() {
        assertEquals(true, wl.createLadder("code", "data"));
        assertEquals(5, wl.getLadderStep());

        assertEquals(false, wl.createLadder("", "data"));
        assertEquals(0, wl.getLadderStep());

        assertEquals(true, wl.createLadder("ghost", "boo"));
        assertEquals(5, wl.getLadderStep());

        assertEquals(true, wl.createLadder("marty", "keith"));
        assertEquals(7, wl.getLadderStep());

        assertEquals(false, wl.createLadder("kitty", "kitty"));
        assertEquals(0, wl.getLadderStep());

        assertEquals(true, wl.createLadder("dog", "cat"));
        assertEquals(4, wl.getLadderStep());

        assertEquals(true, wl.createLadder("Work", "Play"));
        assertEquals(7, wl.getLadderStep());

        assertEquals(true, wl.createLadder("Sleep", "aWAKE"));
        assertEquals(9, wl.getLadderStep());

        assertEquals(true, wl.createLadder("jealous", "ZEALOUS"));
        assertEquals(2, wl.getLadderStep());

        assertEquals(true, wl.createLadder("ANGEL", "devil"));
        assertEquals(9, wl.getLadderStep());

        assertEquals(false, wl.createLadder("metal", "azure"));
        assertEquals(0, wl.getLadderStep());

        assertEquals(false, wl.createLadder("kwyjibo", "fluxbar"));
        assertEquals(0, wl.getLadderStep());

        assertEquals(true, wl.createLadder("car", "banana"));
        assertEquals(6, wl.getLadderStep());

        assertEquals(false, wl.createLadder("partial", ""));
        assertEquals(0, wl.getLadderStep());

    }
}