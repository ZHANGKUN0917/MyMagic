package com.shidonghui.mymagic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/6/13
 * @Describe
 */
public class LiveListModel extends ResponseBaseModel  {

    /**
     * errMsg :
     * data : [{"masterTitles":"测试18181","rtmpDownAddress":"rtmp://r2.weizan.cn/v/451441_132017565659821404","livingPicture":"","favoriteId":0,"city":"","ip":"","isFavor":0,"liveTitle":"测试","del":0,"avatar":"http://cdn.sdh365.com/avatar1541205326640.jpg","liveId":"LIVE1557128136NnRvqA","userId":"99999999131231isfs9d","mp4DownAddress":"","realname":"测试师董会","playerNum":52,"likeNum":30,"livePicture":"http://cdn.sdh365.com/newOam_livePicture_1557128094369.png","liveTime":"","startTime":"1557128100000","m3u8DownAddress":"","chatroomId":"cSZgJ62785Q","status":"STARTED"},{"masterTitles":"CCTV3","rtmpDownAddress":"http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8","livingPicture":"http://pili-live-snapshot.huataiyihe.com/shidonghui/LIVE1510560509OSkQVA.jpg","favoriteId":0,"city":"中国华北北京市北京市联通","ip":"111.196.245.190","isFavor":0,"liveTitle":"CCTV3","del":0,"avatar":"http://cdn.sdh365.com/2017-11-08_17_40_09_0233548.jpeg","liveId":"LIVE1510560509OSkQVA","userId":"0464289547U","mp4DownAddress":"http://1251830167.vod2.myqcloud.com/9d5229a1vodgzp1251830167/6cfb79469031868223148543266/AR0pSvrHmawA.mp4","realname":"任成","playerNum":473543,"likeNum":4005,"livePicture":"http://pic.90sjimg.com/design/01/45/61/40/59254d0902042.png","liveTime":"00:46:57","startTime":"1551850716000","m3u8DownAddress":"http://livecdn.huataiyihe.com/recordings/z1.shidonghui.LIVE1511686425KNJsHA/0_1511687706.m3u8","chatroomId":"773173291","status":"STARTED"},{"masterTitles":"CCTV1","rtmpDownAddress":"http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8","livingPicture":"http://pili-live-snapshot.huataiyihe.com/shidonghui/LIVE1510903517pAhnPA.jpg","favoriteId":0,"city":"中国华北北京市北京市联通","ip":"111.196.242.54","isFavor":0,"liveTitle":"CCTV1","del":0,"avatar":"http://cdn.sdh365.com/avatar1505377570051.jpg","liveId":"LIVE1510903517pAhnPA","userId":"0464289547U","mp4DownAddress":"http://livecdn.huataiyihe.com/sdhhuabei_LIVE1510903517pAhnPA.mp4","realname":"任成","playerNum":51497,"likeNum":15,"livePicture":"http://ziti.cndesign.com/upload/font/2009-07-24/b3c9cd96c6c04f2eab3777526c735146.jpg","liveTime":"00:20:34","startTime":"1551850716000","m3u8DownAddress":"http://livecdn.huataiyihe.com/recordings/z1.shidonghui.LIVE1511686425KNJsHA/0_1511687706.m3u8","chatroomId":"XQyzD50149Q","status":"STARTED"},{"masterTitles":"CCTV6-电影","rtmpDownAddress":"http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8","livingPicture":"http://pili-live-snapshot.sdh365.com/shidonghui/LIVE1543204700hBDXvA.jpg","favoriteId":0,"city":"","ip":"","isFavor":0,"liveTitle":"CCTV6-电影","del":0,"avatar":"http://cdn.shidhui.com/user/avatar/201811241558532777597.jpg","liveId":"LIVE1543204700hBDXvA","userId":"9210308111U","mp4DownAddress":"http://livecdn.sdh365.com/sdhhuabei_LIVE1543204700hBDXvA.mp4","realname":"刘博文","playerNum":18188,"likeNum":165,"livePicture":"http://cdn.shidhui.com/live/cover/1543204696219_4617_tiny-185-2018-11-26-11-58-16.jpg","liveTime":"00:04:16","startTime":"1543204700318","m3u8DownAddress":"http://livecdn.sdh365.com/recordings/z1.shidonghui.LIVE1543204700hBDXvA/0_1543204956.m3u8","chatroomId":"ezmRp72162Q","status":"STARTED"},{"masterTitles":"VB吧","rtmpDownAddress":"rtmp://pili-live-rtmp.sdh365.com/shidonghui/LIVE1559630799HAyCWA","livingPicture":"http://pili-live-snapshot.sdh365.com/shidonghui/LIVE1559630799HAyCWA.jpg","favoriteId":0,"city":"","ip":"","isFavor":0,"liveTitle":"富贵花","del":0,"avatar":"","liveId":"LIVE1559630799HAyCWA","userId":"7011727918U","mp4DownAddress":"http://livecdn.sdh365.com/sdhhuabei_LIVE1559630799HAyCWA.mp4","realname":"师董会测试导师11师资管理","playerNum":0,"likeNum":0,"livePicture":"http://cdn.shidhui.com/live/cover/1559630790874_1666_tiny-930-2019-06-04-14-46-30.jpg","liveTime":"00:19:40","startTime":"1559630799246","m3u8DownAddress":"http://livecdn.sdh365.com/recordings/z1.shidonghui.LIVE1559630799HAyCWA/0_1559631980.m3u8","chatroomId":"uPYJE50538Q","status":"FINISH"},{"masterTitles":"分享","rtmpDownAddress":"rtmp://pili-live-rtmp.sdh365.com/shidonghui/LIVE1551173761iKGhyA","livingPicture":"http://pili-live-snapshot.sdh365.com/shidonghui/LIVE1551173761iKGhyA.jpg","favoriteId":0,"city":"","ip":"","isFavor":0,"liveTitle":"分享","del":0,"avatar":"http://cdn.shidhui.com/2018-11-14_15_10_33_2627743.jpg","liveId":"LIVE1551173761iKGhyA","userId":"6938462499U","mp4DownAddress":"http://livecdn.sdh365.com/sdhhuabei_LIVE1551173761iKGhyA.mp4","realname":"测试师董会测试永久会员","playerNum":5,"likeNum":0,"livePicture":"http://cdn.shidhui.com/live/cover/1551173759793_2824_tiny-720-2019-02-26-17-35-59.jpg","liveTime":"00:09:16","startTime":"1551173761493","m3u8DownAddress":"http://livecdn.sdh365.com/recordings/z1.shidonghui.LIVE1551173761iKGhyA/0_1551174317.m3u8","chatroomId":"xouMh29780Q","status":"FINISH"},{"masterTitles":"在一起就是","rtmpDownAddress":"rtmp://pili-live-rtmp.sdh365.com/shidonghui/LIVE1551160334PsZcMA","livingPicture":"","favoriteId":0,"city":"","ip":"","isFavor":0,"liveTitle":"你是怎么","del":0,"avatar":"http://cdn.shidhui.com/user/avatar/201811152117394123943.jpg","liveId":"LIVE1551160334PsZcMA","userId":"2637022576U","mp4DownAddress":"http://livecdn.sdh365.com/sdhhuabei_LIVE1551160334PsZcMA.mp4","realname":"赵阳光","playerNum":0,"likeNum":0,"livePicture":"http://cdn.shidhui.com/2019-02-26_13_52_14_0216698.jpg","liveTime":"00:19:13","startTime":"1551160320000","m3u8DownAddress":"http://livecdn.sdh365.com/recordings/z1.shidonghui.LIVE1551160334PsZcMA/0_1551161473.m3u8","chatroomId":"gYzpF50141Q","status":"FINISH"},{"masterTitles":"你们都","rtmpDownAddress":"rtmp://pili-live-rtmp.sdh365.com/shidonghui/LIVE1548317493LgitzA","livingPicture":"http://pili-live-snapshot.sdh365.com/shidonghui/LIVE1548317493LgitzA.jpg","favoriteId":0,"city":"","ip":"","isFavor":0,"liveTitle":"我们","del":0,"avatar":"http://cdn.shidhui.com/user/avatar/1542289000171_9548_tiny-441-2018-11-15-21-36-40.jpg","liveId":"LIVE1548317493LgitzA","userId":"2535105417U","mp4DownAddress":"","realname":"韵味短","playerNum":41,"likeNum":0,"livePicture":"http://cdn.shidhui.com/2019-02-05_16_11_10_5085374.jpg","liveTime":"","startTime":"1548317494121","m3u8DownAddress":"","chatroomId":"JInPJ60132Q","status":"FINISH"},{"masterTitles":"环境","rtmpDownAddress":"rtmp://pili-live-rtmp.sdh365.com/shidonghui/LIVE1546484844QAlEmA","livingPicture":"http://pili-live-snapshot.sdh365.com/shidonghui/LIVE1546484844QAlEmA.jpg","favoriteId":0,"city":"","ip":"","isFavor":0,"liveTitle":"兔兔","del":0,"avatar":"","liveId":"LIVE1546484844QAlEmA","userId":"7011727918U","mp4DownAddress":"http://livecdn.sdh365.com/sdhhuabei_LIVE1546484844QAlEmA.mp4","realname":"师董会测试导师11","playerNum":250,"likeNum":475,"livePicture":"http://cdn.shidhui.com/live/cover/1546484836498_7592_tiny-108-2019-01-03-11-07-16.jpg","liveTime":"00:05:53","startTime":"1546484844406","m3u8DownAddress":"http://livecdn.sdh365.com/recordings/z1.shidonghui.LIVE1546484844QAlEmA/0_1546485197.m3u8","chatroomId":"CCLJr20858Q","status":"FINISH"},{"masterTitles":"！。。","rtmpDownAddress":"rtmp://pili-live-rtmp.sdh365.com/shidonghui/LIVE1545899082aKAzZA","livingPicture":"http://pili-live-snapshot.sdh365.com/shidonghui/LIVE1545899082aKAzZA.jpg","favoriteId":0,"city":"","ip":"","isFavor":0,"liveTitle":"。！","del":0,"avatar":"http://cdn.shidhui.com/2018-11-14_15_10_33_2627743.jpg","liveId":"LIVE1545899082aKAzZA","userId":"6938462499U","mp4DownAddress":"http://livecdn.sdh365.com/sdhhuabei_LIVE1545899082aKAzZA.mp4","realname":"测试师董会测试永久会员","playerNum":383,"likeNum":5280,"livePicture":"http://cdn.shidhui.com/live/cover/1545899074497_6183_tiny-567-2018-12-27-16-24-34.jpg","liveTime":"00:49:44","startTime":"1545899082726","m3u8DownAddress":"http://livecdn.sdh365.com/recordings/z1.shidonghui.LIVE1545899082aKAzZA/0_1545902067.m3u8","chatroomId":"ZJzKm43287Q","status":"FINISH"},{"masterTitles":"？！","rtmpDownAddress":"rtmp://pili-live-rtmp.sdh365.com/shidonghui/LIVE1545898254sINFMA","livingPicture":"http://pili-live-snapshot.sdh365.com/shidonghui/LIVE1545898254sINFMA.jpg","favoriteId":0,"city":"","ip":"","isFavor":0,"liveTitle":"。！","del":0,"avatar":"http://cdn.shidhui.com/user/avatar/1542289000171_9548_tiny-441-2018-11-15-21-36-40.jpg","liveId":"LIVE1545898254sINFMA","userId":"2535105417U","mp4DownAddress":"http://livecdn.sdh365.com/sdhhuabei_LIVE1545898254sINFMA.mp4","realname":"韵味短","playerNum":0,"likeNum":0,"livePicture":"http://cdn.shidhui.com/live/cover/1545898247928_4549_tiny-620-2018-12-27-16-10-47.jpg","liveTime":"00:11:36","startTime":"1545898254506","m3u8DownAddress":"http://livecdn.sdh365.com/recordings/z1.shidonghui.LIVE1545898254sINFMA/0_1545898951.m3u8","chatroomId":"rVOgq97037Q","status":"FINISH"}]
     */

