package com.wj.store.util;

import java.io.Serializable;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/7 5:11
 */
public class JsonResult<E> implements Serializable {
    private Integer state;
    private String message;
    private E data;

    public static<E> JsonResult<E> ok(E data) {
        JsonResult<E> jsonResult = new JsonResult<>();
        jsonResult.data = data;
        jsonResult.state = 200;
        jsonResult.message = "操作成功！";
        return jsonResult;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult() {

    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
