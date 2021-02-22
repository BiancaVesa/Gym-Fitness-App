package service;

import entity.Client;
import entity.Membership;
import exception.CustomExceptionMessages;
import repository.MembershipRepo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MembershipService {
    private final Map<String, Double> membershipType;
    private MembershipRepo membershipRepo;


    public MembershipService() {
        this.membershipRepo = new MembershipRepo();
        this.membershipType = new HashMap<String, Double>() {{
            put("SimpleHour", 10.0);  // o sedinta e 10 lei
            put("Silver", 90.0);      // 10 sedinte + reducere 10%
            put("Gold", 170.0);       // 20 sedinte + reducere 15%
            put("Platinum", 230.0);   // nelimitat
        }};
    }

    public void insertMembership(Client client, String membershipType) {
        if (membershipRepo.findMembershipsByIdClient(client).isEmpty()) {
            Membership membership = new Membership(UUID.randomUUID().toString());
            membership.setStartDate(LocalDate.now());
            membership.setExpirationDate(LocalDate.now().plusMonths(1).minusDays(1));
            membership.setClient(client);
            membership.setMembershipType(membershipType);

            switch (membershipType) {
                case "Silver":
                    membership.setBookingsLeft(10);
                    break;
                case "Gold":
                    membership.setBookingsLeft(20);
                    break;
                case "Platinum":
                    membership.setBookingsLeft(100000);
                    break;
            }

            membershipRepo.insertNewMembership(membership);
        } else {
            throw new IllegalArgumentException(CustomExceptionMessages.EXISTING_MEMBERSHIP);
        }
    }

    public void deleteMembership(Membership membership) {
        membershipRepo.deleteMembership(membership);
    }

    public List<Membership> getMembershipByIdClient(Client client) {
        return membershipRepo.findMembershipsByIdClient(client);
    }

    public List<Membership> getMembershipByDate(LocalDate dateTime){
        return membershipRepo.findMembershipsByDate(dateTime);
    }

    public void updateNrOfBookings(Membership membership, int nrOfBookings) {
        membershipRepo.updateMembershipNrOfBookings(membership, nrOfBookings);
    }
}
