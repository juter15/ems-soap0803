package com.lbsation.ems.bassif.enums;

public enum ResContents {
    checkLine("INTEST_RES ", 55),
    monitorDN("VQMON_RES ", 10),
    verifyDN("CALLG_RES ", 10),
    noti(null, 55);
    private String contents;
    private int timeOut;
    ResContents(String contents, int timeOut) {
        this.contents = contents;
        this.timeOut = timeOut;
    }
    public String getResContents(){
        return this.contents;
    }
    public int getTimeOut(){
        return this.timeOut;
    }
}
