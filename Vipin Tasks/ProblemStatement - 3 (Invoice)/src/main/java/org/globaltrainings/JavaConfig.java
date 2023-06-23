package org.globaltrainings;


import org.globaltrainings.entity.Invoice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("org.globaltrainings")
@Configuration
public class JavaConfig {
    @Bean
    public Invoice invoiceService() {
        return new Invoice();
    }
}
