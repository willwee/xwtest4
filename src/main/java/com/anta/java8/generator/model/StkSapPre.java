package com.anta.java8.generator.model;

import java.util.Date;

public class StkSapPre {
    private Long id;

    private Byte type;

    private String iflag;

    private String sapbillno;

    private Byte status;

    private Byte trycount;

    private Date createtime;

    private Date lastchanged;

    private String requrl;

    private String remark;

    private String reqdata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getIflag() {
        return iflag;
    }

    public void setIflag(String iflag) {
        this.iflag = iflag == null ? null : iflag.trim();
    }

    public String getSapbillno() {
        return sapbillno;
    }

    public void setSapbillno(String sapbillno) {
        this.sapbillno = sapbillno == null ? null : sapbillno.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getTrycount() {
        return trycount;
    }

    public void setTrycount(Byte trycount) {
        this.trycount = trycount;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastchanged() {
        return lastchanged;
    }

    public void setLastchanged(Date lastchanged) {
        this.lastchanged = lastchanged;
    }

    public String getRequrl() {
        return requrl;
    }

    public void setRequrl(String requrl) {
        this.requrl = requrl == null ? null : requrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getReqdata() {
        return reqdata;
    }

    public void setReqdata(String reqdata) {
        this.reqdata = reqdata == null ? null : reqdata.trim();
    }
}