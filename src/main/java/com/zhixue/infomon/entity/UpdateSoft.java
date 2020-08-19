package com.zhixue.infomon.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@DynamicUpdate
@DynamicInsert
@Entity
public class UpdateSoft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    String softId;

    String softName;

    String downloadUrl;

    String softSize;

    String softMd5;

    String softType;

    Date updateTime;
    public UpdateSoft() {}
    public static class Builder{

        String softId;

        String softName;

        String downloadUrl;

        String softSize;

        String softMd5;

        String softType;

        Date updateTime;

        public  Builder(String softType){
            this.softId=UUID.randomUUID().toString().replace("-","");
            this.updateTime=new Date();
            this.softType=softType;
        }
        public Builder softName(String val){
            softName=val;
            return this;
        }
        public Builder downloadUrl(String val){
            downloadUrl=val;
            return this;
        }
        public Builder softSize(String val){
            softSize=val;
            return this;
        }
        public Builder softMd5(String val){
            softMd5=val;
            return this;
        }
        public UpdateSoft build(){
            return new UpdateSoft(this);
        }
    }

    private UpdateSoft(Builder builder){
        softId=builder.softId;
        softName=builder.softName;
        downloadUrl=builder.downloadUrl;
        softSize=builder.softSize;
        softMd5=builder.softMd5;
        softType=builder.softType;
        updateTime=builder.updateTime;
    }


}
