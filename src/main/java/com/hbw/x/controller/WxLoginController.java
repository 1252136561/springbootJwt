package com.hbw.x.controller;


import com.hbw.x.service.WxLoginService;
import com.hbw.x.util.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WxLoginController {

      private static final Logger log = LoggerFactory.getLogger(WxLoginController.class);
      @Autowired
      private WxLoginService wxLoginService;


      @PostMapping("/test")
      public ApiResult test(@RequestBody Map<String, String> req)throws Exception {
            ApiResult result = null;
            try {
                  result = ApiResult.newInstance("200","",wxLoginService.testSelect(req));
            } catch (Exception e) {
                  result = ApiResult.newInstance("999","wok出错了！",null);

                  log.error("/test 异常",e);
            }

            return result;
      }

      @PostMapping("/refresh")
      public ApiResult refresh(@RequestBody Map<String,String> req)throws Exception {
            ApiResult result = null;
            try {
                  result = ApiResult.newInstance("200","",wxLoginService.refresh(req));
            } catch (Exception e) {
                  result = ApiResult.newInstance("999","wok出错了！",null);

                  log.error("/refresh 异常",e);
            }

            return result;
      }


      @PostMapping("/wxlogin")
      public ApiResult wxLogin(@RequestBody Map<String, String> req) {
            ApiResult result = null;
            try {
                  result = ApiResult.newInstance("200","",wxLoginService.wxLogin(req));
            } catch (Exception e) {
                  result = ApiResult.newInstance("999","wok出错了！",null);

                  log.error("/wxlogin 异常",e);
            }

            return result;
      }

}
