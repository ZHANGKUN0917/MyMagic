package com.shidonghui.mymagic.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ZhangKun
 * @create 2019/6/4
 * @Describe
 */
public class LivePushModel extends ResponseBaseModel {

    /**
     * errMsg :
     * data : {"masterTitles":"321","rtmpDownAddress":"rtmp://pili-live-rtmp.huataiyihe.com/shidonghui/LIVE1510038585HVILhA","livingPicture":"http://pili-live-snapshot.huataiyihe.com/shidonghui/LIVE1510038585HVILhA.jpg","upstreamAddress":"rtmp://pili-publish.huataiyihe.com/shidonghui/LIVE1510038585HVILhA?e=1515222673&token=OWEQSCxC5KOGAjf1yyYazVmIi0WaS_W4euEfY-X9:U4utxV35UyjghAftaFXhmsjgu9s=","hlsDownAddress":"http://pili-live-hls.huataiyihe.com/shidonghui/LIVE1510038585HVILhA.m3u8","liveTitle":"654","isScreenVertical":0,"avatar":"http://cdn.huataiyihe.com/avatar1505814418160.jpg","liveId":"LIVE1510038585HVILhA","userId":"5320502587U","realname":"测试左广","playerNum":0,"livePicture":"987","flvDownAddress":"http://pili-live-hdl.huataiyihe.com/shidonghui/LIVE1510038585HVILhA.flv","status":"PUSHING","chatroomId":"xWqnU43163Q"}
     */

    private String errMsg;
    private DataBean data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * masterTitles : 321
         * rtmpDownAddress : rtmp://pili-live-rtmp.huataiyihe.com/shidonghui/LIVE1510038585HVILhA
         * livingPicture : http://pili-live-snapshot.huataiyihe.com/shidonghui/LIVE1510038585HVILhA.jpg
         * upstreamAddress : rtmp://pili-publish.huataiyihe.com/shidonghui/LIVE1510038585HVILhA?e=1515222673&token=OWEQSCxC5KOGAjf1yyYazVmIi0WaS_W4euEfY-X9:U4utxV35UyjghAftaFXhmsjgu9s=
         * hlsDownAddress : http://pili-live-hls.huataiyihe.com/shidonghui/LIVE1510038585HVILhA.m3u8
         * liveTitle : 654
         * isScreenVertical : 0
         * avatar : http://cdn.huataiyihe.com/avatar1505814418160.jpg
         * liveId : LIVE1510038585HVILhA
         * userId : 5320502587U
         * realname : 测试左广
         * playerNum : 0
         * livePicture : 987
         * flvDownAddress : http://pili-live-hdl.huataiyihe.com/shidonghui/LIVE1510038585HVILhA.flv
         * status : PUSHING
         * chatroomId : xWqnU43163Q
         */

        private String masterTitles;
        private String rtmpDownAddress;
        private String livingPicture;
        private String upstreamAddress;
        private String hlsDownAddress;
        private String liveTitle;
        private int isScreenVertical;
        private String avatar;
        private String liveId;
        private String userId;
        private String realname;
        private int playerNum;
        private String livePicture;
        private String flvDownAddress;
        private String status;
        private String chatroomId;

        protected DataBean(Parcel in) {
            masterTitles = in.readString();
            rtmpDownAddress = in.readString();
            livingPicture = in.readString();
            upstreamAddress = in.readString();
            hlsDownAddress = in.readString();
            liveTitle = in.readString();
            isScreenVertical = in.readInt();
            avatar = in.readString();
            liveId = in.readString();
            userId = in.readString();
            realname = in.readString();
            playerNum = in.readInt();
            livePicture = in.readString();
            flvDownAddress = in.readString();
            status = in.readString();
            chatroomId = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(masterTitles);
            dest.writeString(rtmpDownAddress);
            dest.writeString(livingPicture);
            dest.writeString(upstreamAddress);
            dest.writeString(hlsDownAddress);
            dest.writeString(liveTitle);
            dest.writeInt(isScreenVertical);
            dest.writeString(avatar);
            dest.writeString(liveId);
            dest.writeString(userId);
            dest.writeString(realname);
            dest.writeInt(playerNum);
            dest.writeString(livePicture);
            dest.writeString(flvDownAddress);
            dest.writeString(status);
            dest.writeString(chatroomId);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getMasterTitles() {
            return masterTitles;
        }

        public void setMasterTitles(String masterTitles) {
            this.masterTitles = masterTitles;
        }

        public String getRtmpDownAddress() {
            return rtmpDownAddress;
        }

        public void setRtmpDownAddress(String rtmpDownAddress) {
            this.rtmpDownAddress = rtmpDownAddress;
        }

        public String getLivingPicture() {
            return livingPicture;
        }

        public void setLivingPicture(String livingPicture) {
            this.livingPicture = livingPicture;
        }

        public String getUpstreamAddress() {
            return upstreamAddress;
        }

        public void setUpstreamAddress(String upstreamAddress) {
            this.upstreamAddress = upstreamAddress;
        }

        public String getHlsDownAddress() {
            return hlsDownAddress;
        }

        public void setHlsDownAddress(String hlsDownAddress) {
            this.hlsDownAddress = hlsDownAddress;
        }

        public String getLiveTitle() {
            return liveTitle;
        }

        public void setLiveTitle(String liveTitle) {
            this.liveTitle = liveTitle;
        }

        public int getIsScreenVertical() {
            return isScreenVertical;
        }

        public void setIsScreenVertical(int isScreenVertical) {
            this.isScreenVertical = isScreenVertical;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getPlayerNum() {
            return playerNum;
        }

        public void setPlayerNum(int playerNum) {
            this.playerNum = playerNum;
        }

        public String getLivePicture() {
            return livePicture;
        }

        public void setLivePicture(String livePicture) {
            this.livePicture = livePicture;
        }

        public String getFlvDownAddress() {
            return flvDownAddress;
        }

        public void setFlvDownAddress(String flvDownAddress) {
            this.flvDownAddress = flvDownAddress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getChatroomId() {
            return chatroomId;
        }

        public void setChatroomId(String chatroomId) {
            this.chatroomId = chatroomId;
        }
    }
}
