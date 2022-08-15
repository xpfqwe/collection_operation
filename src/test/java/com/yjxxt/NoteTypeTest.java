package com.yjxxt;

import com.yjxxt.service.NoteTypeService;
import org.junit.Before;
import org.junit.Test;

public class NoteTypeTest {

    private NoteTypeService noteTypeService;

    @Before
    public void init(){
        System.out.println("方法执行前.....");
        noteTypeService = new NoteTypeService();
    }

    @Test
    //查询数据
    public void addNoteType(){
        noteTypeService.listNoteType(2);
    }

}
