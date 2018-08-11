package com.jt.plt.product.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_area")
public class Area {
    /**
     * 行政区划代码
     */
    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "region_level")
    private Integer regionLevel;

    /**
     * 上级行政区划代码
     */
    @Column(name = "parent_region_code")
    private String parentRegionCode;

    /**
     * 获取行政区划代码
     *
     * @return region_code - 行政区划代码
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * 设置行政区划代码
     *
     * @param regionCode 行政区划代码
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * @return region_name
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * @return region_level
     */
    public Integer getRegionLevel() {
        return regionLevel;
    }

    /**
     * @param regionLevel
     */
    public void setRegionLevel(Integer regionLevel) {
        this.regionLevel = regionLevel;
    }

    /**
     * 获取上级行政区划代码
     *
     * @return parent_region_code - 上级行政区划代码
     */
    public String getParentRegionCode() {
        return parentRegionCode;
    }

    /**
     * 设置上级行政区划代码
     *
     * @param parentRegionCode 上级行政区划代码
     */
    public void setParentRegionCode(String parentRegionCode) {
        this.parentRegionCode = parentRegionCode;
    }
}