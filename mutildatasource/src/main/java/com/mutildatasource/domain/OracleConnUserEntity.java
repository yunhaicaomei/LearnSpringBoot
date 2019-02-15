package com.mutildatasource.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class OracleConnUserEntity implements Serializable {
    private static final long serialVersionUID = -6556793741331167103L;

    private String user_id;
    private String user_nm;
    private String user_pw;
    private Date pw_mod_dt;
    private Date act_dt;
    private Date inact_dt;
    private boolean inv_flg;
    private String ins_usr_cd;
    private Calendar ins_ts;
    private int upd_cntr;
    private String upd_usr_cd;
    private Calendar upd_ts;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_nm() {
        return user_nm;
    }

    public void setUser_nm(String user_nm) {
        this.user_nm = user_nm;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public Date getPw_mod_dt() {
        return pw_mod_dt;
    }

    public void setPw_mod_dt(Date pw_mod_dt) {
        this.pw_mod_dt = pw_mod_dt;
    }

    public Date getAct_dt() {
        return act_dt;
    }

    public void setAct_dt(Date act_dt) {
        this.act_dt = act_dt;
    }

    public Date getInact_dt() {
        return inact_dt;
    }

    public void setInact_dt(Date inact_dt) {
        this.inact_dt = inact_dt;
    }

    public boolean isInv_flg() {
        return inv_flg;
    }

    public void setInv_flg(boolean inv_flg) {
        this.inv_flg = inv_flg;
    }

    public String getIns_usr_cd() {
        return ins_usr_cd;
    }

    public void setIns_usr_cd(String ins_usr_cd) {
        this.ins_usr_cd = ins_usr_cd;
    }

    public Calendar getIns_ts() {
        return ins_ts;
    }

    public void setIns_ts(Calendar ins_ts) {
        this.ins_ts = ins_ts;
    }

    public int getUpd_cntr() {
        return upd_cntr;
    }

    public void setUpd_cntr(int upd_cntr) {
        this.upd_cntr = upd_cntr;
    }

    public String getUpd_usr_cd() {
        return upd_usr_cd;
    }

    public void setUpd_usr_cd(String upd_usr_cd) {
        this.upd_usr_cd = upd_usr_cd;
    }

    public Calendar getUpd_ts() {
        return upd_ts;
    }

    public void setUpd_ts(Calendar upd_ts) {
        this.upd_ts = upd_ts;
    }
}
