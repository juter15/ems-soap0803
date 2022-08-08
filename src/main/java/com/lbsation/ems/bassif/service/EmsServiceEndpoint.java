package com.lbsation.ems.bassif.service;

import com.kt.agwems.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;

@WebService(serviceName = "BassServiceImplService", portName = "BassServiceInterface"
        , targetNamespace = "http://agwems.kt.com/"
,name = "BassServiceImplService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
//@RequiredArgsConstructor
@Slf4j
public class EmsServiceEndpoint {
    @Resource
    private IpcService ipcService;
    @WebMethod(action="http://agwems.kt.com/BassServiceInterface/monitorDNRequest", operationName = "monitorDN")
    @Action(
            input = "http://agwems.kt.com/BassServiceInterface/monitorDNRequest",
            output = "http://agwems.kt.com/BassServiceInterface/monitorDNResponse")
    @WebResult(name = "return",  targetNamespace = "")
    public ResultVo monitorDN(@WebParam(name = "parameters") MonitorDNReqVo monitorDNReqVo) {
        log.info("monitorDN");

        return  ipcService.monitorDNResponse(monitorDNReqVo);
//        return  new MonitorDNResponse();
    }

    @WebMethod(action="http://agwems.kt.com/BassServiceInterface/verifyDNRequest", operationName = "verifyDN")
    @Action(
            input = "http://agwems.kt.com/BassServiceInterface/verifyDNRequest",
            output = "http://agwems.kt.com/BassServiceInterface/verifyDNResponse")
    @WebResult(name = "return",  targetNamespace = "")

    public VerifyDNResVo verifyDN(@WebParam(name = "parameters") VerifyDNReqVo verifyDNReq) {
        return ipcService.verifyDNResponse(verifyDNReq);
    }

    @WebMethod(action="http://agwems.kt.com/BassServiceInterface/unmonitorDNRequest", operationName = "unmonitorDN")
    @Action(
            input = "http://agwems.kt.com/BassServiceInterface/unmonitorDNRequest",
            output = "http://agwems.kt.com/BassServiceInterface/unmonitorDNResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ResultVo unmonitorDN(@WebParam(name = "parameters") CommonReqVo unmonitorDNReq) {
        return ipcService.unmonitorDNResponse(unmonitorDNReq);
    }

    @WebMethod(action="http://agwems.kt.com/BassServiceInterface/checkLineRequest", operationName = "checkLine")
    @Action(
            input = "http://agwems.kt.com/BassServiceInterface/checkLineRequest",
            output = "http://agwems.kt.com/BassServiceInterface/checkLineResponse")
    @WebResult(name = "return",  targetNamespace = "")

    public ResultVo checkLine(@WebParam(name = "parameters") CommonReqVo checkLineReq){
        return ipcService.checkLineResponse(checkLineReq);
    }
}
