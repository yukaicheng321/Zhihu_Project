package com.lenovo.example.zhihu_project.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by lenovo on 2019/9/11.
 */
@Entity
public class CollectBean {
    @Id
    private Long id;
    @NotNull
    private String img;
    @NotNull
    private String content;
    @NotNull
    private String where;
    @Transient
    private int nowId;

    public int getNowId() {
        return nowId;
    }

    public void setNowId(int nowId) {
        this.nowId = nowId;
    }

    public CollectBean(Long id, String img, String content, String where, int nowId) {
        this.id = id;
        this.img = img;
        this.content = content;
        this.where = where;
        this.nowId = nowId;
    }

    @Generated(hash = 2080133141)
    public CollectBean(Long id, @NotNull String img, @NotNull String content,
            @NotNull String where) {
        this.id = id;
        this.img = img;
        this.content = content;
        this.where = where;
    }
    @Generated(hash = 420494524)
    public CollectBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWhere() {
        return this.where;
    }
    public void setWhere(String where) {
        this.where = where;
    }
  

}
