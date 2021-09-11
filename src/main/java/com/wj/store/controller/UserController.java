package com.wj.store.controller;

import com.wj.store.controller.ex.*;
import com.wj.store.entity.User;
import com.wj.store.service.IUserService;
import com.wj.store.service.ex.InsertException;
import com.wj.store.service.ex.UsernameDuplicatedException;
import com.wj.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/7 5:28
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);

        return new JsonResult<Void>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);
        //想session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        //获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword,
                                            HttpSession session) {
        // 调用session.getAttribute("")获取uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行修改密码
        userService.changePassword(uid, username, oldPassword, newPassword);
        // 返回成功
        return new JsonResult<Void>(OK);
    }

    @GetMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
// 从HttpSession对象中获取uid
        Integer uid = getuidFromSession(session);
// 调用业务对象执行获取数据
        User data = userService.getByUid(uid);
// 响应成功和数据
        return new JsonResult<User>(OK, data);
    }


//    @PostMapping("modify_userInfo")
//    public JsonResult<String> modifyUserInfo(@RequestBody User user,HttpSession session){
//        userService.changeInfo(user,session);
//        return JsonResult.ok("用户信息修改成功！");
//    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
// 从HttpSession对象中获取uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
// 调用业务对象执行修改用户资料.2
        userService.changeInfo(uid, username, user);
// 响应成功
        return new JsonResult<Void>(OK);
    }

    public static  final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        // 判断上传的文件是否为空
        if (file.isEmpty()){
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空！");
        }
        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE){
            // 是：抛出异常
            throw new FileSizeException("文件超出限制");
        }
        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        if(!AVATAR_TYPE.contains(contentType)){
            // 是：抛出异常
            throw new FileTypeException("文件类型不支持");
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        if(!dir.exists()){  //检测文件是否存在
            dir.mkdirs();  //创建当前目录
        }
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename=" + originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase()
                + suffix;
        File dest = new File(dir,filename);  //是一个空文件
        //参数file中的数据写入到这个空文件夹中
        try {
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        } catch (IOException e){
            throw new FileUploadException("文件读写异常");
        }
        String avatar = "/upload/" + filename;
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        userService.changeAvatar(uid,avatar,username);
        return new JsonResult<>(OK,avatar);

    }

}
