package com.yjxxt.service;

import com.yjxxt.pojo.Note;
import com.yjxxt.pojo.NoteType;
import org.apache.commons.lang3.ObjectUtils;
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

    private UserService userService = new UserService();

    private NoteService noteService = new NoteService();

    //集合存储数据
    private Map<Integer, NoteType> noteTypeMap;


    public NoteTypeService() {
    noteTypeMap = new HashMap<>();
    noteTypeMap.put(1,new NoteType(1,"java",1));
    noteTypeMap.put(2,new NoteType(2,"php",1));
    noteTypeMap.put(3,new NoteType(3,"scala",2));
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
            throw new RuntimeException("云记记录不能为空...");
        }
        if (StringUtils.isBlank(noteType.getTypeName())){
            throw new RuntimeException("云记类别名不能为空...");
        }
        if (noteType.getUserId() == null || null == userService.findUserByUserId(noteType.getUserId())){
            throw new RuntimeException("用户记录不存在...");
        }
        //2.用户类别唯一校验
        Boolean flag = checkNoteTypeNameUnique(noteType.getTypeName(),noteType.getUserId());
        if (!flag){
            throw new RuntimeException("云记类别名称不能重复...");
        }
        //3.执行添加操作
        noteTypeMap.put(noteType.getId(),noteType);
    }

    //类别名称唯一校验
    private Boolean checkNoteTypeNameUnique(String typeName, Integer userId) {
        //遍历数据
        for (NoteType noteType : noteTypeMap.values()) {
            //遍历到的数据通过ID、类名对比，是否存在
            if (noteType.getUserId()==userId && noteType.getTypeName().equals(typeName)){
                return false;
            }
        }
        return true;
    }


    //根据登陆用户查询云记类别
    public void listNoteType(Integer userId){
        //遍历数据
        for (Map.Entry<Integer, NoteType> integerNoteTypeEntry : noteTypeMap.entrySet()) {
            //通过遍历的数据 获取value ，userId 对比传过的userId
            if (integerNoteTypeEntry.getValue().getUserId().equals(userId)){
                //打印数据
                System.out.println(integerNoteTypeEntry.getValue());
            }
        }
/*
        noteTypeMap.values().stream().filter(n->n.getUserId().equals(userId)).forEach(n->{
            System.out.println(n);
        });
*/
/*        noteTypeMap.values().stream().filter(n->n.getUserId().equals(userId)).forEach(n->{
            System.out.println(n);
        });*/

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

        //1.非空校验
        if (noteType == null){
            throw new RuntimeException("云记记录不能为空...");
        }
        if (StringUtils.isBlank(noteType.getTypeName())){
            throw new RuntimeException("云记类别名不能为空...");
        }
        if(noteType.getUserId()==null || null == userService.findUserByUserId(noteType.getUserId())){
            throw  new RuntimeException("用户记录不存在!");
        }
        NoteType temp =  noteTypeMap.get(noteType.getId());
        if (temp==null){
            throw new RuntimeException("记录不存在！");
        }
       /* if (noteTypeMap.containsKey(noteType.getId())){
            throw new RuntimeException("记录不存在！");
        }*/

        //2.类别名唯一校验
        for (Map.Entry<Integer, NoteType> integerNoteTypeEntry : noteTypeMap.entrySet()) {
            if (integerNoteTypeEntry.getValue().getTypeName().equals(noteType.getTypeName())
                    && !(integerNoteTypeEntry.getValue().getId().equals(noteType.getId()))
                    && integerNoteTypeEntry.getValue().getUserId().equals(noteType.getUserId())){
                throw new RuntimeException("类别名称已存在！");
            }
        }
        //更新
        for (Map.Entry<Integer, NoteType> integerNoteTypeEntry : noteTypeMap.entrySet()){
            if (integerNoteTypeEntry.getValue().getId().equals(noteType.getId())){
                noteTypeMap.replace(integerNoteTypeEntry.getKey(),noteType);
                break;
            }
        }
    }

    //删除用户记录
    public void delNoteType(Integer noteTypeId){
        /**
         * 1.参数校验
         *    云记类别id 必须存在
         * 2.如果类别下存在云记记录 不允许删除
         * 3.执行删除
         */
        //1.非空
        if (!noteTypeMap.containsKey(noteTypeId)){
            throw new RuntimeException("待删除的记录不存在");
        }
        //2.如果存在数据 不允许删除
        Integer count = noteService.countNoteByNoteTypeId(noteTypeId);
        if (count>0){
            throw new RuntimeException("存在云记记录，不允许删除！");
        }
        //3.删除
        noteTypeMap.remove(noteTypeId);




    }


}
