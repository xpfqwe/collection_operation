package com.yjxxt.service;

import java.util.*;

/**
 * 云记管理
 * 云记添加
 * 云记列表展示
 * 云记更新
 * 云记删除
 */
public class NoteService {

    private List<Map<String,Object>> noteList=null;

    private NoteTypeService noteTypeService;

/*    public NoteTypeService getNoteTypeService() {
        return noteTypeService;
    }*/

    public void setNoteTypeService(NoteTypeService noteTypeService) {
        this.noteTypeService = noteTypeService;
    }

    public NoteService() {
        noteList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("typeId", 1);
        map.put("title", "hello");
        map.put("pubTime", new Date());
        map.put("content", "hello");
        noteList.add(map);
    }

    public Integer countNoteByNoteTypeId(Integer noteTypeId) {
        Integer count = 0;
        for (Map<String, Object> map : noteList) {
            if (map.get("typeId").equals(noteTypeId)){
                count = count + 1;
            }
        }
        return count;
    }
}


