package com.example.emssoap0803.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="AGW_CONF_T")
public class AgwConfT {
    @Id
    @Column(name="AGW_ID")
    private String agwId;

    @Column(name="GROUP_ID_L1")
    private String groupIdL1;

    @Column(name="GROUP_ID_L2")
    private String groupIdL2;

    @Column(name="GROUP_ID_L3")
    private String groupIdL3;

    @Column(name="GROUP_ID_L4")
    private String groupIdL4;

    @Column(name="MASTER_TID")
    private String masterTid;

    @Column(name="AREA_ID")
    private String areaId;

    @Column(name="AGW_TYPE")
    private Integer agwType;

    @Column(name="AGW_NAME")
    private String agwName;

    @Column(name="TID")
    private String tid;

    @Column(name="EMS1_IP")
    private String ems1Ip;

    @Column(name="EMS1_MASKBITS", columnDefinition = "TINYINT")
    private Integer ems1Maskbits;

    @Column(name="EMS2_IP")
    private String ems2Ip;

    @Column(name="EMS2_MASKBITS", columnDefinition = "TINYINT")
    private Integer ems2Maskbits;

    @Column(name="DNS1_IP")
    private String dns1Ip;

    @Column(name="DNS2_IP")
    private String dns2Ip;

    @Column(name="TIME_SYNC")
    private String timeSync;

    @Column(name="MIH_IP")
    private String mihIp;

    @Column(name="MIH_MASKBITS", columnDefinition = "TINYINT")
    private Integer mihMaskbits;

    @Column(name="MIH_GW")
    private String mihGw;

    @Column(name="MANUFACTURER")
    private String manufacturer;

    @Column(name="CONF_VERSION")
    private Integer confVersion;

    @Column(name="MG_ID")
    private Integer mgId;

    @Column(name="START_LLN")
    private Integer startLln;

    @Column(name="NODE_NO", columnDefinition = "TINYINT")
    private Integer nodeNo;

    @Column(name="IS_ROUTER", columnDefinition = "TINYINT")
    private Integer isRouter;

    @Column(name="MGCIP")
    private String mgcip;

    @Column(name="MGCPORT", columnDefinition = "SMALLINT")
    private Integer mgcport;

    @Column(name="MEGACOTOS")
    private Integer megacotos;

    @Column(name="LOG_SAVE", columnDefinition = "TINYINT")
    private Integer logSave;

    @Column(name="MGPORT", columnDefinition = "SMALLINT")
    private Integer mgport;

    @Column(name="MGMASKBITS", columnDefinition = "TINYINT")
    private Integer mgmaskbits;

    @Column(name="POTS_INFO")
    private String potsInfo;

    @Column(name="MG1IP")
    private String mg1ip;

    @Column(name="MG2IP")
    private String mg2ip;

    @Column(name="MG3IP")
    private String mg3ip;

    @Column(name="MG4IP")
    private String mg4ip;

    @Column(name="MG5IP")
    private String mg5ip;

    @Column(name="MG6IP")
    private String mg6ip;

    @Column(name="MG7IP")
    private String mg7ip;

    @Column(name="MG8IP")
    private String mg8ip;

    @Column(name="RTPPORT", columnDefinition = "SMALLINT")
    private Integer rtpport;

    @Column(name="RTPMASKBITS", columnDefinition = "TINYINT")
    private Integer rtpmaskbits;

    @Column(name="RTP1IP")
    private String rtp1ip;

    @Column(name="RTP2IP")
    private String rtp2ip;

    @Column(name="RTP3IP")
    private String rtp3ip;

    @Column(name="RTP4IP")
    private String rtp4ip;

    @Column(name="RTP5IP")
    private String rtp5ip;

    @Column(name="RTP6IP")
    private String rtp6ip;

    @Column(name="RTP7IP")
    private String rtp7ip;

    @Column(name="RTP8IP")
    private String rtp8ip;

    @Column(name="LINK0_ENABLE", columnDefinition = "TINYINT")
    private Integer link0Enable;

    @Column(name="LINK0IP")
    private String link0ip;

    @Column(name="LINK0GW")
    private String link0gw;

    @Column(name="LINK0MASKBITS", columnDefinition = "TINYINT")
    private Integer link0maskbits;

    @Column(name="LINK0_DLINE_NO")
    private String link0DlineNo;

    @Column(name="LINK1_ENABLE", columnDefinition = "TINYINT")
    private Integer link1Enable;

    @Column(name="LINK1IP")
    private String link1ip;

    @Column(name="LINK1GW")
    private String link1gw;

    @Column(name="LINK1MASKBITS", columnDefinition = "TINYINT")
    private Integer link1maskbits;

    @Column(name="LINK1_DLINE_NO")
    private String link1DlineNo;

    @Column(name="L3_SWITCH_INFO")
    private String l3SwitchInfo;

    @Column(name="HBT_TIMER")
    private Integer hbtTimer;

