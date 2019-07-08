package com.shidonghui.mymagic.model;

/**
 * @author ZhangKun
 * @create 2019/6/11
 * @Describe 用户信息
 */
public class UserModel  {


    /**
     * msg : null
     * rstCde : 0
     * data : {"userId":"7011727918U","balance":5339.44,"cashCouponBalance":2337,"point1":0,"point":0,"cardBagCount":28,"realname":"张堃","idcardno":"1311****1610","titles":null,"avatar":"","avatarThumb":"","vipLevel":"SILVER","type":"PUPIL","nickname":"师董会测试导师11师资管理","msisdn":"18810431910","signature":"111","isFendaExpert":true,"company":"师董会北京","post":"还回家","isSigned":0,"isIdVerify":1,"isAlipayAttestation":0,"exepirence":5,"wxBind":"0","qqBind":"0","areaCode":"ss","shareWxTimelineCount":0,"signedNumber":"0","todaySignedNumber":5,"vipDueTime":"1605336262810","city":"保定","sex":"0","individualResume":"VB那你","rongToken":"nI3aQlYNMKAiuCiKXCSTkceKlmIDjPqt/tjAMngLO6QRuWNhcl1bKcPmG9eJMHYoh0JJhCtobm2jG9+wdaGOH6eSXGRbKRRB","inviteCode":"108532","creatGroupCount":0,"joinGroupCount":2,"friendCount":3,"isExpertCheck":0,"isAudit":1,"isPartner":0}
     */

    private Object msg;
    private int rstCde;
    private DataBean data;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
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
         * userId : 7011727918U
         * balance : 5339.44
         * cashCouponBalance : 2337
         * point1 : 0
         * point : 0
         * cardBagCount : 28
         * realname : 张堃
         * idcardno : 1311****1610
         * titles : null
         * avatar :
         * avatarThumb :
         * vipLevel : SILVER
         * type : PUPIL
         * nickname : 师董会测试导师11师资管理
         * msisdn : 18810431910
         * signature : 111
         * isFendaExpert : true
         * company : 师董会北京
         * post : 还回家
         * isSigned : 0
         * isIdVerify : 1
         * isAlipayAttestation : 0
         * exepirence : 5
         * wxBind : 0
         * qqBind : 0
         * areaCode : ss
         * shareWxTimelineCount : 0
         * signedNumber : 0
         * todaySignedNumber : 5
         * vipDueTime : 1605336262810
         * city : 保定
         * sex : 0
         * individualResume : VB那你
         * rongToken : nI3aQlYNMKAiuCiKXCSTkceKlmIDjPqt/tjAMngLO6QRuWNhcl1bKcPmG9eJMHYoh0JJhCtobm2jG9+wdaGOH6eSXGRbKRRB
         * inviteCode : 108532
         * creatGroupCount : 0
         * joinGroupCount : 2
         * friendCount : 3
         * isExpertCheck : 0
         * isAudit : 1
         * isPartner : 0
         */

        private String userId;
        private double balance;
        private int cashCouponBalance;
        private int point1;
        private int point;
        private int cardBagCount;
        private String realname;
        private String idcardno;
        private Object titles;
        private String avatar;
        private String avatarThumb;
        private String vipLevel;
        private String type;
        private String nickname;
        private String msisdn;
        private String signature;
        private boolean isFendaExpert;
        private String company;
        private String post;
        private int isSigned;
        private int isIdVerify;
        private int isAlipayAttestation;
        private int exepirence;
        private String wxBind;
        private String qqBind;
        private String areaCode;
        private int shareWxTimelineCount;
        private String signedNumber;
        private int todaySignedNumber;
        private String vipDueTime;
        private String city;
        private String sex;
        private String individualResume;
        private String rongToken;
        private String inviteCode;
        private int creatGroupCount;
        private int joinGroupCount;
        private int friendCount;
        private int isExpertCheck;
        private int isAudit;
        private int isPartner;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public int getCashCouponBalance() {
            return cashCouponBalance;
        }

