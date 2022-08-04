package com.example.emssoap0803.service;

import com.example.emssoap0803.entity.QAgwConfT;
import com.example.emssoap0803.enums.ReqCommand;
import com.example.emssoap0803.enums.ReqLength;
import com.example.emssoap0803.enums.ResContents;
import com.example.emssoap0803.enums.Status;
import com.example.emssoap0803.model.MonitorDnDataModel;
import com.example.emssoap0803.util.IpcUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kt.agwems.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class IpcService {
    private final QAgwConfT qAgwConfT = QAgwConfT.agwConfT;
    private final EntityManager entityManager;
    private final RedisService redisService;

    @Value("${ipc.sigid}") //Hexa String(별도 정의)(4)
    String sigid;
    @Value("${ipc.flag}") //Hexa String(별도 정의)(8)
    String flag;
    @Value("${ipc.destPrcs}") //Hexa String(별도 정의)(8)
    String destPrcs;
    @Value("${ipc.srcPrcs}") //Hexa String(별도 정의)(8)
    String srcPrcs;

    public VerifyDNResVo verifyDNResponse(VerifyDNReqVo verifyDNReq){
        VerifyDNResVo verifyReturn = new VerifyDNResVo();
        verifyReturn.setAfi(agwFacilityInfoSet(verifyDNReq.getCrv().getCmi()));
        //Command Setting || CALLG CH ACT|DEACT tryDn
        String command = String.format(ReqCommand.verifyDN.getFormat()
                , ReqCommand.verifyDN.getCommand()
                , IpcUtil.getCh(verifyDNReq.getCrv().getCmi().getFlln(), verifyDNReq.getCrv().getCmi().getLln())
                , Status.ACT.name()
                , verifyDNReq.getCrv().getReqId()
                , verifyDNReq.getTryDn()
        );

        log.info("### verifyDN command: {}", command);
        //DB에서 장비 TID 조회
        String tId = getTid(verifyDNReq.getCrv().getCmi().getFlln(), verifyDNReq.getCrv().getCmi().getColcode(), verifyDNReq.getCrv().getCmi().getAgwIp());
        // TID값이 NULL일시 실패 Return
        if (StringUtils.isEmpty(tId)) {
//            verifyReturn.setAfi(agwFacilityInfoSet(verifyDN.getParameters().getCrv().getCmi()));
            verifyReturn.setResultVo(tIdNull());
            return verifyReturn;
        }
        //ResContents GET
        String result = ipcRequestSet(tId, command, ResContents.verifyDN);

        //Response Setting
        if (!StringUtils.isEmpty(result)) {

            //CALLG_RES BUSY|OK -> BUSY|OK
            verifyReturn.setResultVo(resultVoSet(result, ResContents.verifyDN));
            return verifyReturn;
        }
        else{
            verifyReturn.setResultVo(timeOut(result));
            return verifyReturn;
        }
    }

    public ResultVo monitorDNResponse(MonitorDNReqVo monitorDNReq){
        log.info("### MonitorDN ###");
//        log.info("### MonitorDN ###: {}", monitorDN.getParameters());
//        log.info("### MonitorDN ###: {}", monitorDN.getParameters().getCrv().getCmi());
//        log.info("### MonitorDN ###: {}", monitorDN.getParameters());
//        log.info("### MonitorDN ###: {}", monitorDN.getParameters());
//        log.info("### MonitorDN ###: {}", monitorDN.getParameters());

        //Command Setting || VQMON CH ACT trsSvrlp trsSvrPort reqID
        String command = String.format(ReqCommand.monitorDN.getFormat()
                , ReqCommand.monitorDN.getCommand()
                , IpcUtil.getCh(monitorDNReq.getCrv().getCmi().getFlln(), monitorDNReq.getCrv().getCmi().getLln())
                , Status.ACT.name()
                , monitorDNReq.getTrsSvrIp()
                , monitorDNReq.getTrsSvrPort()
                , monitorDNReq.getCrv().getReqId()
                //, 0x7fffffff

        );


        log.info("### monitorDN command: {}", command);
        //DB에서 장비 TID 조회
        String tId = getTid(monitorDNReq.getCrv().getCmi().getFlln(), monitorDNReq.getCrv().getCmi().getColcode(), monitorDNReq.getCrv().getCmi().getAgwIp());
        if (StringUtils.isEmpty(tId)) {
            return tIdNull();
        }
        //ResContents GET
        String result = ipcRequestSet(tId, command, ResContents.monitorDN);

        //Response Setting
        if (!StringUtils.isEmpty(result)) {
            //수신 데이터 Rdis에 저장
            monitorDNRedisSave(tId, monitorDNReq);
            //VQMON_RES OK -> OK
            return resultVoSet(result, ResContents.monitorDN);
        }else{
            return timeOut(result);
        }
    }

    public ResultVo unmonitorDNResponse(CommonReqVo commonReqVo){
        // 취소전 Redis에 들어있는 monitorDN GET
        MonitorDnDataModel monitor = redisService.monitorDataGet(commonReqVo.getCmi().getColcode());
        log.info("### unmonitorDB GET MonitorDN: {}", monitor);
        UnmonitorDNResponse response = new UnmonitorDNResponse();

        //Command Setting || VQMON CH DEACT trsSvrlp trsSvrPort reqID
        String command = String.format(ReqCommand.monitorDN.getFormat()
                , ReqCommand.monitorDN.getCommand()
                , IpcUtil.getCh(commonReqVo.getCmi().getFlln(), commonReqVo.getCmi().getLln())
                , Status.DEACT.name()
                , monitor.getTrsSvrIp()
                , commonReqVo.getReqId()
                , monitor.getTrsSvrPor()
                //,  0x7fffffff

        );


        log.info("### unmonitorDN command: {}", command);
        //DB에서 장비 TID 조회
        String tId = getTid(commonReqVo.getCmi().getFlln(), commonReqVo.getCmi().getColcode(), commonReqVo.getCmi().getAgwIp());
        if (StringUtils.isEmpty(tId)) {
            return tIdNull();
        }

        //ResContents GET
        String result = ipcRequestSet(tId, command, ResContents.monitorDN);

        //Response Setting
        if (!StringUtils.isEmpty(result)) {
            //수신 데이터 Rdis에서 삭제
            redisService.monitorDnDel(tId);
            //VQMON_RES OK -> OK
            return resultVoSet(result, ResContents.monitorDN);
        }else{
            return timeOut(result);
        }
    }

    public ResultVo checkLineResponse(CommonReqVo checkLineReq){
        CheckLineResponse response = new CheckLineResponse();
        //Command Setting || INTEST 0 0 CH CH 0(전체)
        String command = String.format(ReqCommand.checkLine.getFormat()
                , ReqCommand.checkLine.getCommand()
                , IpcUtil.getCh(checkLineReq.getCmi().getFlln(), checkLineReq.getCmi().getLln())
                , IpcUtil.getCh(checkLineReq.getCmi().getFlln(), checkLineReq.getCmi().getLln())
                //RG, RD, CC, DS, DR, PR 체크하여 계산(2진수 -> 16진수)해야 하지만 모두 체크한것으로 처리
                , 0
        );
        log.info("### checkLine command: {}", command);

        //DB에서 장비 TID 조회
        String tId = getTid(checkLineReq.getCmi().getFlln(), checkLineReq.getCmi().getColcode(), checkLineReq.getCmi().getAgwIp());
        if (StringUtils.isEmpty(tId)) {
            return tIdNull();
        }
        //ResContents GET
        String result = ipcRequestSet(tId, command, ResContents.checkLine);

        //Response Setting
        if (!StringUtils.isEmpty(result)) {
            // (임시) INTEST CH=CH OK|BUSY -> OK|BUSY

            String checkLineResult = contentsResultSet(result, ResContents.checkLine);
            log.info("checkLineResult: {}", checkLineResult);
            ResultVo resultVo = new ResultVo();
            //감시 상태 확인
            // OG ??
            if (!checkLineResult.contains("STOP") &&
                    !checkLineResult.contains("BUSY") &&
                    !checkLineResult.contains("HOOKOFF_FAIL") &&
                    !checkLineResult.contains("NK") &&
                    !checkLineResult.contains("ALREADY")) {
                resultVo.setResultCode(0);
//                resultVo.setResultMessage(checkLineResult);
            } else {
                resultVo.setResultCode(1);
                resultVo.setResultMessage(checkLineResult);
                return resultVo;
            }
            return resultVo;
        }else{
            return timeOut(result);
        }
    }

    /**
     * NOTI Check - 품질 요청 재등록
     * Redis에서 Noti데이터 조회하다 데이터가 들어오면 품질 요청을 재등록 한다.
     * @throws JsonProcessingException
     */
    @Scheduled(fixedDelay = 1)
    public void notiCheck() throws JsonProcessingException {
        //1. TID 획득 | LPOP MONITORDN_IPC
//        log.info("### NOTI START");
        String tId;
//        do {
//            TimeUnit.SECONDS.sleep(5);
        tId = redisService.notiTidGet();
        log.info("recover: {}", tId);
//        } while (StringUtils.isEmpty(tId));
        //2. HGET MONITORDB TID
        if (!StringUtils.isEmpty(tId)) {
            MonitorDnDataModel monitorData = redisService.monitorDataGet(tId);
            log.info("monitorData: {}", monitorData);
            //3. RPUSH IPC MONITORDN
            //Command Setting || VQMON CH ACT trsSvrlp trsSvrPort reqID
            if (!ObjectUtils.isEmpty(monitorData)) {
                String command = String.format(ReqCommand.monitorDN.getFormat()
                        , ReqCommand.monitorDN.getCommand()
                        , IpcUtil.getCh(monitorData.getFlln(), monitorData.getLln())
                        , Status.ACT.name()
                        , monitorData.getTrsSvrIp()
                        , monitorData.getTrsSvrPor()
                        , monitorData.getReqId()
                );
                log.info("command: {}", command);
                String result = ipcRequestSet(tId, command, ResContents.noti);
//                log.info("### NOTI END");
            }
        }
    }

    /**
     *
     * @param tId : 장비 TID
     * @param command : IPC Command
     * @param resContents : Redis Left POP TimeOut
     * @return Redis IPC Data Save
     */
    //IPC Request SET
    public String ipcRequestSet(String tId, String command, ResContents resContents) {
        //Response받을 Key(UUID) (36-자리수까지 0으로 채움)
        String reqId = UUID.randomUUID().toString().replaceAll("-", "");

        //Base64 encoded data
        String encodedData = encodeDataSet(tId, command, reqId);

        String appendData = String.format(",%s,%s,%s,%s,%s", sigid, flag, destPrcs, srcPrcs, encodedData);

        //len(전체길이) -> 16진수(4)
        int lenLength = ReqLength.Len.getLength() + appendData.length();
        log.info("### lenLength: {}", lenLength);
        String lenHexa = IpcUtil.alterHexa(lenLength);
        //len + ,sigId,flag,destPrcs,srcPrcs,encodedData
        String ipcRequest = lenHexa.concat(appendData);
        log.info("### ipcRequest: {}", ipcRequest);


        return redisService.ipcRequestSave(ipcRequest, reqId, resContents);
    }

    /**
     * DB에서 StartLLN으로 장비 TID 조회
     * @param flln: StartLLN
     * @return : TID
     */
    //DB에서 FLLN -> 장비 TID조회
    public String getTid(int flln, String callCode, String agwIp){
        log.info("### AGW FLLN: {}, CALLCODE: {}", flln, callCode);
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        String tid =  queryFactory
                .select(qAgwConfT.tid)
                .from(qAgwConfT)
                .where(qAgwConfT.startLln.eq(flln)
                        .and(qAgwConfT.tid.eq(callCode)))
                .fetchOne();
        if(StringUtils.isEmpty(tid)){
            tid =  queryFactory
                    .select(qAgwConfT.tid)
                    .from(qAgwConfT)
                    .where(qAgwConfT.startLln.eq(flln)
                            .and(qAgwConfT.mg1ip.eq(agwIp))
                            .or(qAgwConfT.mg2ip.eq(agwIp))
                            .or(qAgwConfT.mg3ip.eq(agwIp))
                            .or(qAgwConfT.mg4ip.eq(agwIp)))
                    .fetchOne();
        }
        return tid;
    }

    /**
     * IPC Request Data EnCodeData 셋팅
     * @param tId : 장비 TID
     * @param command: IPC Request Command
     * @param reqId: 요청 UUID
     * @return : IPC Response Data
     */
    public String encodeDataSet(String tId, String command, String reqId) {

        //TID(AGW 장비) (36-자리수까지 0으로 채움)
        //요청 command (256)
        //"%" + limit + "s"
        String data = String.format("%s%s%s", IpcUtil.zeroRightAdd(reqId, ReqLength.ReqId.getLength()), IpcUtil.zeroRightAdd(tId, ReqLength.Tid.getLength()), IpcUtil.zeroRightAdd(command, ReqLength.Command.getLength()));
        log.info("### data: {}", data);
        return new String(Base64.encodeBase64(data.getBytes()));
    }

    /**
     * resultVo SET | Response 결과 -> 1. 해당 명령어 제외 , 2. "0" -> "", 3. OK, NK 처리
     * @param result IPC Response Result Data
     * @param resContents IPC Response Command
     * @return
     */

    public ResultVo resultVoSet(String result, ResContents resContents) {
        String contentsResult = contentsResultSet(result, resContents);
        log.info("### {} contentsResult: {}", resContents.name(), contentsResult);
        ResultVo resultVo = new ResultVo();
        if (contentsResult.equals("OK")) {
            resultVo.setResultCode(0);
//            resultVo.setResultMessage(contentsResult);
        } else {
            resultVo.setResultCode(1);
            resultVo.setResultMessage(contentsResult);
        }
        log.info("### resultVO:{}", resultVo);
        return resultVo;
    }
    public ResultVo timeOut(String result){
        ResultVo resultVo = new ResultVo();
        resultVo.setResultCode(1);
        resultVo.setResultMessage("TIME OUT");

        return resultVo;
    }

    /**
     * 0제거
     * @param result
     * @param resContents
     * @return
     */
    public String contentsResultSet(String result, ResContents resContents) {
        return result.replace(resContents.getResContents(), "").replaceAll("\0", "");
    }

    public AgwFacilityInfo agwFacilityInfoSet(CommonMeasInfo commonMeasInfo) {
        AgwFacilityInfo agwFacilityInfo = new AgwFacilityInfo();
        agwFacilityInfo.setAgwIp(commonMeasInfo.getAgwIp());
        agwFacilityInfo.setEmsIp(commonMeasInfo.getEmsIp());
        agwFacilityInfo.setColcode(commonMeasInfo.getColcode());
        agwFacilityInfo.setFlln(commonMeasInfo.getFlln());
        agwFacilityInfo.setLln(commonMeasInfo.getLln());
        agwFacilityInfo.setManufacturer(commonMeasInfo.getManufacturer());
        agwFacilityInfo.setModel(commonMeasInfo.getModel());
        agwFacilityInfo.setPots(commonMeasInfo.getPots());
        agwFacilityInfo.setMgid(commonMeasInfo.getMgid());
        return agwFacilityInfo;
    }

    public ResultVo tIdNull() {
        ResultVo resultVo = new ResultVo();
        resultVo.setResultCode(1);
        resultVo.setResultMessage("해당 장비를 찾을수 없습니다.");
        log.info("### resultVO: {}", resultVo);
        return resultVo;
    }

    public void monitorDNRedisSave(String tId, MonitorDNReqVo monitorDNReqVo) {
        redisService.monitorDnSave(tId, toJsonStr(monitorDNReqVo));

    }

    public String toJsonStr(MonitorDNReqVo monitorDNReqVo) {
        JSONObject toJo = new JSONObject();
        toJo.put("trsSvrIp", monitorDNReqVo.getTrsSvrIp());
        toJo.put("trsSvrPor", monitorDNReqVo.getTrsSvrPort());
        toJo.put("reqId", monitorDNReqVo.getCrv().getReqId());
        toJo.put("flln", monitorDNReqVo.getCrv().getCmi().getFlln());
        toJo.put("lln", monitorDNReqVo.getCrv().getCmi().getLln());
        return toJo.toString();
    }




}
