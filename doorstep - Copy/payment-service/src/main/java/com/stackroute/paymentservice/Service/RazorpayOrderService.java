package com.stackroute.paymentservice.Service;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.paymentservice.Model.PaymentModel;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RazorpayOrderService {

    public String newRazorpayOrder(PaymentModel paymentModel) throws RazorpayException {
        float totPrice= (float) (Math.round(paymentModel.getTotalPrice()*100)/100);

        RazorpayClient razorpay;
        razorpay = new RazorpayClient("rzp_test_izF9jhDmyI6wH5", "XmROe0Bzjll5kyfGF8Yzlk3D"); //razorpay client credentials
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", totPrice*100); // amount in INR
        orderRequest.put("currency", "INR");

        String receiptId= String.valueOf(paymentModel.getPaymentId());

        orderRequest.put("receipt", receiptId);

        Order order = razorpay.orders.create(orderRequest);

        return (String) order.get("id");
    }
}


