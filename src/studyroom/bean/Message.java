package studyroom.bean;

import java.util.Date;

/**
 * Message 实体类，用于存储留言信息
 */
public class Message {
    private int id;          // 留言ID
    private int userId;      // 用户ID
    private String content;  // 留言内容
    private Date createTime; // 留言创建时间

    public Message() {
    }

    public Message(int id, int userId, String content, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}