package by.itacademy.userservice.service;

import by.itacademy.userservice.core.exceptions.FindEntityException;
import by.itacademy.userservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.userservice.dao.entity.VerificationTokenEntity;
import by.itacademy.userservice.dao.repositories.IVerificationDao;
import by.itacademy.userservice.service.api.IVerificationTokenService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class VerificationTokenService implements IVerificationTokenService {
    private static final String WRONG_REQUEST_TOKEN = "Wrong token";
    private final IVerificationDao verificationDao;

    public VerificationTokenService(
            IVerificationDao verificationDao
    ) {
        this.verificationDao = verificationDao;
    }

    @Override
    public VerificationTokenEntity get(UUID token) {
        return verificationDao.findByToken(token)
                .orElseThrow(() -> new FindEntityException(WRONG_REQUEST_TOKEN));
    }

    @Override
    public VerificationTokenEntity save(VerificationTokenEntity token) {
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
    public void delete(UUID uuid) {
        verificationDao.deleteById(uuid);
    }
}
