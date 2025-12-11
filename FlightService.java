
package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private List<Flight> flights = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public List<Flight> searchFlights(String destination, LocalDateTime date) {
        List<Flight> result = new ArrayList<>();
        for (Flight f : flights) {
            if (f.getDestination().equalsIgnoreCase(destination)
                    && f.getDepartureTime().toLocalDate().equals(date.toLocalDate())) {
                result.add(f);
            }
        }
        return result;
    }

    public Reservation bookFlight(String customerName, Flight flight, int seats) {
        if (flight.getAvailableSeats() < seats) {
            throw new IllegalArgumentException("Not enough seats available.");
        }
        flight.setAvailableSeats(flight.getAvailableSeats() - seats);
        Reservation r = new Reservation(customerName, flight, seats);
        reservations.add(r);
        return r;
    }

    public List<Reservation> getReservationsForCustomer(String customerName) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getCustomerName().equalsIgnoreCase(customerName)) {
                result.add(r);
            }
        }
        return result;
    }
}
