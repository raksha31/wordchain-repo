package com.wordchain.test;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import com.wordchain.DictionaryMain;

import java.util.Arrays;
import java.util.List;
import  java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class DictionaryMainTest {

    private  DictionaryMain dictionaryMain;

    @Before
    public void setUp() throws Exception {
        dictionaryMain = new DictionaryMain();
    }

    @Test
    public void findWordChain_nullInput()
    {
        List<String> result=dictionaryMain.findWordChain("","");
        assertEquals(0,result.size());
    }

    @Test
    public void findWordChain_notEqualLengthInput()
    {
        List<String> result=dictionaryMain.findWordChain("god","jesus");
        assertEquals(0,result.size());
    }

    @Test
    public void findWordChain_sameWord()
    {
        List<String> actual=dictionaryMain.findWordChain("dog","dog");
        List<String> expected= Arrays.asList("dog");
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void findWordChain_equalLengthInput_ChainPresent()
    {
        List<String> actual=dictionaryMain.findWordChain("dog","cat");
        List<String> expected= Arrays.asList("dog","cat","cog","cag");
        assertTrue(actual.containsAll(expected));
    }


    @Test
    public void findWordChain_equalLengthInput_WithOutChain()
    {
        List<String> actual=dictionaryMain.findWordChain("dog","zng");
        List<String> expected= Arrays.asList("");
        assertEquals(0,actual.size());
    }

    @Test
    public void findWordChain_equalLengthInput_CaseSensitivity()
    {

        List<String> actual=dictionaryMain.findWordChain("dog","Cat");
        List<String> expected= Arrays.asList("dog","cat","cog","cag");
        assertTrue(actual.containsAll(expected));
    }

}
