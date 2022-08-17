package com.yjxxt.service;

import com.yjxxt.pojo.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 云日记-用户管理模块
 *        用户登陆
 *        用户注册
 *        用户列表展示
 *        用户信息更新
 *        用户信息删除
 */
public class UserService {

    /**
     * 用户记录如何存储???
     *    List-->ArrayList<User>
     *    Map-->HashMap<Integer,User>
     *    Set-->HashSet<User>
     *    List<Map<k,v>>
     */
    //用户信息存储
    public List<User> users;
    //原有的数据
    public UserService(){
        users = new ArrayList<>();
        users.add(new User(1,"admin","123456","admin","",""));
        users.add(new User(2,"test","123456","test","",""));
    }

    //用户登陆
    public void loginUser(String userName,String userPwd){
        /**
         * 1.非空校验
         *      用户名 | 密码
         * 2.根据用户名查找用户记录
         * 3.记录存在判断
         *      不存在-->抛异常
         * 4.用户存在
         *      用户密码比较
         *          不正确-->抛异常
         * 5.密码正确
         *      登陆成功
         */
        //1.非空
        if (StringUtils.isBlank(userName)){
            throw new RuntimeException("用户名不能为空...");
        }
        if(StringUtils.isBlank(userPwd)){
            throw new RuntimeException("用户密码不能为空...");
        }
        //2.根据用户查找记录
        Integer index = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName)){
                index = i;
                break;
            }
        }
        //3.不存在抛出
        if (index == null){
            throw new RuntimeException("用户记录不存在...");
        }
        //4.用户存在比较密码
        User temp = users.get(index);
        if(!temp.getUserPwd().equals(userPwd)){
            throw new RuntimeException("用户密码错误...");
        }
        //5.登陆成功
        System.out.println("用户登陆成功");
    }

    //添加用户记录
    public void addUser(User user){
        /**
         * 1.参数合法校验
         *      用户名 、 密码 、 昵称 、 非空
         * 2.用户名唯一 | 昵称唯一
         * 3.执行添加，返回结果
         */
        //校验user 是否有数据
        if (user == null){
            throw  new RuntimeException("用户记录为空");
        }
        //校验用户名非空
        if (StringUtils.isBlank(user.getUserName())){
            throw new RuntimeException("用户名不能为空");
        }
        //校验密码非空
        if (StringUtils.isBlank(user.getUserPwd())){
            throw new RuntimeException("用户密码不能为空");
        }
        //校验昵称非空
        if (StringUtils.isBlank(user.getNick())){
            throw new RuntimeException("昵称不能为空");
        }

        //唯一校验    遍历集合里原有的数据
        for (User tamp:users){
            //集合里的数据与传过来的数据对比
            if (tamp.getUserName().equals(user.getUserName())){
                throw new RuntimeException("用户名已存在");
            }
        }
  /*      if (users.contains(user)){
            throw new RuntimeException("用户名已存在");
        }*/

        users.add(user);
    }

    //查询用户信息
    public void  listUser(){
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
    }

    //更新用户记录
    public void updateUser(User user){
        /**
         * 1.校验非空  |  用户是否存在
         *      用户名 、 密码 、 昵称  非空
         * 2.根据ID查询用户记录是否存在
         *      不存在-->抛异常（更新数据不存在）
         *3.记录存在，判断用户名 、 昵称  是否重复
         *      用户名 、 昵称  非空校验
         * 4.执行更新 、 判断结果
         */
        //1.校验非空 、
        if (user == null){
            throw  new RuntimeException("用户不存在");
        }
        if (StringUtils.isBlank(user.getUserName())){
            throw new RuntimeException("用户名不能为空...");
        }
        if (StringUtils.isBlank(user.getUserPwd())){
            throw new RuntimeException("用户密码不能为空...");
        }
        if (StringUtils.isBlank(user.getNick())){
            throw new RuntimeException("用户昵称不能为空...");
        }
        //2.根据ID查询用户记录
        if (user.getId() == null || null == findUserByUserId(user.getId())){
            throw new RuntimeException("用户记录不存在...");
        }
        //3.用户名 、 昵称  非空
        //用户名
        long count = users.stream().filter(u->u.getUserName().equals(user.getUserName()))
                                    .filter(u->!(u.getId().equals(user.getId())))
                                    .count();
        if (count ==1){
            throw new RuntimeException("用户名已存在...");
        }
        //昵称
        count = users.stream().filter(u->u.getNick().equals(user.getNick()))
                                .filter(u->!(u.getId().equals(user.getId())))
                                .count();
        if (count ==1){
            throw new RuntimeException("用户昵称已存在...");
        }

        //4.更新 执行
        users.set(users.indexOf(findUserByUserId(user.getId())),user);
    }

    //封装的查询方法
    public User findUserByUserId(Integer userId) {
        Integer index = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)){
                index = i;
                break;
            }
        }
        return index == null?null:users.get(index);
    }

    //删除用户信息
    public void delUser(Integer userId){
        /**
         * id 唯一 、 用户名唯一 、 昵称唯一
         * 1.确定记录存在性
         *      不存在-->抛异常
         * 2.存在 执行删除
         */
        User result = this.findUserByUserId(userId);
        if (null == result) {
            throw new RuntimeException("待删除的记录不存在...");
        }
        users.remove(result);
    }

}

