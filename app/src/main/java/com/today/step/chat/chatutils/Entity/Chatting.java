package com.today.step.chat.chatutils.Entity;


public class Chatting {

    private Integer id;

    private String sendUser;

    private String receiveUser;

    private String message;

    /**
     * 1 认证信息 2 发送消息
     */
    private Integer messagetype;

    /**
     * 1 未读 2 已读
     */
    private Integer isRead;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return send_user
     */
    public String getSendUser() {
        return sendUser;
    }

    /**
     * @param sendUser
     */
    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    /**
     * @return receive_user
     */
    public String getReceiveUser() {
        return receiveUser;
    }

    /**
     * @param receiveUser
     */
    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取1 认证信息 2 发送消息
     *
     * @return messagetype - 1 认证信息 2 发送消息
     */
    public Integer getMessagetype() {
        return messagetype;
    }

    /**
     * 设置1 认证信息 2 发送消息
     *
     * @param messagetype 1 认证信息 2 发送消息
     */
    public void setMessagetype(Integer messagetype) {
        this.messagetype = messagetype;
    }

    /**
     * 获取1 未读 2 已读
     *
     * @return is_read - 1 未读 2 已读
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * 设置1 未读 2 已读
     *
     * @param isRead 1 未读 2 已读
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

	@Override
	public String toString() {
		return "Chatting [id=" + id + ", sendUser=" + sendUser + ", receiveUser=" + receiveUser + ", message=" + message
				+ ", messagetype=" + messagetype + ", isRead=" + isRead + "]";
	}
    
    
}