package com.yjxxt;

import com.yjxxt.pojo.NoteType;
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


    //查询数据
    @Test
    public void listNoteType(){
        noteTypeService.listNoteType(1);
    }

    //添加数据
    @Test
    public void addNoteType(){
        noteTypeService.addNoteType(new NoteType(4,"css",2));
        noteTypeService.listNoteType(2);
    }

    //更新数据
    @Test
    public void updateNoteType(){
        noteTypeService.updateNoteType(new NoteType(1,"html",1));
        noteTypeService.listNoteType(1);
    }

    //删除数据
    @Test
    public void deleteNoteType(){
        noteTypeService.listNoteType(1);
        noteTypeService.delNoteType(2);
        noteTypeService.listNoteType(1);
    }
}


