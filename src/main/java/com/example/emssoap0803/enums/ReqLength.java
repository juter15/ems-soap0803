package com.example.emssoap0803.enums;

public enum ReqLength {
    Len(4),
    SigId(4),
    Flag(8),
    DestPrcs(8),
    SrcPrcs(8),
    ReqId(36),
    Tid(36),
    Command(256);

    private final int length;

    ReqLength(int length) {
        this.length = length;
    }
    public int getLength(){
        return this.length;
    }
}
