package com.visionvera.psychologist.c.module.usercenter.bean;

import java.io.Serializable;

/**
 * @Classname:Trace
 * @author:haohuizhao
 * @Date:2021/7/28 11:03
 * @Version
 */

//区域请求

 public class SelecteCityBean  implements Serializable {
        private String id;
        private String level;
        private String code;

        private String name;
        //0 1   原点状态
        private String state;
        //0 1   选中状态
        private String selectState;



       public SelecteCityBean(String id, String level, String name, String state, String selectState,String code) {
              this.id = id;
              this.level = level;
              this.name = name;
              this.state = state;
              this.selectState = selectState;
              this.code = code;
       }
       public String getCode() {
              return code;
       }

       public void setCode(String code) {
              this.code = code;
       }
       public String getName() {
        return name;
        }

        public void setName(String name) {
        this.name = name;
        }

        public String getState() {
        return state;
        }

        public void setState(String state) {
        this.state = state;
        }

        public String getSelectState() {
        return selectState;
        }

        public void setSelectState(String selectState) {
        this.selectState = selectState;
        }

        public String getId() {
        return id;
        }

        public void setId(String id) {
        this.id = id;
        }

        public String getLevel() {
        return level;
        }

        public void setLevel(String level) {
        this.level = level;
        }
        }
