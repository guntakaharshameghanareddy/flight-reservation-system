package com.example.flight;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest {

    @Test
    public void testSearchFlights() {
        FlightService service = new FlightService();
        service.addFlight(new Flight("A101", "Boston",
                LocalDateTime.of(2025, 1, 10, 10, 0), 5));

        List<Flight> results = service.searchFlights(
                "Boston",
                LocalDateTime.of(2025, 1, 10, 0, 0)
        );

        assertEquals(1, results.size());
    }

    @Test
    public void testBookFlightSuccess() {
        Flight flight = new Flight("A101", "Boston", LocalDateTime.now(), 5);
        FlightService service = new FlightService();
        service.addFlight(flight);

        Reservation reservation = service.bookFlight("John", flight, 2);

        assertNotNull(reservation);
        assertEquals(3, flight.getAvailableSeats());
    }

    @Test
    public void testBookFlightNotEnoughSeats() {
        Flight flight = new Flight("A101", "Boston", LocalDateTime.now(), 2);
        FlightService service = new FlightService();
        service.addFlight(flight);

        Reservation reservation = service.bookFlight("John", flight, 3);

        assertNull(reservation);
        assertEquals(2, flight.getAvailableSeats());
    }
}
