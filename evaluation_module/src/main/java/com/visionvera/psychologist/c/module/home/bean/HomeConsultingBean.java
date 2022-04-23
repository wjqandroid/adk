package com.visionvera.psychologist.c.module.home.bean;

import java.io.Serializable;

public class HomeConsultingBean implements Serializable {
    public String name;
    public boolean isSelect;

    @Override
    public String toString() {
        return "HomeConsultingBean{" +
                "name='" + name + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
