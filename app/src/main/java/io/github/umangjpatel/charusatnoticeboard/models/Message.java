package io.github.umangjpatel.charusatnoticeboard.models;

public class Message {

    private String mId, mMessage;

    public Message() {
    }

    public Message(String id, String message) {
        mId = id;
        mMessage = message;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