    @Column(name="RETURN_TIMER")
    private Integer returnTimer;

    @Column(name="THRESHOLD")
    private Integer threshold;

    @Column(name="T1_TIMER")
    private Integer t1Timer;

    @Column(name="T2_TIMER")
    private Integer t2Timer;

    @Column(name="UPLINK_ENABLE", columnDefinition = "TINYINT")
    private Integer uplinkEnable;

    @Column(name="SERVICE_ENABLE", columnDefinition = "TINYINT")
    private Integer serviceEnable;

    @Column(name="RTPCODEC")
    private String rtpcodec;

    @Column(name="RTPTOS")
    private Integer rtptos;

    @Column(name="FAX", columnDefinition = "TINYINT")
    private Integer fax;

    @Column(name="MODEM", columnDefinition = "TINYINT")
    private Integer modem;

    @Column(name="FAXCONF")
    private String faxconf;

    @Column(name="ANN", columnDefinition = "TINYINT")
    private Integer ann;

    @Column(name="RFC2833EN", columnDefinition = "TINYINT")
    private Integer rfc2833en;

    @Column(name="JITTER_BUFFER", columnDefinition = "TINYINT")
    private Integer jitterBuffer;

    @Column(name="VAD", columnDefinition = "TINYINT")
    private Integer vad;

    @Column(name="JITTER")
    private String jitter;

    @Column(name="EC", columnDefinition = "TINYINT")
    private Integer ec;

    @Column(name="JITTER_VAL")
    private Integer jitterVal;

    @Column(name="PLC", columnDefinition = "TINYINT")
    private Integer plc;

    @Column(name="MONITOR_ENABLE", columnDefinition = "TINYINT")
    private Integer monitorEnable;

    @Column(name="CODEC_CRITICAL", columnDefinition = "TINYINT")
    private Integer codecCritical;

    @Column(name="CODEC_MAJOR", columnDefinition = "TINYINT")
    private Integer codecMajor;

    @Column(name="CODEC_MINOR", columnDefinition = "TINYINT")
    private Integer codecMinor;

    @Column(name="CPU_CRITICAL", columnDefinition = "TINYINT")
    private Integer cpuCritical;

    @Column(name="CPU_MAJOR", columnDefinition = "TINYINT")
    private Integer cpuMajor;

    @Column(name="CPU_MINOR", columnDefinition = "TINYINT")
    private Integer cpuMinor;

    @Column(name="MEM_CRITICAL", columnDefinition = "TINYINT")
    private Integer memCritical;

    @Column(name="MEM_MAJOR", columnDefinition = "TINYINT")
    private Integer memMajor;

    @Column(name="MEM_MINOR", columnDefinition = "TINYINT")
    private Integer memMinor;

    @Column(name="RING_CRITICAL", columnDefinition = "TINYINT")
    private Integer ringCritical;

    @Column(name="RING_MAJOR", columnDefinition = "TINYINT")
    private Integer ringMajor;

    @Column(name="RING_MINOR", columnDefinition = "TINYINT")
    private Integer ringMinor;

    @Column(name="CPU_DIAG_REBOOT", columnDefinition = "TINYINT")
    private Integer cpuDiagReboot;

    @Column(name="LACP_ENABLE", columnDefinition = "TINYINT")
    private Integer lacpEnable;

    @Column(name="L3_ENABLE", columnDefinition = "TINYINT")
    private Integer l3Enable;

    @Column(name="BS_SELF_TEST", columnDefinition = "TINYINT")
    private Integer bsSelfTest;

    @Column(name="ENV_MONITOR_ENABLE", columnDefinition = "TINYINT")
    private Integer envMonitorEnable;

    @Column(name="ENV_MONITOR_PORT")
    private String envMonitorPort;

    @Column(name="MIRROR_PORT")
    private String mirrorPort;

    @Column(name="MIRROR_MODE", columnDefinition = "TINYINT")
    private Integer mirrorMode;

    @Column(name="LLOLIMIT", columnDefinition = "SMALLINT")
    private Integer llolimit;

    @Column(name="LLOLIMIT_PER_PORT", columnDefinition = "SMALLINT")
    private Integer llolimitPerPort;

    @Column(name="LLOLIMIT_PER_SLOT", columnDefinition = "SMALLINT")
    private Integer llolimitPerSlot;

    @Column(name="HW_VERSION")
    private String hwVersion;

    @Column(name="SW_VERSION")
    private String swVersion;

    @Column(name="SW_IMAGE")
    private String swImage;

    @Column(name="KERNEL_VERSION")
    private String kernelVersion;

    @Column(name="KERNEL_IMAGE")
    private String kernelImage;

    @Column(name="SORT_ORDER")
    private Integer sortOrder;

    @Column(name="CREATE_TIME")
    private Timestamp createTime;
    
    @Column(name="UPDATE_TIME")
    private Timestamp updateTime;
}
