package com.hbw.x.dto;

import lombok.Data;

@Data
public class WxResponse {

      private String openid;
      private String session_key;


      private  String errmsg;
      private String rid;
      private int errcode;




}