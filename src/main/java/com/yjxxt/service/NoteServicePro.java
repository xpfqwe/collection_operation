package com.yjxxt.service;

import com.yjxxt.pojo.Note;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通过stream 流来测试
 *      云记管理
 *      云记添加
 *      云记列表展示
 *      云记更新
 *      云记删除
 */
public class NoteServicePro {

    private List<Note> noteList;

    public NoteServicePro() {
        noteList =new ArrayList<Note>();
        noteList.add(new Note(1,"java","java",1,new Date(),20));
        noteList.add(new Note(2,"php","php",3,new Date(),88));
        noteList.add(new Note(4,"scala","scala",1,new Date(),40));
        noteList.add(new Note(6,"nodejs","nodejs",2,new Date(),55));
        noteList.add(new Note(7,"html","html",1,new Date(),70));
        noteList.add(new Note(10,"css","css",1,new Date(),68));
        noteList.add(new Note(12,"aa","aa",1,new Date(),90));
        noteList.add(new Note(15,"cc","cc",1,new Date(),100));
    }


    //查询
    public void listNote(){
        noteList.stream().forEach(n->{
            System.out.println(n);
        });
    }

    //查询id = 1 的所有云记的标题
    public void listNote01(){
        List<String> titleList = noteList.stream()
                                            .filter(n -> n.getTypeId() == 1)
                                            .map(Note::getTitle)
                                            .collect(Collectors.toList());
        //遍历出拿到的数据
        titleList.forEach(t->{
            System.out.println(t);
        });
    }

    //查询点击量超过50的所有云记记录
    public void listNote02(){
        List<Note> noteList = this.noteList.stream()
                                            .filter(n -> n.getClick() > 50)
                                            .collect(Collectors.toList());
        //遍历拿到的数据
        noteList.forEach(n->{
            System.out.println(n);
        });
    }
    
    //查询类别id = 2 点击量超过50  所有的云记标题
    public void listNote03(){
        List<String> titleList = noteList.stream()
                                        .filter(n -> n.getTypeId() == 2)
                                        .filter(c -> c.getClick() > 50)
                                        .map(Note::getTitle)
                                        .collect(Collectors.toList());
        //遍历出来拿到的数据
        titleList.forEach(n->{
            System.out.println(n);
        });
    }

    //查询类别id= 1 点击量超过50 云记标题 前两天数据
    public void listNote04(){
        List<String>  titleList  = noteList.stream()
                                            .filter(n -> n.getTypeId() == 1)
                                            .filter(n -> n.getClick() > 50)
                                            .map(Note::getTitle)
                                            .limit(3)
                                            .collect(Collectors.toList());
        //遍历拿到的数据
        titleList.forEach(n->{
            System.out.println(n);
        });
    }

    //查询类别id=1 点击量超过50 云记标题（分页查找-每页两条  查询第二页数据）
    public void listNote05() {
        List<String> titleList = noteList.stream()
                                            .filter(n -> n.getTypeId() == 1)
                                            .filter(n -> n.getClick() > 50)
                                            .map(Note::getTitle)
                                            .skip(2)
                                            .limit(3)
                                            .collect(Collectors.toList());
        //遍历拿到的数据
        titleList.forEach(n -> {
                    System.out.println(n);
                });
    }

    //分组统计每个类别下云记数量
    public void listNote06(){
        Map<Integer, List<Note>> result = noteList.stream()
                                                    .collect(Collectors.groupingBy(Note::getTypeId));
        //分别遍历key | value 的数据
        result.forEach((k,v)->{
            System.out.println("类别id："+k);
            v.forEach(n->{
                System.out.println(n);
            });
            System.out.println("------------------------");
        });
    }

    //根据类别id 统计每个类别下云记的数量
    public void listNote07(){
        Map<Integer, Long> collect = noteList.stream()
                .collect(Collectors.groupingBy(Note::getTypeId, Collectors.counting()));
        collect.forEach((k,v)->{
            System.out.println("类别id："+k+",总数："+v);
        });
    }

    //根据类别发布日期  统计每个类别下云记的数量
    public void listNote08(){
        Map<Date, Long> collect = noteList.stream()
                    .collect(Collectors.groupingBy(Note::getPubTime, Collectors.counting()));
        //遍历数据
        collect.forEach((k,v)->{
            System.out.println("日期："+new SimpleDateFormat("yyyy-MM-dd").format(k)+",总数："+v);
        });
    }

    //对数据降序排序
    public void  listNote09(){
        List<Note> collect = noteList.stream()
                                //两种降序方式
                                .sorted((n1, n2) -> n1.getClick().compareTo(n2.getClick()))
//                                .sorted(Comparator.comparing(Note::getClick))
                                .collect(Collectors.toList());
        collect.forEach(n->{
            System.out.println(n);
        });
    }

    //查询云记记录点击量最低的点击量值
    public void listNote10(){
        Integer min = noteList.stream().map(n -> n.getClick()).reduce(Integer::min).get();
        System.out.println(min);
    }

    //查询云记记录点击量最低的点击量值
    public void listNote11(){
        Integer max = noteList.stream().map(n -> n.getClick()).reduce(Integer::max).get();
        System.out.println(max);
    }

    //查询云记记录点击量总和
    public void listNote12(){
        Integer sum = noteList.stream().map(n -> n.getClick()).reduce(Integer::sum).get();
        System.out.println(sum);
    }
}


