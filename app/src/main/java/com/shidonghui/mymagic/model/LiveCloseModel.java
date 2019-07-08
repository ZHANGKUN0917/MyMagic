package com.shidonghui.mymagic.model;

/**
 * @author ZhangKun
 * @create 2019/6/5
 * @Describe
 */
public class LiveCloseModel extends ResponseBaseModel {

    /**
     * errMsg :
     * data : {"masterTitles":"超凡大师","favoriteId":0,"isFavor":0,"liveTitle":"蜡毛小新","avatar":"","liveId":"LIVE1559719277afSXUA","userId":"7011727918U","realname":"师董会测试导师11师资管理","playerNum":0,"likeNum":0,"favoriteNum":4,"number":0,"livePicture":"","canPlayGiftAmount":0,"liveTime":"00:00:39","startTime":"1559719277695","endTime":"1559719317605","status":"FINISH"}
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

    public static class DataBean {
        /**
         * masterTitles : 超凡大师
         * favoriteId : 0
         * isFavor : 0
         * liveTitle : 蜡毛小新
         * avatar :
         * liveId : LIVE1559719277afSXUA
         * userId : 7011727918U
         * realname : 师董会测试导师11师资管理
         * playerNum : 0
         * likeNum : 0
         * favoriteNum : 4
         * number : 0
         * livePicture :
         * canPlayGiftAmount : 0
         * liveTime : 00:00:39
         * startTime : 1559719277695
         * endTime : 1559719317605
         * status : FINISH
         */

        private String masterTitles;
        private int favoriteId;
        private int isFavor;
        private String liveTitle;
        private String avatar;
        private String liveId;
        private String userId;
        private String realname;
        private int playerNum;
        private int likeNum;
        private int favoriteNum;
        private int number;
        private String livePicture;
        private int canPlayGiftAmount;
        private String liveTime;
        private String startTime;
        private String endTime;
        private String status;

        public String getMasterTitles() {
            return masterTitles;
        }

        public void setMasterTitles(String masterTitles) {
            this.masterTitles = masterTitles;
        }

        public int getFavoriteId() {
            return favoriteId;
        }

        public void setFavoriteId(int favoriteId) {
            this.favoriteId = favoriteId;
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

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(int favoriteNum) {
            this.favoriteNum = favoriteNum;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getLivePicture() {
            return livePicture;
        }

        public void setLivePicture(String livePicture) {
            this.livePicture = livePicture;
        }

        public int getCanPlayGiftAmount() {
            return canPlayGiftAmount;
        }

        public void setCanPlayGiftAmount(int canPlayGiftAmount) {
            this.canPlayGiftAmount = canPlayGiftAmount;
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

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
