package com.jhon.recruitmentanalysis.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhon.recruitmentanalysis.pojo.User;
import com.jhon.recruitmentanalysis.service.UserService;
import com.jhon.recruitmentanalysis.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/admin/login")
    public R login(@RequestBody User user){

        User user1 = userService.getAllUserByName(user.getName());

        if (user1 != null) {
            if (userService.getPwdByName(user.getName()).equals(user.getPassword())){
                Map<String,Object> map = new LinkedHashMap<>();
                map.put("user",user1);
                return R.ok().message("登录成功!").data(map);
            } else {
                return R.error().message("登录失败，密码错误");
            }
        }

        return R.error().code(404).message("用户未注册");

    }

    @PostMapping("/admin/register")
    public R register(@RequestBody User user){

        User user1 = userService.getAllUserByName(user.getName());

        if (user1 != null) {
            return R.error().code(400).message("此用户名已存在,请重新选择用户名");
        }

        Integer integer = userService.insertUser(user);

        if (integer > 0){
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("user",user);
            return R.ok().message("用户注册成功").data(map);
        } else {
            return R.ok().message("用户注册失败");
        }

    }

    @GetMapping("/admin/getUsers")
    public R getUsers(@RequestParam(value = "query",required = false) String query,
                      @RequestParam("pageNum") int pageNum,
                      @RequestParam("pageSize") int pageSize){

        PageHelper.startPage(pageNum,pageSize);

        List<User> userList = userService.getAllUser(query);

        PageInfo<User> pageInfo = new PageInfo<>(userList);

        if(pageInfo != null){
            Map<String,Object> map=new HashMap<>();
            map.put("userList",pageInfo);
            return R.ok().message("查询成功").data(map);
        }else {
            return R.error().message("当前没有用户注册");
        }

    }

    @GetMapping("/admin/getUserById")
    public R getUserById(@RequestParam("id") Integer id){

        User user = userService.getUserById(id);

        if (user != null) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("user",user);
            return R.ok().message("查询成功").data(map);
        }

        return R.error().message("未查询到该用户");

    }

    @GetMapping("/admin/addUser")
    public R addUser(@RequestParam("name") String name,
                     @RequestParam("password") String password){

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Integer i = userService.insertUser(user);

        if (i > 0) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("user",user);
            return R.ok().message("添加用户成功").data(map);
        }

        return R.error().message("添加用户失败");

    }


    @GetMapping("/admin/deleteUser")
    public R deleteUser(@RequestParam("id") Integer id){

        Integer i = userService.deleteUser(id);

        if (i > 0) {
            return R.ok().message("删除用户成功");
        }

        return R.error().message("删除用户失败");

    }

    @GetMapping("/admin/updateUser")
    public R updateUser(@RequestParam("name") String name,
                        @RequestParam("password") String password){

        Integer i = userService.updateUser(name,password);

        if (i > 0) {
            return R.ok().message("修改用户成功");
        }

        return R.error().message("修改用户失败");

    }

}
