package mappers;

import dto.ManagerDto;
import entity.Manager;

public class ManagerMapper {

    public static ManagerDto managerToDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setName(manager.getName());
        managerDto.setEmail(manager.getEmail());
        managerDto.setPhoneNr(manager.getPhoneNr());

        return managerDto;
    }
}
