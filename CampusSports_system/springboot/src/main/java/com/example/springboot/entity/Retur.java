package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Retur {
        /**
         * id
         */
        private Integer id;

        /**
         * 器材名称
         */
        private String sportsName;

        /**
         * 器材码
         */
        private String sportsNo;

        /**
         * 用户号
         */
        private String userNo;

        /**
         * 用户名称
         */
        private String userName;

        /**
         * 用户联系方式
         */
        private String userPhone;

        /**
         * createtime
         */
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        private LocalDate createtime;

        /**
         * updatetime
         */
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        private LocalDate updatetime;

        private String status;
        private Integer days;
        //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        private LocalDate returnDate;
        //提醒状态 即将到期（-1） 已到期（当天） 已过期（超过归还日期之后）
        private String note;
        //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        private LocalDate realDate;

    }

