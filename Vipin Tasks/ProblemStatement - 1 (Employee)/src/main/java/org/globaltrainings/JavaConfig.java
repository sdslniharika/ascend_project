package org.globaltrainings;


import org.globaltrainings.dao.EmployeeDaoImpl;
import org.globaltrainings.entity.Address;
import org.globaltrainings.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("org.globaltrainings")
@Configuration
public class JavaConfig {
    @Bean
    public Employee employee(){
        return new Employee();
    }

    @Bean
    public Address address(){
        return new Address();
    }

    @Bean
    public EmployeeDaoImpl employeeDao(){
        return new EmployeeDaoImpl();
    }
}
