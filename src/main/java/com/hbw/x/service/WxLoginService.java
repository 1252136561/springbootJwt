package com.hbw.x.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface WxLoginService {

      String testSelect(Map<String, String> req);

      JSONObject refresh(Map<String, String> req);

      public JSONObject wxLogin(Map<String, String> req);



}
