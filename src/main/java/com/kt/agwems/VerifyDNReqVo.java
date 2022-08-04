//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.7 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2022.08.04 시간 07:00:35 PM KST 
//


package com.kt.agwems;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>verifyDNReqVo complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="verifyDNReqVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crv" type="{http://agwems.kt.com/}commonReqVo" minOccurs="0"/>
 *         &lt;element name="tryDn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifyDNReqVo", propOrder = {
    "crv",
    "tryDn"
})
public class VerifyDNReqVo {

    protected CommonReqVo crv;
    protected String tryDn;

    /**
     * crv 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CommonReqVo }
     *     
     */
    public CommonReqVo getCrv() {
        return crv;
    }

    /**
     * crv 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonReqVo }
     *     
     */
    public void setCrv(CommonReqVo value) {
        this.crv = value;
    }

    /**
     * tryDn 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTryDn() {
        return tryDn;
    }

    /**
     * tryDn 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTryDn(String value) {
        this.tryDn = value;
    }

}
