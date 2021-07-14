package demo.quanlysanpham;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuanlysanphamApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanlysanphamApplication.class, args);
    }

    @Bean("urlUtil")
    List<Integer> doSomthing(List<Integer> listNumber) {
//        List.addParameter("page", "0");
        return listNumber;
    }
}