    private String errMsg;
    private List<DataBean> data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }



    public static class DataBean implements Parcelable {
        /**
         * masterTitles : 测试18181
         * rtmpDownAddress : rtmp://r2.weizan.cn/v/451441_132017565659821404
         * livingPicture :
         * favoriteId : 0
         * city :
         * ip :
         * isFavor : 0
         * liveTitle : 测试
         * del : 0
         * avatar : http://cdn.sdh365.com/avatar1541205326640.jpg
         * liveId : LIVE1557128136NnRvqA
         * userId : 99999999131231isfs9d
         * mp4DownAddress :
         * realname : 测试师董会
         * playerNum : 52
         * likeNum : 30
         * livePicture : http://cdn.sdh365.com/newOam_livePicture_1557128094369.png
         * liveTime :
         * startTime : 1557128100000
         * m3u8DownAddress :
         * chatroomId : cSZgJ62785Q
         * status : STARTED
         */

        private String masterTitles;
        private String rtmpDownAddress;
        private String livingPicture;
        private int favoriteId;
        private String city;
        private String ip;
        private int isFavor;
        private String liveTitle;
        private int del;
        private String avatar;
        private String liveId;
        private String userId;
        private String mp4DownAddress;
        private String realname;
        private int playerNum;
        private int likeNum;
        private String livePicture;
        private String liveTime;
        private String startTime;
        private String m3u8DownAddress;
        private String chatroomId;
        private String status;


        protected DataBean(Parcel in) {
            masterTitles = in.readString();
            rtmpDownAddress = in.readString();
            livingPicture = in.readString();
            favoriteId = in.readInt();
            city = in.readString();
            ip = in.readString();
            isFavor = in.readInt();
            liveTitle = in.readString();
            del = in.readInt();
            avatar = in.readString();
            liveId = in.readString();
            userId = in.readString();
            mp4DownAddress = in.readString();
            realname = in.readString();
            playerNum = in.readInt();
            likeNum = in.readInt();
            livePicture = in.readString();
            liveTime = in.readString();
            startTime = in.readString();
            m3u8DownAddress = in.readString();
            chatroomId = in.readString();
            status = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(masterTitles);
            dest.writeString(rtmpDownAddress);
            dest.writeString(livingPicture);
            dest.writeInt(favoriteId);
            dest.writeString(city);
            dest.writeString(ip);
            dest.writeInt(isFavor);
            dest.writeString(liveTitle);
            dest.writeInt(del);
            dest.writeString(avatar);
            dest.writeString(liveId);
            dest.writeString(userId);
            dest.writeString(mp4DownAddress);
            dest.writeString(realname);
            dest.writeInt(playerNum);
            dest.writeInt(likeNum);
            dest.writeString(livePicture);
            dest.writeString(liveTime);
            dest.writeString(startTime);
            dest.writeString(m3u8DownAddress);
            dest.writeString(chatroomId);
            dest.writeString(status);
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

        public int getFavoriteId() {
            return favoriteId;
        }

        public void setFavoriteId(int favoriteId) {
            this.favoriteId = favoriteId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getIsFavor() {
            return isFavor;
        }

        public void setIsFavor(int isFavor) {
            this.isFavor = isFavor;
        }

        public String getLiveTitle() {
            return liveTitle;
        }

        public void setLiveTitle(String liveTitle) {
            this.liveTitle = liveTitle;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
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

        public String getMp4DownAddress() {
            return mp4DownAddress;
        }

        public void setMp4DownAddress(String mp4DownAddress) {
            this.mp4DownAddress = mp4DownAddress;
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

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public String getLivePicture() {
            return livePicture;
        }

        public void setLivePicture(String livePicture) {
            this.livePicture = livePicture;
        }

        public String getLiveTime() {
            return liveTime;
        }

        public void setLiveTime(String liveTime) {
            this.liveTime = liveTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getM3u8DownAddress() {
            return m3u8DownAddress;
        }

        public void setM3u8DownAddress(String m3u8DownAddress) {
            this.m3u8DownAddress = m3u8DownAddress;
        }

        public String getChatroomId() {
            return chatroomId;
        }

        public void setChatroomId(String chatroomId) {
            this.chatroomId = chatroomId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


    }
}
