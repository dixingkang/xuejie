package com.dafen.xuejie.bean;

/**
 * Created by 94239 on 2016/12/26.
 */

public class RegisterBean {


    /**
     * status : success
     * explain : 注册成功
     * value : {"id":"16","user_login":"1833694131","user_pass":"###dad747c18126d88d65dec18bbf7dba1e","user_nicename":"1833694131","user_email":"","user_url":"","avatar":null,"sex":"0","birthday":"2000-01-01","signature":null,"last_login_ip":"1.192.80.88","last_login_time":"2000-01-01 00:00:00","create_time":"2016-12-26 17:25:44","user_activation_key":"","user_status":"1","score":"0","user_type":"2","coin":"0","mobile":"1833694131","qq":null,"weixin":null,"agencyid":null,"account":"","openid":"0","source":"0"}
     */

    private String status;
    private String explain;
    private ValueBean value;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public ValueBean getValue() {
        return value;
    }

    public void setValue(ValueBean value) {
        this.value = value;
    }

    public static class ValueBean {
        /**
         * id : 16
         * user_login : 1833694131
         * user_pass : ###dad747c18126d88d65dec18bbf7dba1e
         * user_nicename : 1833694131
         * user_email :
         * user_url :
         * avatar : null
         * sex : 0
         * birthday : 2000-01-01
         * signature : null
         * last_login_ip : 1.192.80.88
         * last_login_time : 2000-01-01 00:00:00
         * create_time : 2016-12-26 17:25:44
         * user_activation_key :
         * user_status : 1
         * score : 0
         * user_type : 2
         * coin : 0
         * mobile : 1833694131
         * qq : null
         * weixin : null
         * agencyid : null
         * account :
         * openid : 0
         * source : 0
         */

        private String id;
        private String user_login;
        private String user_pass;
        private String user_nicename;
        private String user_email;
        private String user_url;
        private Object avatar;
        private String sex;
        private String birthday;
        private Object signature;
        private String last_login_ip;
        private String last_login_time;
        private String create_time;
        private String user_activation_key;
        private String user_status;
        private String score;
        private String user_type;
        private String coin;
        private String mobile;
        private Object qq;
        private Object weixin;
        private Object agencyid;
        private String account;
        private String openid;
        private String source;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public String getUser_pass() {
            return user_pass;
        }

        public void setUser_pass(String user_pass) {
            this.user_pass = user_pass;
        }

        public String getUser_nicename() {
            return user_nicename;
        }

        public void setUser_nicename(String user_nicename) {
            this.user_nicename = user_nicename;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_url() {
            return user_url;
        }

        public void setUser_url(String user_url) {
            this.user_url = user_url;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public Object getSignature() {
            return signature;
        }

        public void setSignature(Object signature) {
            this.signature = signature;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUser_activation_key() {
            return user_activation_key;
        }

        public void setUser_activation_key(String user_activation_key) {
            this.user_activation_key = user_activation_key;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
            this.weixin = weixin;
        }

        public Object getAgencyid() {
            return agencyid;
        }

        public void setAgencyid(Object agencyid) {
            this.agencyid = agencyid;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }
}
