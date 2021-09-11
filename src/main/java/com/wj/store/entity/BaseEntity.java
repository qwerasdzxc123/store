package com.wj.store.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author: WangJin
 * @description 实体类的基类
 * @date: 2021/9/6 18:54
 */


public class BaseEntity implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;

    // Generate: Getter and Setter、toString()


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(createdUser, that.createdUser) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedUser, that.modifiedUser) &&
                Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdUser='" + createdUser + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdUser, createdTime, modifiedUser, modifiedTime);
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
