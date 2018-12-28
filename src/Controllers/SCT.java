package Controllers;

import java.awt.*;
import java.util.Observable;

public class SCT extends Observable {
    private String sid,cid,cname,sname,tid,tname,nb_credits;
    private TextField grades =null;
private String xid,xlabel,xdate,mark_on,mark;//sid,sname,cid,cname
    public SCT(String sid, String cid, String cname, String sname, String tid, String tname, String nb_credits) {
        this.sid = sid;
        this.cid = cid;
        this.cname = cname;
        this.sname = sname;
        this.tid = tid;
        this.tname = tname;
        this.nb_credits = nb_credits;
    }

    public SCT(String sid, String cid, String cname, String sname, String tid, String xid, String xlabel, String xdate) {
        this.sid = sid;
        this.cid = cid;
        this.cname = cname;
        this.sname = sname;
        this.tid = tid;
        this.xid = xid;
        this.xlabel = xlabel;
        this.xdate = xdate;
    }

    //tid,sid,sname,cid,cname,xid,xlabel,xdate
    public SCT(String sid, String cid, String cname, String sname, String nb_credits, String xid, String xlabel, String xdate, String mark_on, String mark) {
        this.sid = sid;
        this.cid = cid;
        this.cname = cname;
        this.sname = sname;
        this.nb_credits = nb_credits;
        this.xid = xid;
        this.xlabel = xlabel;
        this.xdate = xdate;
        this.mark_on = mark_on;
        this.mark = mark;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getXlabel() {
        return xlabel;
    }

    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;
    }

    public String getXdate() {
        return xdate;
    }

    public void setXdate(String xdate) {
        this.xdate = xdate;
    }

    public String getMark_on() {
        return mark_on;
    }

    public void setMark_on(String mark_on) {
        this.mark_on = mark_on;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getNb_credits() {
        return nb_credits;
    }
    public TextField getGrades(){
        return grades;
    }
    public void setNb_credits(String nb_credits) {
        this.nb_credits = nb_credits;
    }
}
