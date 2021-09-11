package com.wj.store.controller;

import com.wj.store.controller.ex.*;
import com.wj.store.service.ex.*;
import com.wj.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/7 6:58
 */
public class BaseController {
    public static final int OK = 200;

    @ExceptionHandler({ServiceException.class,FileUploadException.class}) //用于统一处理抛出异常
    public JsonResult<Void> handlerException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
        }else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
        }else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
        } else if (e instanceof AccessDeniedException) {
            result.setState(4005);
        }else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
        } else if (e instanceof InsertException) {
            result.setState(5000);
        } else if (e instanceof UpdateException) {
            result.setState(5001);
        }else if (e instanceof DeleteException) {
            result.setState(5002);
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }
        return result;
    }

    /**
     *
     * @param session session对象
     * @return 当前登陆的用户uid的值
     */
    protected  final Integer getuidFromSession(HttpSession session){
       return Integer.valueOf(session.getAttribute("uid")
               .toString());
    }

    /**
     * @param session session对象
     * @return 当前登陆的用户username的值
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}