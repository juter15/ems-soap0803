package com.lbsation.ems.bassif;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class WsdlController {
    @Value("${soap.ip}")
    private String ip;

    @GetMapping("/service/agwems")
    public ResponseEntity getWsdl(
            @RequestParam("wsdl") String wsdl
    ) throws IOException {
        String contents = "<?xml version='1.0' encoding='UTF-8'?><wsdl:definitions xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\" xmlns:wsaw=\"http://www.w3.org/2006/05/addressing/wsdl\" xmlns:wsam=\"http://www.w3.org/2007/05/addressing/metadata\" xmlns:tns=\"http://agwems.kt.com/\" xmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\" xmlns:ns1=\"http://schemas.xmlsoap.org/soap/http\" name=\"BassServiceImplService\" targetNamespace=\"http://agwems.kt.com/\">\n" +
                "  <wsdl:types>\n" +
                "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:tns=\"http://agwems.kt.com/\" targetNamespace=\"http://agwems.kt.com/\" version=\"1.0\">\n" +
                "\n" +
                "  <xs:complexType name=\"commonReqVo\">\n" +
                "    <xs:sequence>\n" +
                "      <xs:element minOccurs=\"0\" name=\"cmi\" type=\"tns:commonMeasInfo\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"eventTime\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"reqId\" type=\"xs:string\"/>\n" +
                "    </xs:sequence>\n" +
                "  </xs:complexType>\n" +
                "\n" +
                "  <xs:complexType name=\"commonMeasInfo\">\n" +
                "    <xs:sequence>\n" +
                "      <xs:element minOccurs=\"0\" name=\"agwIp\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"apsIp\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"colcode\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"emsIp\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"endTime\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"exId\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"flcYn\" type=\"xs:string\"/>\n" +
                "      <xs:element name=\"flln\" type=\"xs:int\"/>\n" +
                "      <xs:element name=\"lln\" type=\"xs:int\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"manufacturer\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"mgid\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"model\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"pctIp\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"pots\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"pstnLen\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"startTime\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"v52Yn\" type=\"xs:string\"/>\n" +
                "    </xs:sequence>\n" +
                "  </xs:complexType>\n" +
                "\n" +
                "  <xs:complexType name=\"resultVo\">\n" +
                "    <xs:sequence>\n" +
                "      <xs:element name=\"resultCode\" type=\"xs:int\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"resultMessage\" type=\"xs:string\"/>\n" +
                "    </xs:sequence>\n" +
                "  </xs:complexType>\n" +
                "\n" +
                "  <xs:complexType name=\"verifyDNReqVo\">\n" +
                "    <xs:sequence>\n" +
                "      <xs:element minOccurs=\"0\" name=\"crv\" type=\"tns:commonReqVo\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"tryDn\" type=\"xs:string\"/>\n" +
                "    </xs:sequence>\n" +
                "  </xs:complexType>\n" +
                "\n" +
                "  <xs:complexType name=\"verifyDNResVo\">\n" +
                "    <xs:sequence>\n" +
                "      <xs:element minOccurs=\"0\" name=\"afi\" type=\"tns:agwFacilityInfo\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"resultVo\" type=\"tns:resultVo\"/>\n" +
                "    </xs:sequence>\n" +
                "  </xs:complexType>\n" +
                "\n" +
                "  <xs:complexType name=\"agwFacilityInfo\">\n" +
                "    <xs:sequence>\n" +
                "      <xs:element minOccurs=\"0\" name=\"agwIp\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"colcode\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"emsIp\" type=\"xs:string\"/>\n" +
                "      <xs:element name=\"flln\" type=\"xs:int\"/>\n" +
                "      <xs:element name=\"lln\" type=\"xs:int\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"manufacturer\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"mgId\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"model\" type=\"xs:string\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"pots\" type=\"xs:string\"/>\n" +
                "    </xs:sequence>\n" +
                "  </xs:complexType>\n" +
                "\n" +
                "  <xs:complexType name=\"monitorDNReqVo\">\n" +
                "    <xs:sequence>\n" +
                "      <xs:element minOccurs=\"0\" name=\"crv\" type=\"tns:commonReqVo\"/>\n" +
                "      <xs:element minOccurs=\"0\" name=\"trsSvrIp\" type=\"xs:string\"/>\n" +
                "      <xs:element name=\"trsSvrPort\" type=\"xs:int\"/>\n" +
                "    </xs:sequence>\n" +
                "  </xs:complexType>\n" +
                "\n" +
                "</xs:schema>\n" +
                "  </wsdl:types>\n" +
                "  <wsdl:message name=\"monitorDNResponse\">\n" +
                "    <wsdl:part name=\"return\" type=\"tns:resultVo\">\n" +
                "    </wsdl:part>\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"verifyDNResponse\">\n" +
                "    <wsdl:part name=\"return\" type=\"tns:verifyDNResVo\">\n" +
                "    </wsdl:part>\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"checkLineResponse\">\n" +
                "    <wsdl:part name=\"return\" type=\"tns:resultVo\">\n" +
                "    </wsdl:part>\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"unmonitorDN\">\n" +
                "    <wsdl:part name=\"parameters\" type=\"tns:commonReqVo\">\n" +
                "    </wsdl:part>\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"verifyDN\">\n" +
                "    <wsdl:part name=\"parameters\" type=\"tns:verifyDNReqVo\">\n" +
                "    </wsdl:part>\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"checkLine\">\n" +
                "    <wsdl:part name=\"parameters\" type=\"tns:commonReqVo\">\n" +
                "    </wsdl:part>\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"monitorDN\">\n" +
                "    <wsdl:part name=\"parameters\" type=\"tns:monitorDNReqVo\">\n" +
                "    </wsdl:part>\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:message name=\"unmonitorDNResponse\">\n" +
                "    <wsdl:part name=\"return\" type=\"tns:resultVo\">\n" +
                "    </wsdl:part>\n" +
                "  </wsdl:message>\n" +
                "  <wsdl:portType name=\"BassServiceInterface\">\n" +
                "    <wsdl:operation name=\"unmonitorDN\">\n" +
                "      <wsdl:input message=\"tns:unmonitorDN\" name=\"unmonitorDN\" wsam:Action=\"http://agwems.kt.com/BassServiceInterface/unmonitorDNRequest\" wsaw:Action=\"http://agwems.kt.com/BassServiceInterface/unmonitorDNRequest\">\n" +
                "    </wsdl:input>\n" +
                "      <wsdl:output message=\"tns:unmonitorDNResponse\" name=\"unmonitorDNResponse\" wsam:Action=\"http://agwems.kt.com/BassServiceInterface/unmonitorDNResponse\" wsaw:Action=\"http://agwems.kt.com/BassServiceInterface/unmonitorDNResponse\">\n" +
                "    </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"verifyDN\">\n" +
                "      <wsdl:input message=\"tns:verifyDN\" name=\"verifyDN\" wsam:Action=\"http://agwems.kt.com/BassServiceInterface/verifyDNRequest\" wsaw:Action=\"http://agwems.kt.com/BassServiceInterface/verifyDNRequest\">\n" +
                "    </wsdl:input>\n" +
                "      <wsdl:output message=\"tns:verifyDNResponse\" name=\"verifyDNResponse\" wsam:Action=\"http://agwems.kt.com/BassServiceInterface/verifyDNResponse\" wsaw:Action=\"http://agwems.kt.com/BassServiceInterface/verifyDNResponse\">\n" +
                "    </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"checkLine\">\n" +
                "      <wsdl:input message=\"tns:checkLine\" name=\"checkLine\" wsam:Action=\"http://agwems.kt.com/BassServiceInterface/checkLineRequest\" wsaw:Action=\"http://agwems.kt.com/BassServiceInterface/checkLineRequest\">\n" +
                "    </wsdl:input>\n" +
                "      <wsdl:output message=\"tns:checkLineResponse\" name=\"checkLineResponse\" wsam:Action=\"http://agwems.kt.com/BassServiceInterface/checkLineResponse\" wsaw:Action=\"http://agwems.kt.com/BassServiceInterface/checkLineResponse\">\n" +
                "    </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"monitorDN\">\n" +
                "      <wsdl:input message=\"tns:monitorDN\" name=\"monitorDN\" wsam:Action=\"http://agwems.kt.com/BassServiceInterface/monitorDNRequest\" wsaw:Action=\"http://agwems.kt.com/BassServiceInterface/monitorDNRequest\">\n" +
                "    </wsdl:input>\n" +
                "      <wsdl:output message=\"tns:monitorDNResponse\" name=\"monitorDNResponse\" wsam:Action=\"http://agwems.kt.com/BassServiceInterface/monitorDNResponse\" wsaw:Action=\"http://agwems.kt.com/BassServiceInterface/monitorDNResponse\">\n" +
                "    </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "  </wsdl:portType>\n" +
                "  <wsdl:binding name=\"BassServiceImplServiceSoapBinding\" type=\"tns:BassServiceInterface\">\n" +
                "    <soap:binding style=\"rpc\" transport=\"http://schemas.xmlsoap.org/soap/http\"/>\n" +
                "    <wsdl:operation name=\"unmonitorDN\">\n" +
                "      <soap:operation soapAction=\"http://agwems.kt.com/BassServiceInterface/unmonitorDNRequest\" style=\"rpc\"/>\n" +
                "      <wsdl:input name=\"unmonitorDN\">\n" +
                "        <soap:body namespace=\"http://agwems.kt.com/\" use=\"literal\"/>\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output name=\"unmonitorDNResponse\">\n" +
                "        <soap:body namespace=\"http://agwems.kt.com/\" use=\"literal\"/>\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"verifyDN\">\n" +
                "      <soap:operation soapAction=\"http://agwems.kt.com/BassServiceInterface/verifyDNRequest\" style=\"rpc\"/>\n" +
                "      <wsdl:input name=\"verifyDN\">\n" +
                "        <soap:body namespace=\"http://agwems.kt.com/\" use=\"literal\"/>\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output name=\"verifyDNResponse\">\n" +
                "        <soap:body namespace=\"http://agwems.kt.com/\" use=\"literal\"/>\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"checkLine\">\n" +
                "      <soap:operation soapAction=\"http://agwems.kt.com/BassServiceInterface/checkLineRequest\" style=\"rpc\"/>\n" +
                "      <wsdl:input name=\"checkLine\">\n" +
                "        <soap:body namespace=\"http://agwems.kt.com/\" use=\"literal\"/>\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output name=\"checkLineResponse\">\n" +
                "        <soap:body namespace=\"http://agwems.kt.com/\" use=\"literal\"/>\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "    <wsdl:operation name=\"monitorDN\">\n" +
                "      <soap:operation soapAction=\"http://agwems.kt.com/BassServiceInterface/monitorDNRequest\" style=\"rpc\"/>\n" +
                "      <wsdl:input name=\"monitorDN\">\n" +
                "        <soap:body namespace=\"http://agwems.kt.com/\" use=\"literal\"/>\n" +
                "      </wsdl:input>\n" +
                "      <wsdl:output name=\"monitorDNResponse\">\n" +
                "        <soap:body namespace=\"http://agwems.kt.com/\" use=\"literal\"/>\n" +
                "      </wsdl:output>\n" +
                "    </wsdl:operation>\n" +
                "  </wsdl:binding>\n" +
                "  <wsdl:service name=\"BassServiceImplService\">\n" +
                "    <wsdl:port binding=\"tns:BassServiceImplServiceSoapBinding\" name=\"BassServiceImplPort\">\n" +
                "      <soap:address location=\""+ip+":30388/agwems\"/>\n" +
                "    </wsdl:port>\n" +
                "  </wsdl:service>\n" +
                "</wsdl:definitions>";
        return ResponseEntity.ok(contents);
    }
}
