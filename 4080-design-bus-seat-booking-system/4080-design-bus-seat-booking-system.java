import java.util.HashMap;
import java.util.Map;

class BusBooking {
    // Map to store the cost assigned to each booked seat
    private Map<String, Integer> bookedSeats;
    private int totalCost;
    private int bookedCount;

    public BusBooking(int n) {
        this.bookedSeats = new HashMap<>();
        this.totalCost = 0;
        this.bookedCount = 0;
    }
    
    public void toggle(String seat) {
        if (bookedSeats.containsKey(seat)) {
            // Free the seat
            int cost = bookedSeats.remove(seat);
            totalCost -= cost;
            bookedCount--;
        } else {
            // Book the seat
            char col = seat.charAt(0);
            int row = Integer.parseInt(seat.substring(1));
            
            int cost = 1;
            if (col == 'A') {
                String adjacent = "B" + row;
                if (bookedSeats.containsKey(adjacent)) {
                    cost = 3;
                }
            } else if (col == 'D') {
                String adjacent = "C" + row;
                if (bookedSeats.containsKey(adjacent)) {
                    cost = 3;
                }
            }
            
            bookedSeats.put(seat, cost);
            totalCost += cost;
            bookedCount++;
        }
    }
    
    public int getTotalTime() {
        return totalCost;
    }
    
    public int getMinTime() {
        return bookedCount;
    }
}