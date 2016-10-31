package com.ea.helper;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ChinzorigD on 9/26/16.
 */
public class OrderHelper {

    @NotNull
    public Integer orderId;

    //@Size(min = 1, max = 30)
    @Min(value=1)
    @Max(value=9)
    public Integer travelledMiles;

    public Integer getOrderId() {
        return this.orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTravelledMiles() {
        return this.travelledMiles;
    }
    public void setTravelledMiles(Integer travelledMiles) {
        this.travelledMiles = travelledMiles;
    }

}
