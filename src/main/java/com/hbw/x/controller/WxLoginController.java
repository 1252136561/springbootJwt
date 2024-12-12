package com.hbw.x.controller;


import com.hbw.x.service.WxLoginService;
import com.hbw.x.util.ApiResult;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WxLoginController {

      @Autowired
      private WxLoginService wxLoginService;


      @PostMapping("/test")
      public ApiResult test(@RequestBody Map<String, String> req)throws Exception {
            ApiResult result = null;
            try {
                  result = ApiResult.newInstance("T", null, null, wxLoginService.testSelect(req).toString());
            } catch (Exception e) {
                  result = ApiResult.newInstance("F", null, null, e.getMessage());

                  e.printStackTrace();
            }

            return result;
      }

      @PostMapping("/wxlogin")
      public ApiResult wxLogin(@RequestBody Map<String, String> req) {
            ApiResult result = null;
            try {
                  result = ApiResult.newInstance("T", null, null, wxLoginService.wxLogin(req));
            } catch (Exception e) {
                  result = ApiResult.newInstance("F", null, null, e.getMessage());

                  e.printStackTrace();
            }

            return result;
      }

}
