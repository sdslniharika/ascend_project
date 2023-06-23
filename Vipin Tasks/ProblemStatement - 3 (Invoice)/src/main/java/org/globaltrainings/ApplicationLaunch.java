package org.globaltrainings;

import org.globaltrainings.entity.Invoice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationLaunch {
    public static void main(String args[]){
        ApplicationContext context=new AnnotationConfigApplicationContext(JavaConfig.class);
        Invoice invoice=context.getBean(Invoice.class);

        invoice.setPartNumber("1");
        invoice.setPartDescription("ABCDEFG");
        invoice.setQuantity(5);
        invoice.setPricePerItem(100);

        System.out.println("The details of Invoice are as follows : "+invoice);

        double amount = invoice.getInvoiceAmount();
        System.out.println("The Invoice Amount as asked is : "+amount);

    }
}
