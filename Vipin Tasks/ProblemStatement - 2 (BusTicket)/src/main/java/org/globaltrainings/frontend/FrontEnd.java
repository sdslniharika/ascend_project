package org.globaltrainings.frontend;

import org.globaltrainings.entity.Bus;
import org.globaltrainings.entity.Passenger;
import org.globaltrainings.entity.Ticket;
import org.globaltrainings.service.BookBus;
import org.globaltrainings.service.IBusService;
import org.globaltrainings.service.IPassengerService;
import org.globaltrainings.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class FrontEnd {
    @Autowired
    private IPassengerService passengerService;
    @Autowired
    private IBusService busService;
    @Autowired
    private ITicketService ticketService;
    @Autowired
    private BookBus bookBus;



    public void runUI(){
        try {
            System.out.println();
            System.out.println();
            System.out.println("----------------Buses----------------");
            Bus bus1 = busService.addBus(LocalDate.parse("2023-01-01"), LocalTime.parse("21:15:45"), LocalTime.parse("21:20:45"), "Hyderabad", "Kakinada", 1400);
            displayBus(bus1);

            Bus bus2 = busService.addBus(LocalDate.parse("2023-01-01"), LocalTime.parse("22:15:45"), LocalTime.parse("22:20:45"), "Kakinada", "Hyderabad", 1400);
            displayBus(bus2);
            System.out.println();

            System.out.println("----------------Passenger----------------");
            Passenger passenger1 = passengerService.registerPassenger("Rahul", "ANNDD1208B", 9848022338l, "U123");
            displayPassenger(passenger1);

            Passenger passenger2 = passengerService.registerPassenger("Vinod", "ANNDD1208B", 9959802455l, "B123");
            displayPassenger(passenger2);

            Passenger passenger3 = passengerService.registerPassenger("Vikas", "ANNDD1208B", 9618045773l, "E123");
            displayPassenger(passenger3);
            System.out.println();

            System.out.println("----------------Ticket Details----------------");
            String[] passengersForBooking = {"Rahul", "Vikas"};
            Ticket ticket = bookBus.bookBus("Hyderabad","Kakinada", passengersForBooking);
            displayTicket(ticket);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println("something went wrong, Please try again");
        }
    }


    void displayPassenger(Passenger passenger){
        System.out.println(passenger);
    }
    void displayBus(Bus bus){
        System.out.println(bus);
    }
    void displayTicket(Ticket ticket){
        System.out.println("Ticket Details :\n" +
                "\n" +
                "FROM : "+ticket.getBus().getFrom()+"\n" +
                "To : "+ticket.getBus().getTo()+"\n" +
                "Departure Date : "+ticket.getBus().getDateOfArrival()+"\n" +
                "Departure Time: "+ticket.getBus().getDepartureTime()+"\n" +
                "\n" +
                "List Of Passengers: "+ticket.getPassengerList()+"\n" +
                "\n" +
                "Total Amount : "+ticket.getTotalAmount()+"\n" +
                "\n" +
                "—--------------------------------------------------------------------------------------------------------------------------");
    }



}
