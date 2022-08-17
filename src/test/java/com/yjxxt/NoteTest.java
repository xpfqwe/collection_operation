package com.yjxxt;



import com.yjxxt.service.NoteServicePro;
import org.junit.Before;
import org.junit.Test;

public class NoteTest {

    private NoteServicePro noteServicepro = null;

    @Before
    public void init(){
        System.out.println("-----------测试stream流的增删改查-------------");
        noteServicepro = new NoteServicePro();
    }

    @Test
    public void listNoteTest(){
        noteServicepro.listNote12();
    }




}
