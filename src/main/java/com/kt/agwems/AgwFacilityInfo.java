//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.7 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2022.08.05 시간 03:51:59 PM KST 
//


package com.kt.agwems;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>agwFacilityInfo complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="agwFacilityInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agwIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emsIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flln" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lln" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mgid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pots" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agwFacilityInfo", propOrder = {
    "agwIp",
    "colcode",
    "emsIp",
    "flln",
    "lln",
    "manufacturer",
    "mgid",
    "model",
    "pots"
})
public class AgwFacilityInfo {

    protected String agwIp;
    protected String colcode;
    protected String emsIp;
    protected int flln;
    protected int lln;
    protected String manufacturer;
    protected String mgid;
    protected String model;
    protected String pots;

    /**
     * agwIp 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgwIp() {
        return agwIp;
    }

    /**
     * agwIp 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgwIp(String value) {
        this.agwIp = value;
    }

    /**
     * colcode 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColcode() {
        return colcode;
    }

    /**
     * colcode 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColcode(String value) {
        this.colcode = value;
    }

    /**
     * emsIp 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmsIp() {
        return emsIp;
    }

    /**
     * emsIp 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmsIp(String value) {
        this.emsIp = value;
    }

    /**
     * flln 속성의 값을 가져옵니다.
     * 
     */
    public int getFlln() {
        return flln;
    }

    /**
     * flln 속성의 값을 설정합니다.
     * 
     */
    public void setFlln(int value) {
        this.flln = value;
    }

    /**
     * lln 속성의 값을 가져옵니다.
     * 
     */
    public int getLln() {
        return lln;
    }

    /**
     * lln 속성의 값을 설정합니다.
     * 
     */
    public void setLln(int value) {
        this.lln = value;
    }

    /**
     * manufacturer 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * manufacturer 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    /**
     * mgid 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMgid() {
        return mgid;
    }

    /**
     * mgid 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMgid(String value) {
        this.mgid = value;
    }

    /**
     * model 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * model 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * pots 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPots() {
        return pots;
    }

    /**
     * pots 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPots(String value) {
        this.pots = value;
    }

}
