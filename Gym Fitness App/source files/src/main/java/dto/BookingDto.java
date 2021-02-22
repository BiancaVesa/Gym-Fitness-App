package dto;

import java.time.LocalDateTime;

public class BookingDto {

    private String bookedTraining;

    private String client;

    private String membershipType;

    private LocalDateTime timestamp;

    public BookingDto(){}

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getBookedTraining() {
        return bookedTraining;
    }

    public void setBookedTraining(String bookedTraining) {
        this.bookedTraining = bookedTraining;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String memmberhipType) {
        this.membershipType = memmberhipType;
    }
}
