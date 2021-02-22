package mappers;


import dto.BookingDto;
import entity.Booking;

public class BookingMapper {

    public static BookingDto entityToDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookedTraining(booking.getBookedTraining().getClassName());
        bookingDto.setTimestamp(booking.getBookedTraining().getTemp());
        bookingDto.setClient(booking.getClient().getProfile().getName());
        bookingDto.setMembershipType(booking.getMembership().getMembershipType());
        return bookingDto;
    }

    public static Booking dtoToEntity(BookingDto bookingDto) {
        return null;
    }
}
