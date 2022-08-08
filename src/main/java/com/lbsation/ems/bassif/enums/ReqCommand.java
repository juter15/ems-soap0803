package com.lbsation.ems.bassif.enums;

public enum ReqCommand {
    checkLine("INTEST 0 0", "%s %s %s %s"),
    monitorDN("VQMON", "%s %s %s %s %s %s"),

    verifyDN("CALLG", "%s %s %s %s %s");
    private String command;
    private String format;

    ReqCommand(String commandStr, String format) {
        this.command = commandStr;
        this.format = format;
    }
    public String getCommand(){
        return this.command;
    }

    public String getFormat(){
        return this.format;
    }
}
