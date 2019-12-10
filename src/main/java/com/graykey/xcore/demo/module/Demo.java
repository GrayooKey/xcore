package com.graykey.xcore.demo.module;

import com.graykey.xcore.common.base.module.BaseModule;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 示例
 *
 * @author xuezb
 * @date 2019-12-09
 */
@Entity
@org.hibernate.annotations.Table(comment = "示例", appliesTo = "a_demo")
@Table(name = "a_demo")
public class Demo extends BaseModule {

    private Integer number;
    private Double money;
    private String name;
    private Date time;


    public Integer getNumber() {
        return number;
    }

    @Column(precision = 8, scale = 2)
    public Double getMoney() {
        return money;
    }

    @Column(length = 30)
    public String getName() {
        return name;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getTime() {
        return time;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}