package by.itacademy.userservice.core.mappers;

import by.itacademy.userservice.core.dto.VerificationDTO;
import by.itacademy.userservice.dao.entity.VerificationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VerificationMapper {

    VerificationDTO verificationEntityToVerificationDTO(VerificationEntity verificationEntity);
}
