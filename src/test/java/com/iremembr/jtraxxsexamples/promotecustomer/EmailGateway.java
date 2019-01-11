package com.iremembr.jtraxxsexamples.promotecustomer;

import com.iremembr.jtraxxs.VoidResult;

interface EmailGateway {

    VoidResult<String> sendPromotionNotification(String email);

}
