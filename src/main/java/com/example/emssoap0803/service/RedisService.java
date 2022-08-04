package com.example.emssoap0803.service;

import com.example.emssoap0803.enums.ResContents;
import com.example.emssoap0803.model.MonitorDnDataModel;
import com.example.emssoap0803.util.IpcUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {
    @Value("${ipc.requestKey}")
    private String requestKey;
    @Value("${monitorDnKey}") // 품질 측정 요청 등록/취소시 Redis Key
    String monitorDnKey;

    @Value("${notiKey}") // NOTI KEY
    String notiKey;
    private final RedisTemplate<String, String> redisTemplate;

    public String ipcRequestSave(String ipcRequestContents, String reqId, ResContents resContents) {
        String ipcResponse;
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        listOperations.rightPush(requestKey, ipcRequestContents);
        log.info("### reqId: {}", reqId);

//        do { //
////            TimeUnit.SECONDS.sleep(1);
        if(!ResContents.noti.name().equals(resContents.name())){

                ipcResponse = ipcResponseGet(reqId, resContents);


    //        } while (StringUtils.isEmpty(ipcResponse));
            log.info("### ipcResponse: {}", ipcResponse);
            return IpcUtil.getResponseContents(ipcResponse);
        }
        return null;
    }

    public String ipcResponseGet(String key, ResContents resContents) {

        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        if (ResContents.noti.name().equals(resContents.name())) {
            return listOperations
                    .leftPop(key, Duration.ZERO);
//                    .leftPop(key, Duration.ofSeconds(resContents.getTimeOut()));
        }
        else {
            return listOperations
//                    .leftPop(key, Duration.ZERO);
                    .leftPop(key, Duration.ofSeconds(resContents.getTimeOut()));
        }
    }

    public String notiTidGet() {
//        String key = "RECOVER_MONITOR_NOTI";
        String notiData = ipcResponseGet(notiKey, ResContents.noti);
        log.info("notiData: {}", notiData);
        if (StringUtils.isEmpty(notiData)) {
            return null;
        } else {
            String command = new String(Base64.decodeBase64(notiData.split(",")[5].getBytes()));
            log.info("command: {}", command);
            String tid = Objects.requireNonNull(command).replaceAll("\0", "");
            log.info("tid: {}", tid);
            return tid;
        }
    }

    public void monitorDnSave(String tId, String data) {
        log.info("### monitorDn Redis Save | tid: {},data:{}", tId, data);
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(monitorDnKey, tId, data);
    }
    public void monitorDnDel(String tId) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        log.info("### delete: {}", hashOperations.get(monitorDnKey, tId));
        hashOperations.delete(monitorDnKey, tId);
    }

    public MonitorDnDataModel monitorDataGet(String tId) {
        try{

            HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
            ObjectMapper mapper = new ObjectMapper();
            Object data = hashOperations.get(monitorDnKey, tId);
            if (!ObjectUtils.isEmpty(data)) {
                return mapper.readValue(data.toString(), MonitorDnDataModel.class);
            } else {
                return null;
            }
        }catch (JsonProcessingException e){
            return null;
        }
    }

}
