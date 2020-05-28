package com.fei.softwaredevlopmentliftcycle.model.gante;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/5/17
 * @Description: $value$
 */
@Setter
@Getter
public class GanTeModel {
    private Integer gunter_id;
    private Long start_time;
    private Long end_time;
    private Integer level;
    private ParamsModel params;
}
