package com.visionvera.library.greendao.testbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author lilongfeng
 * date 2019/6/18
 */
@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;
    @Unique
    int studentNo;

    int age;

    @Generated(hash = 8774757)
    public Student(Long id, int studentNo, int age) {
        this.id = id;
        this.studentNo = studentNo;
        this.age = age;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStudentNo() {
        return this.studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
