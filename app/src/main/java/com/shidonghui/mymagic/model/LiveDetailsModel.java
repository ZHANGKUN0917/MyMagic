package com.shidonghui.mymagic.model;

import java.util.List;

/**
 * @author ZhangKun
 * @create 2019/6/19
 * @Describe
 */
public class LiveDetailsModel {

    /**
     * msg :
     * errMsg :
     * rstCde : 0
     * data : {"favoriteId":0,"replaceTime":"1202749260000","upstreamAddress":"","isFavor":0,"liveTitle":"测试","isScreenVertical":0,"del":0,"mp4DownAddress":"","playerNum":71,"likeNum":30,"isThrowRobot":1,"startTime":"1557128100000","flvDownAddress":"","chatroomId":"cSZgJ62785Q","masterTitles":"测试18181","liveAdministrator":"0464289547U,5320502587U,1462540926686579013","rtmpDownAddress":"rtmp://r2.weizan.cn/v/451441_132017565659821404","isShutUp":"0","hlsDownAddress":"","avatar":"http://cdn.sdh365.com/avatar1541205326640.jpg","liveId":"LIVE1557128136NnRvqA","userId":"99999999131231isfs9d","realname":"测试师董会","livePicture":"http://cdn.sdh365.com/newOam_livePicture_1557128094369.png","m3u8DownAddress":"","items":[{"portraitUri":"","name":"师董会测试导师11师资管理","userId":"7011727918U"},{"portraitUri":"http://cdn.shidhui.com/user/avatar/1542289000171_9548_tiny-441-2018-11-15-21-36-40.jpg","name":"韵味短1","userId":"2535105417U"}],"status":"STARTED"}
     */

    private String msg;
    private String errMsg;
    private int rstCde;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getRstCde() {
        return rstCde;
    }

    public void setRstCde(int rstCde) {
        this.rstCde = rstCde;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * favoriteId : 0
         * replaceTime : 1202749260000
         * upstreamAddress :
         * isFavor : 0
         * liveTitle : 测试
         * isScreenVertical : 0
         * del : 0
         * mp4DownAddress :
         * playerNum : 71
         * likeNum : 30
         * isThrowRobot : 1
         * startTime : 1557128100000
         * flvDownAddress :
         * chatroomId : cSZgJ62785Q
         * masterTitles : 测试18181
         * liveAdministrator : 0464289547U,5320502587U,1462540926686579013
         * rtmpDownAddress : rtmp://r2.weizan.cn/v/451441_132017565659821404
         * isShutUp : 0
         * hlsDownAddress :
         * avatar : http://cdn.sdh365.com/avatar1541205326640.jpg
         * liveId : LIVE1557128136NnRvqA
         * userId : 99999999131231isfs9d
         * realname : 测试师董会
         * livePicture : http://cdn.sdh365.com/newOam_livePicture_1557128094369.png
         * m3u8DownAddress :
         * items : [{"portraitUri":"","name":"师董会测试导师11师资管理","userId":"7011727918U"},{"portraitUri":"http://cdn.shidhui.com/user/avatar/1542289000171_9548_tiny-441-2018-11-15-21-36-40.jpg","name":"韵味短1","userId":"2535105417U"}]
         * status : STARTED
         */

        private int favoriteId;
        private String replaceTime;
        private String upstreamAddress;
        private int isFavor;
        private String liveTitle;
        private int isScreenVertical;
        private int del;
        private String mp4DownAddress;
        private int playerNum;
        private int likeNum;
        private int isThrowRobot;
        private String startTime;
        private String flvDownAddress;
        private String chatroomId;
        private String masterTitles;
        private String liveAdministrator;
        private String rtmpDownAddress;
        private String isShutUp;
        private String hlsDownAddress;
        private String avatar;
        private String liveId;
        private String userId;
        private String realname;
        private String livePicture;
        private String m3u8DownAddress;
        private String status;
        private List<ItemsBean> items;

        public int getFavoriteId() {
            return favoriteId;
        }

        public void setFavoriteId(int favoriteId) {
            this.favoriteId = favoriteId;
        }

        public String getReplaceTime() {
            return replaceTime;
        }

        public void setReplaceTime(String replaceTime) {
            this.replaceTime = replaceTime;
        }

        public String getUpstreamAddress() {
            return upstreamAddress;
        }

        public void setUpstreamAddress(String upstreamAddress) {
            this.upstreamAddress = upstreamAddress;
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

        public int getIsScreenVertical() {
            return isScreenVertical;
        }

        public void setIsScreenVertical(int isScreenVertical) {
            this.isScreenVertical = isScreenVertical;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public String getMp4DownAddress() {
            return mp4DownAddress;
        }

        public void setMp4DownAddress(String mp4DownAddress) {
            this.mp4DownAddress = mp4DownAddress;
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

        public int getIsThrowRobot() {
            return isThrowRobot;
        }

        public void setIsThrowRobot(int isThrowRobot) {
            this.isThrowRobot = isThrowRobot;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getFlvDownAddress() {
            return flvDownAddress;
        }

        public void setFlvDownAddress(String flvDownAddress) {
            this.flvDownAddress = flvDownAddress;
        }

        public String getChatroomId() {
            return chatroomId;
        }

        public void setChatroomId(String chatroomId) {
            this.chatroomId = chatroomId;
        }

        public String getMasterTitles() {
            return masterTitles;
        }

        public void setMasterTitles(String masterTitles) {
            this.masterTitles = masterTitles;
        }

        public String getLiveAdministrator() {
            return liveAdministrator;
        }

        public void setLiveAdministrator(String liveAdministrator) {
            this.liveAdministrator = liveAdministrator;
        }

        public String getRtmpDownAddress() {
            return rtmpDownAddress;
        }

        public void setRtmpDownAddress(String rtmpDownAddress) {
            this.rtmpDownAddress = rtmpDownAddress;
        }

        public String getIsShutUp() {
            return isShutUp;
        }

        public void setIsShutUp(String isShutUp) {
            this.isShutUp = isShutUp;
        }

        public String getHlsDownAddress() {
            return hlsDownAddress;
        }

        public void setHlsDownAddress(String hlsDownAddress) {
            this.hlsDownAddress = hlsDownAddress;
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

        public String getLivePicture() {
            return livePicture;
        }

        public void setLivePicture(String livePicture) {
            this.livePicture = livePicture;
        }

        public String getM3u8DownAddress() {
            return m3u8DownAddress;
        }

        public void setM3u8DownAddress(String m3u8DownAddress) {
            this.m3u8DownAddress = m3u8DownAddress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * portraitUri :
             * name : 师董会测试导师11师资管理
             * userId : 7011727918U
             */

            private String portraitUri;
            private String name;
            private String userId;

            public ItemsBean(String portraitUri, String name, String userId) {
                this.portraitUri = portraitUri;
                this.name = name;
                this.userId = userId;
            }

            public String getPortraitUri() {
                return portraitUri;
            }

            public void setPortraitUri(String portraitUri) {
                this.portraitUri = portraitUri;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
