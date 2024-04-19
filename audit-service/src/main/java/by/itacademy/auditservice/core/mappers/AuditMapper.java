package by.itacademy.auditservice.core.mappers;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import by.itacademy.auditservice.dao.entity.UserEntity;
import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    UserEntity userEntityToUserShortDTO(UserShortDTO userShortDTO);
    UserShortDTO userShortDTOToUserEntity(UserEntity userEntity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "dtCreate", ignore = true)
    @Mapping(target = "user", source = "userShortDTO")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "id", source = "id")
    AuditEntity auditCreateDTOToAuditEntity(AuditCreateDTO auditCreateDTO);

    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "dtCreate", expression = "java(formatData(auditEntity.getDtCreate()))")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "id", source = "id")
    AuditDTO auditEntityToAuditDTO(AuditEntity auditEntity);

    default Long formatData(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
