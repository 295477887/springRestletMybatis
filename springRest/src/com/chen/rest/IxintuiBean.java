package com.chen.rest;

/**
 * @author chenjie
 * @version 1.0
 * @since 2017-02-09
 */
public class IxintuiBean
{
    private String messageId;
    private String plateNumber;
    private String token;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "IxintuiBean{" +
                "messageId='" + messageId + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
