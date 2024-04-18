package by.itacademy.userservice.service;

import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.VerificationDTO;
import by.itacademy.userservice.core.exceptions.FindEntityException;
import by.itacademy.userservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.userservice.core.mappers.VerificationMapper;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.entity.VerificationEntity;
import by.itacademy.userservice.dao.repositories.IVerificationDao;
import by.itacademy.userservice.service.api.IVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static by.itacademy.userservice.core.util.Messages.WRONG_MAIL_RESPONSE;

@Service
@RequiredArgsConstructor
public class VerificationService implements IVerificationService {
    private final IVerificationDao verificationDao;
    private final VerificationMapper verificationMapper;

    @Override
    @Transactional(readOnly = true)
    public VerificationDTO get(String mail) {
        VerificationEntity entity = verificationDao.findByMail(mail)
                .orElseThrow(() -> new FindEntityException(WRONG_MAIL_RESPONSE));
        return verificationMapper.verificationEntityToVerificationDTO(entity);
    }

    @Override
    @Transactional
    public VerificationDTO save(UserDTO userDTO) {
        VerificationEntity verificationEntity = new VerificationEntity();
        verificationEntity.setUuid(UUID.randomUUID());
        verificationEntity.setMail(userDTO.getMail());

        try {
            verificationEntity = verificationDao.save(verificationEntity);
            return verificationMapper.verificationEntityToVerificationDTO(verificationEntity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        } catch (RuntimeException ex) {
            throw new RuntimeException (ex.getMessage(), ex);
        }
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        verificationDao.deleteById(uuid);
    }

    @Override
    @Transactional
    public void deleteByDtCreateLessThan(LocalDateTime dateTime) {
        verificationDao.deleteByDtCreateLessThan(dateTime);
    }
}
