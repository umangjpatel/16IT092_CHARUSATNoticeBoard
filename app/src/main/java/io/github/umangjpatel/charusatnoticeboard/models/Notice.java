package io.github.umangjpatel.charusatnoticeboard.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Notice implements Serializable {

    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("uid")
    @Expose
    private int mUid;

    @SerializedName("category")
    @Expose
    private String mCategory;

    @SerializedName("Title")
    @Expose
    private String mTitle;

    @SerializedName("start_date")
    @Expose
    private String mStartDate;

    @SerializedName("end_date")
    @Expose
    private String mEndDate;

    @SerializedName("content")
    @Expose
    private String mContent;

    @SerializedName("docname")
    @Expose
    private String mDocName;

    @SerializedName("docpath")
    @Expose
    private String mDocPath;

    @SerializedName("upload_date")
    @Expose
    private String mUploadDate;

    @SerializedName("valid_date")
    @Expose
    private String mValidDate;

    @SerializedName("created")
    @Expose
    private String mCreated;

    @SerializedName("modified")
    @Expose
    private String mModified;

    public Notice(int id, int uid, String category, String title, String startDate, String endDate, String content, String docName, String docPath, String uploadDate, String validDate, String created, String modified) {
        mId = id;
        mUid = uid;
        mCategory = category;
        mTitle = title;
        mStartDate = startDate;
        mEndDate = endDate;
        mContent = content;
        mDocName = docName;
        mDocPath = docPath;
        mUploadDate = uploadDate;
        mValidDate = validDate;
        mCreated = created;
        mModified = modified;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getUid() {
        return mUid;
    }

    public void setUid(int uid) {
        mUid = uid;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getDocName() {
        return mDocName;
    }

    public void setDocName(String docName) {
        mDocName = docName;
    }

    public String getDocPath() {
        return mDocPath;
    }

    public void setDocPath(String docPath) {
        mDocPath = docPath;
    }

    public String getUploadDate() {
        return mUploadDate;
    }

    public void setUploadDate(String uploadDate) {
        mUploadDate = uploadDate;
    }

    public String getValidDate() {
        return mValidDate;
    }

    public void setValidDate(String validDate) {
        mValidDate = validDate;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getModified() {
        return mModified;
    }

    public void setModified(String modified) {
        mModified = modified;
    }


}
