package org.globaltrainings;

import org.globaltrainings.entity.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationLaunch {
    public static void main(String args[]){
        ApplicationContext context1= new ClassPathXmlApplicationContext("config.xml");
        Employee employee=(Employee)context1.getBean("employee");

        System.out.println( "Employee details are = "+employee );

    }
}
