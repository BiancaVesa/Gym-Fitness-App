package service;

import dto.BookingDto;
import dto.ClientProfileDto;
import dto.ScheduleDto;
import entity.*;
import exception.CustomExceptionMessages;
import mappers.BookingMapper;
import mappers.ScheduleMapper;
import repository.ClientRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private ClientRepo clientRepo;
    private ProfileService profileService;
    private LoginService loginService;
    private BookingService bookingService;
    private ScheduleService scheduleService;
    private MembershipService membershipService;
    private RatingService ratingService;

    public ClientService() {
        this.clientRepo = new ClientRepo();
        this.profileService = new ProfileService();
        this.loginService = new LoginService();
        this.bookingService = new BookingService();
        this.scheduleService = new ScheduleService();
        this.membershipService = new MembershipService();
        this.ratingService = new RatingService();
    }

    public Client getClient(String idClient) {
        return clientRepo.findClientById(idClient);
    }

    //permite clientului sa isi vizualizeze profilul
    public ClientProfileDto viewProfile(String idClient) {
        return profileService.getClientProfile(getClient(idClient).getIdProfile());
    }

    //permite clientului sa isi modifice data nasterii
    public void modifyBirthday(String idClient, String newBirthday) {
        profileService.modifyBirthday(getClient(idClient).getIdProfile(), newBirthday);
    }

    //permite clientului sa isi modifice numele
    public void modifyName(String idClient, String newName) {
        profileService.modifyName(getClient(idClient).getIdProfile(), newName);
    }

    //permite clientului sa isi modifice username-ul
    public void modifyUserName(String idClient, String newUserName) {
        loginService.modifyUserName(getClient(idClient).getIdClientCredentials(), newUserName);
    }

    //permite clientului sa isi modifice parola
    public void modifyPassword(String idClient, String newPassword) {
        loginService.modifyPassword(getClient(idClient).getIdClientCredentials(), newPassword);
    }

    //permite clientului sa isi faca un booking (obiectul Schedule este afisat intr-un dropdown list)
    public void createBooking(String idClient, Schedule schedule) {
        Client client = getClient(idClient);
        Membership membership = client.getMembership();
        if (membership != null) {
            if (membership.getExpirationDate().isAfter(LocalDate.now())) {
                if (membership.getBookingsLeft() != 0) {
                    bookingService.insertBooking(client, schedule, membership);
                    membershipService.updateNrOfBookings(membership, membership.getBookingsLeft() - 1);
                } else {
                    throw new IllegalArgumentException(CustomExceptionMessages.ZERO_BOOKINGS_LEFT);
                }
            } else {
                throw new IllegalArgumentException(CustomExceptionMessages.MEMBERSHIP_EXPIRED);
            }
        } else {
            throw new IllegalArgumentException(CustomExceptionMessages.NO_MEMBERSHIP);
        }
    }

    //returneaza toate clasele la care poate participa clientul, dupa data curenta(la care s-a facut query pe Schedule)
    //le afiseaza sub forma de dto (dto ar putea sa contina doar nume clasa, timestamp si nume trainer)
    public List<ScheduleDto> viewAvailableClassesDto() {
        List<Schedule> allScheduleClasses = scheduleService.getScheduleByDate();
        List<ScheduleDto> allScheduleDto = new ArrayList<ScheduleDto>();
        for (Schedule s : allScheduleClasses) {
            allScheduleDto.add(ScheduleMapper.entityToDto(s));
        }

        return allScheduleDto;
    }

    public List<Schedule> viewAvailableClasses(String idClient) {
        List<Booking> bookings = viewAllBookings(idClient);
        List<Schedule> futureClasses = scheduleService.getScheduleByDate();
        List<Schedule> availableClasses = new ArrayList<>();
        for (Schedule schedule : futureClasses) {
            availableClasses.add(schedule);
            for (Booking booking : bookings) {
                if (booking.getBookedTraining().getIdClass().equals(schedule.getIdClass())) {
                    availableClasses.remove(schedule);
                }
            }
        }
        return availableClasses;
    }

    //permite clientului sa isi creeze un membership
    public void createMembership(String idClient, String membershipType) {
        List<Membership> membership = membershipService.getMembershipByIdClient(getClient(idClient));
        if (membership.size() == 0) {
            membershipService.insertMembership(getClient(idClient), membershipType);
        } else {
            throw new IllegalArgumentException(CustomExceptionMessages.EXISTING_MEMBERSHIP);
        }
    }

    //returneaza toate booking-urile facute de un anumit client
    public List<Booking> viewAllBookings(String idClient) {
        return bookingService.getBookingsByClientId(getClient(idClient));
    }

    public List<BookingDto> viewAllBookingsDto(String idClient) {
        List<Booking> allBookings = bookingService.getBookingsByClientId(getClient(idClient));
        List<BookingDto> allBookingsDto = new ArrayList<BookingDto>();
        for (Booking b : allBookings) {
            allBookingsDto.add(BookingMapper.entityToDto(b));
        }
        return allBookingsDto;
    }

    //permite clientului sa anuleze un booking deja creat
    public void cancelBooking(Booking booking) {
        bookingService.deleteBooking(booking);
        Membership membership = booking.getClient().getMembership();
        membershipService.updateNrOfBookings(membership, membership.getBookingsLeft() + 1);
    }

    public void rateTrainer(String idClient, float rating, Trainer trainer) {
        ratingService.insertRating(rating, idClient, trainer);
        float g1 = ratingService.countTrainerRatingsByGrade(1, trainer);
        float g2 = ratingService.countTrainerRatingsByGrade(2, trainer) * 2;
        float g3 = ratingService.countTrainerRatingsByGrade(3, trainer) * 3;
        float g4 = ratingService.countTrainerRatingsByGrade(4, trainer) * 4;
        float g5 = ratingService.countTrainerRatingsByGrade(5, trainer) * 5;
        float r = ratingService.countTrainerRatings(trainer);
        profileService.modifyRating(trainer.getIdProfile(), (g1 + g2 + g3 + g4 + g5) / r);
    }

    public List<Trainer> viewAllTrainersToRate(String idClient) {
        List<Booking> bookings = viewAllBookings(idClient);
        List<Trainer> trainersToRate = new ArrayList<>();

        // daca clientul nu a patricipat inca la clasa la care si-a facut booking
        bookings.removeIf(booking -> booking.getBookedTraining().getTemp().isAfter(LocalDateTime.now()));

        for (Booking booking : bookings) {
            try {
                ratingService.getRatingOfClientToTrainer(idClient, booking.getBookedTraining().getTrainer());
            } catch (javax.persistence.NoResultException e) { // clientul nu a dat inca rating trainer-ului
                trainersToRate.add(booking.getBookedTraining().getTrainer());
            }
        }

        return trainersToRate;
    }
}
