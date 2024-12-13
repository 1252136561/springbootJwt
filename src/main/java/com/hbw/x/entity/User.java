package com.hbw.x.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "id", nullable = false)
      private Integer id;

      @Column(name = "userName")
      private String userName;

      @Column(name = "password")
      private String password;

      @Column(name = "open_id")
      private String openId;

      @Column(name = "session_key")
      private String sessionKey;

      @Override
      public String toString() {
            return "User{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", openId='" + openId + '\'' +
                    ", sessionKey='" + sessionKey + '\'' +
                    '}';
      }

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public String getUserName() {
            return userName;
      }

      public void setUserName(String userName) {
            this.userName = userName;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public String getOpenId() {
            return openId;
      }

      public void setOpenId(String openId) {
            this.openId = openId;
      }

      public String getSessionKey() {
            return sessionKey;
      }

      public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
      }



}