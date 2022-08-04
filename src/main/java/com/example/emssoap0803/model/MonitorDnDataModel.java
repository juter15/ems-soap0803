package com.example.emssoap0803.model;

import lombok.Data;

@Data
public class MonitorDnDataModel {
    private String trsSvrIp;
    private String trsSvrPor;
    private String reqId;
    private int flln;
    private int lln;
}
