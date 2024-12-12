package com.hbw.x.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public interface WxLoginService {

      String testSelect(Map<String, String> req);

      public String wxLogin(Map<String, String> req);


}
