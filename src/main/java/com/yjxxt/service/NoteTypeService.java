package com.yjxxt.service;

import com.yjxxt.pojo.NoteType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 云记类别管理
 *    云记类别遍历
 *    云记类别添加
 *    云记类别更新
 *    云记类别删除
 */
public class NoteTypeService {
    //集合存储数据
    private Map<Integer, NoteType> noteTypeMap;
    public NoteTypeService() {
    noteTypeMap = new HashMap<>();
    noteTypeMap.put(1,new NoteType(1,"java",1));
    noteTypeMap.put(2,new NoteType(2,"php",1));
    noteTypeMap.put(3,new NoteType(3,"scala",3));
    }

    //添加用户记录
    public void addNoteType(NoteType noteType){
        /**
         * 1.参数校验
         *    类别名 不能为空 用户id 必须存在(UserService->List<User> 必须存在对应用户记录)
         * 2.当前用户下类别名称不可重复
         * 3.执行添加
         */
        //1.先校验是否存在数据 、 非空
        if (noteType == null){
            throw new RuntimeException("类别为空...");
        }
        if (StringUtils.isBlank(noteType.getTypeName())){
            throw new RuntimeException("类别名不能为空...");
        }
    }


    //根据登陆用户查询云记类别
    public void listNoteType(Integer userId){

    }

    //更新用户记录
    public void updateNoteType(NoteType noteType){
        /**
         * 1.参数校验
         *    类别名 不能为空
         *    用户id 必须存在(UserService->List<User> 必须存在对应用户记录)
         *    云记类别id 必须存在
         * 2.当前用户下类别名称不可重复
         * 3.执行更新
         */
    }

    //删除用户记录
    public void delNoteType(Integer noteTypeId){
        /**
         * 1.参数校验
         *    云记类别id 必须存在
         * 2.如果类别下存在云记记录 不允许删除
         * 3.执行删除
         */
    }


}
