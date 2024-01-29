
# Railway Reservation System

This Railway Reservation System is a Java application that allows users to book train tickets, cancel tickets, and retrieve information about available trains. The system uses JSON files to store and retrieve data.

## Classes

### 1. Passengers

- Represents information about passengers booking train tickets.
- Fields:
  - `choice`: Train choice
  - `name`: Passenger's name
  - `age`: Passenger's age
  - `BirthPreference`: Passenger's birth preference (L, U, M)
  - `passengerId`: Unique identifier for passengers
- Methods:
  - Getter and setter methods

### 2. BookTicketsViewModel

- Manages the booking of train tickets.
- Fields:
  - Static fields for the view and model
- Methods:
  - `bookTickets()`: Books tickets based on user input.
  - `isAvailableBirthPreference()`: Checks and books tickets based on birth preferences.
  - Other helper methods.

### 3. CancelTicketsViewModel

- Manages the cancellation of train tickets.
- Fields:
  - Static fields for the model and view
- Methods:
  - `CancelTicket()`: Cancels tickets based on passenger ID.
  - Other helper methods.

### 4. JSONRetreiverViewModel

- Retrieves and displays information about available trains.
- Fields:
  - Fields for model and view
  - Static field `stage` to keep track of the application stage
- Methods:
  - `retreiveJSONObject()`: Retrieves JSON object.
  - `showAllTrains()`: Displays information about available trains.
  - Other helper methods.

### 5. BookTicketsModel

- Handles data related to booking tickets.
- Methods:
  - `addPassengers()`: Adds passengers to the system.
  - Other helper methods.

### 6. CancelTicketsModel

- Handles data related to canceling tickets.
- Methods:
  - `gettingJSONObject()`: Retrieves JSON object.
  - `getPassengerStorage()`: Retrieves passenger storage set.

### 7. JSONRetreiverModel

- Handles data related to retrieving JSON information.
- Methods:
  - `getJSONObject()`: Retrieves JSON object.
  - `setJSONObject()`: Sets JSON object.
  - Other helper methods.

## Usage

- The application uses JSON files to store and retrieve data.
- Users can book, cancel tickets and retrieve train information.
- Various view models and models handle different aspects of the system.




```
+------------------------------------------------------------+
|                    RailwayReservationSystem                |
+------------------------------------------------------------+
| - dto                                                      |
|   - Passengers                                             |
| - viewmodel                                                |
|   - booktickets                                            |
|     - BookTicketsViewModel                                 |
|   - canceltickets                                          |
|     - CancelTicketsViewModel                               |
|   - jsonretreive                                           |
|     - JSONRetreiverViewModel                               |
| - model                                                    |
|   - booktickets                                            |
|     - BookTicketsModel                                     |
|   - canceltickets                                          |
|     - CancelTicketsModel                                   |
|   - jsonretreive                                           |
|     - JSONRetreiverModel                                   |
| - repository                                               |
|   - RailwayRepository                                      |
+------------------------------------------------------------+
```