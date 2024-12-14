package com.hbw.x.service.impl;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbw.x.dao.UserDao;
import com.hbw.x.dto.WxResponse;
import com.hbw.x.entity.User;
import com.hbw.x.service.WxLoginService;
import com.hbw.x.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WxLoginServiceImpl implements WxLoginService {

      private static final Logger log = LoggerFactory.getLogger(WxLoginServiceImpl.class);
      @Value("${wx.appId}")
      private String appId;

      @Value("${wx.appSecret}")
      private String appSecret;

      @Value("${wx.loginUrl}")
      private String wxLoginUrl;

      @Autowired
      private UserDao userRepository;

      @Override
      public String testSelect(Map<String, String> req) {
            log.info("req:[{}]",req.toString());
            List<User> all = userRepository.findAll();
            return all.toString();
      }


      @Override
      public JSONObject refresh(Map<String, String> req) {
            JSONObject r = new JSONObject();
            if (StringUtils.isEmpty(req.get("refreshToken"))) {
                  r.put("error","refreshToken is null");
                  return r;
            }
            User u = userRepository.findByOpenId(req.get("refreshToken"));

            if (u == null){
                  r.put("errpr","该用户未授权");
                  return r;
            }

            String t = JwtUtil.generateToken(u.getOpenId());

            r.put("accessToken",u.getOpenId());
            r.put("token",t);


            return r;
      }

      @Override
      public JSONObject wxLogin(Map<String, String> req) {
            String code = req.get("code");

            String url = wxLoginUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";


            log.info("wx-reqUrl:[{}]",url);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // 解析微信服务器的响应
            String responseBody = response.getBody();

            log.info("wx-response:[{}]",responseBody);

            //提取openid和session_key
            WxResponse wxResponse = parseWxResponse(responseBody);

            if (StringUtils.isEmpty(wxResponse.getOpenid())){
                  wxResponse.setOpenid("errOAForWx");
                  log.warn("wxOA failed,  err:[{}]",wxResponse.toString());


//                  return null;

            }


            // 检查数据库中是否已经有一个与这个openid关联的用户
            User user = userRepository.findByOpenId(wxResponse.getOpenid());

            if (user == null) {
                  // 如果没有，创建一个新的用户
                  user = new User();
                  user.setUserName(code);
                  user.setOpenId(wxResponse.getOpenid());

            }

            // 更新用户的session_key
            user.setSessionKey(wxResponse.getSession_key());

            // 保存用户
            User save = userRepository.save(user);
            String tok =  JwtUtil.generateToken(save.getOpenId());

            log.info("token:[{}]",tok);

            JSONObject r = new JSONObject();
            r.put("accessToken", user.getOpenId());
            r.put("token",tok);



            //  1.会话密钥 session_key 是对用户数据进行 加密签名 的密钥。
            //  为了应用自身的数据安全，开发者服务器不应该把会话密钥下发到小程序，也不应该对外提供这个密钥。
            //  2.临时登录凭证 code 只能使用一次，5分钟未被使用自动过期。
            //  根据用户的openId生成token返回给前端

            return r;
      }

      private WxResponse parseWxResponse(String responseBody) {
            WxResponse o = JSON.parseObject(responseBody,WxResponse.class);

            return o;
      }
}

