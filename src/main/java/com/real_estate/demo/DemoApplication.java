package com.real_estate.demo;

import com.real_estate.demo.domain.enums.SaleStatus;
import com.real_estate.demo.domain.enums.Type;
import com.real_estate.demo.dto.SaleSaveRequest;
import com.real_estate.demo.service.SalesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@EnableJpaAuditing
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
//
//    @Bean
//    public CommandLineRunner runner(SalesService salesService) throws Exception {
//        return(args)->{
//            SaleSaveRequest saleSaveRequest1 = new SaleSaveRequest(
//                   LocalDate.parse("2021-03-01", DateTimeFormatter.ISO_DATE), SaleStatus.SALE, Type.TRADE, null ,13.0,12.0,null,null,"집이 오래됨",
//                    "래미안아파트",1111,1020,34,"남",3000.0,"에어컨 세탁기","수원 무슨구 무슨동",
//                    "김한비", "집주인","010-123-1234"
//            );
//            SaleSaveRequest saleSaveRequest2 = new SaleSaveRequest(
//                    LocalDate.parse("2021-03-01", DateTimeFormatter.ISO_DATE), SaleStatus.SALE, Type.JEONSE, null ,8.0,12.0,null,null,"집이 오래됨",
//                    "래미안아파트",1111,1020,34,"남",3000.0,"에어컨 세탁기","수원 무슨구 무슨동",
//                    "김한비", "집주인","010-123-1234"
//            );
//            SaleSaveRequest saleSaveRequest3 = new SaleSaveRequest(
//                    LocalDate.parse("2021-03-01", DateTimeFormatter.ISO_DATE), SaleStatus.SALE, Type.TRADE, null ,15.4,8.0,null,null,"집 올수리",
//                    "자이아파트",501,1123,45,"북",4.5,"에어컨 세탁기","수원 무슨구 무슨동",
//                    "이주현", "집주인","010-5555-1434"
//            );
//            SaleSaveRequest saleSaveRequest4 = new SaleSaveRequest(
//                    LocalDate.parse("2021-03-01", DateTimeFormatter.ISO_DATE), SaleStatus.SALE, Type.MONTHLY, 5.5 ,300.0,3000.0,null,null,"월세 가격 협의 가능",
//                    "에이비씨원룸",103,201,25,"남",5.0,"에어컨 세탁기","수원 무슨구 무슨동",
//                    "김땡땡", "집주인, 부인이 관리","010-4444-8799"
//            );
//            salesService.save(saleSaveRequest1);
//            salesService.save(saleSaveRequest2);
//            salesService.save(saleSaveRequest3);
//            salesService.save(saleSaveRequest4);
//        };
//    }
}
