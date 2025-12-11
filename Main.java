
package com.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FlightService service = new FlightService();
        service.addFlight(new Flight("AA101", "New York", LocalDateTime.of(2025, 1, 10, 14, 30), 50));
        service.addFlight(new Flight("AA202", "Chicago", LocalDateTime.of(2025, 1, 10, 16, 45), 30));
        service.addFlight(new Flight("AA303", "New York", LocalDateTime.of(2025, 1, 10, 18, 00), 20));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Search Flights\n2. Book Flight\n3. View Reservations\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Destination: ");
                String dest = sc.nextLine();
                System.out.print("Date (YYYY-MM-DD): ");
                String d = sc.nextLine();
                LocalDateTime dt = LocalDateTime.parse(d + "T00:00");

                List<Flight> flights = service.searchFlights(dest, dt);
                if (flights.isEmpty()) System.out.println("No flights found.");
                else flights.forEach(System.out::println);

            } else if (choice == 2) {
                System.out.print("Your Name: ");
                String name = sc.nextLine();
                System.out.print("Flight Number: ");
                String fno = sc.nextLine();
                System.out.print("Seats: ");
                int seats = sc.nextInt();
                sc.nextLine();

                Flight flight = service.searchFlights("", LocalDateTime.now())
                        .stream().filter(f -> f.getFlightNumber().equals(fno)).findFirst().orElse(null);

                if (flight == null) {
                    System.out.println("Flight not found.");
                } else {
                    try {
                        Reservation r = service.bookFlight(name, flight, seats);
                        System.out.println("Booked: " + r);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

            } else if (choice == 3) {
                System.out.print("Your Name: ");
                String name = sc.nextLine();
                service.getReservationsForCustomer(name).forEach(System.out::println);

            } else {
                break;
            }
        }
    }
}