        public void setCashCouponBalance(int cashCouponBalance) {
            this.cashCouponBalance = cashCouponBalance;
        }

        public int getPoint1() {
            return point1;
        }

        public void setPoint1(int point1) {
            this.point1 = point1;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getCardBagCount() {
            return cardBagCount;
        }

        public void setCardBagCount(int cardBagCount) {
            this.cardBagCount = cardBagCount;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getIdcardno() {
            return idcardno;
        }

        public void setIdcardno(String idcardno) {
            this.idcardno = idcardno;
        }

        public Object getTitles() {
            return titles;
        }

        public void setTitles(Object titles) {
            this.titles = titles;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatarThumb() {
            return avatarThumb;
        }

        public void setAvatarThumb(String avatarThumb) {
            this.avatarThumb = avatarThumb;
        }

        public String getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(String vipLevel) {
            this.vipLevel = vipLevel;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public boolean isIsFendaExpert() {
            return isFendaExpert;
        }

        public void setIsFendaExpert(boolean isFendaExpert) {
            this.isFendaExpert = isFendaExpert;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public int getIsSigned() {
            return isSigned;
        }

        public void setIsSigned(int isSigned) {
            this.isSigned = isSigned;
        }

        public int getIsIdVerify() {
            return isIdVerify;
        }

        public void setIsIdVerify(int isIdVerify) {
            this.isIdVerify = isIdVerify;
        }

        public int getIsAlipayAttestation() {
            return isAlipayAttestation;
        }

        public void setIsAlipayAttestation(int isAlipayAttestation) {
            this.isAlipayAttestation = isAlipayAttestation;
        }

        public int getExepirence() {
            return exepirence;
        }

        public void setExepirence(int exepirence) {
            this.exepirence = exepirence;
        }

        public String getWxBind() {
            return wxBind;
        }

        public void setWxBind(String wxBind) {
            this.wxBind = wxBind;
        }

        public String getQqBind() {
            return qqBind;
        }

        public void setQqBind(String qqBind) {
            this.qqBind = qqBind;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public int getShareWxTimelineCount() {
            return shareWxTimelineCount;
        }

        public void setShareWxTimelineCount(int shareWxTimelineCount) {
            this.shareWxTimelineCount = shareWxTimelineCount;
        }

        public String getSignedNumber() {
            return signedNumber;
        }

        public void setSignedNumber(String signedNumber) {
            this.signedNumber = signedNumber;
        }

        public int getTodaySignedNumber() {
            return todaySignedNumber;
        }

        public void setTodaySignedNumber(int todaySignedNumber) {
            this.todaySignedNumber = todaySignedNumber;
        }

        public String getVipDueTime() {
            return vipDueTime;
        }

        public void setVipDueTime(String vipDueTime) {
            this.vipDueTime = vipDueTime;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIndividualResume() {
            return individualResume;
        }

        public void setIndividualResume(String individualResume) {
            this.individualResume = individualResume;
        }

        public String getRongToken() {
            return rongToken;
        }

        public void setRongToken(String rongToken) {
            this.rongToken = rongToken;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public int getCreatGroupCount() {
            return creatGroupCount;
        }

        public void setCreatGroupCount(int creatGroupCount) {
            this.creatGroupCount = creatGroupCount;
        }

        public int getJoinGroupCount() {
            return joinGroupCount;
        }

        public void setJoinGroupCount(int joinGroupCount) {
            this.joinGroupCount = joinGroupCount;
        }

        public int getFriendCount() {
            return friendCount;
        }

        public void setFriendCount(int friendCount) {
            this.friendCount = friendCount;
        }

        public int getIsExpertCheck() {
            return isExpertCheck;
        }

        public void setIsExpertCheck(int isExpertCheck) {
            this.isExpertCheck = isExpertCheck;
        }

        public int getIsAudit() {
            return isAudit;
        }

        public void setIsAudit(int isAudit) {
            this.isAudit = isAudit;
        }

        public int getIsPartner() {
            return isPartner;
        }

        public void setIsPartner(int isPartner) {
            this.isPartner = isPartner;
        }
    }

}

