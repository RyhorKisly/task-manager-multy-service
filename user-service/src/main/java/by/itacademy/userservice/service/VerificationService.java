package by.itacademy.userservice.service;

import by.itacademy.userservice.core.exceptions.FindEntityException;
import by.itacademy.userservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.entity.VerificationEntity;
import by.itacademy.userservice.dao.repositories.IVerificationDao;
import by.itacademy.userservice.service.api.IVerificationService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class VerificationService implements IVerificationService {
    private static final String WRONG_MAIL_RESPONSE = "Wrong mail";
    private final IVerificationDao verificationDao;

    public VerificationService(
            IVerificationDao verificationDao
    ) {
        this.verificationDao = verificationDao;
    }

    @Override
    @Transactional(readOnly = true)
    public VerificationEntity get(String mail) {
        return verificationDao.findByMail(mail)
                .orElseThrow(() -> new FindEntityException(WRONG_MAIL_RESPONSE));
    }

    @Override
    @Transactional
    public VerificationEntity save(UserEntity item) {
        VerificationEntity token = new VerificationEntity();
        token.setUuid(UUID.randomUUID());
        token.setMail(item.getMail());

        try {
            verificationDao.save(token);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        } catch (RuntimeException ex) {
            throw new RuntimeException (ex.getMessage(), ex);
        }

        return token;
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
