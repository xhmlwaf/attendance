package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "CLOCK_ADDRESS_SETTING")
public class ClockAddressSetting implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "LAT")
    private BigDecimal lat;

    @Column(name = "LON")
    private BigDecimal lon;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "RADIUS")
    private Integer radius;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return LAT
     */
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * @param lat
     */
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    /**
     * @return LON
     */
    public BigDecimal getLon() {
        return lon;
    }

    /**
     * @param lon
     */
    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }
}