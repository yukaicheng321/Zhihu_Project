package com.lenovo.example.zhihu_project.model.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/9.
 */

public class ColumnTalkBean {

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * author : umaru
         * content : 推荐苦瓜炒腊肉。真心好吃。夏天吃凉拌苦瓜真的很棒的口感。周围80
         * %朋友都不吃苦瓜，我可是从小不挑食什么都吃的！虽然小时候吃苦瓜感觉很苦，嚼一下就吐出来，但是吃多了会被麻痹的，自然而感就能接受了。老人家说吃苦瓜败火，不知真假。反正暑假回国都会要求家里做苦瓜的！
         * avatar : http://pic4.zhimg.com/c7e7d3686e616bc3b7da514ddb1a22af_im.jpg
         * time : 1438123562
         * id : 20039560
         * likes : 0
         * reply_to : {"content":"甭管炖什么肉 往里加切块的胡萝卜 炖到萝卜跟豆腐一样烂 完了头一顿还不如味儿 放到下一顿你在吃 胡萝卜比肉好吃",
         * "status":0,"id":1282915,"author":"金十六"}
         */

        private String author;
        private String content;
        private String avatar;
        private int time;
        private int id;
        private int likes;
        private ReplyToBean reply_to;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public ReplyToBean getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToBean reply_to) {
            this.reply_to = reply_to;
        }

        public static class ReplyToBean {
            /**
             * content : 甭管炖什么肉 往里加切块的胡萝卜 炖到萝卜跟豆腐一样烂 完了头一顿还不如味儿 放到下一顿你在吃 胡萝卜比肉好吃
             * status : 0
             * id : 1282915
             * author : 金十六
             */

            private String content;
            private int status;
            private int id;
            private String author;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}
