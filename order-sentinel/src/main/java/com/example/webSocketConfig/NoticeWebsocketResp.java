package com.example.webSocketConfig;

/**
 * @author JLS
 * @description:
 * @since 2023-06-19 18:52
 */

public class NoticeWebsocketResp<T> {

    private String noticeType;


    private T noticeInfo;

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public T getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(T noticeInfo) {
        this.noticeInfo = noticeInfo;
    }
}