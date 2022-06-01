package com.stdio.esm.service;

import com.stdio.esm.config.service.EsmUserDetail;
import com.stdio.esm.exception.EsmException;
import com.stdio.esm.model.Account;
import com.stdio.esm.repository.AccountRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    /**
     * Change the password of the person who is logged in
     *
     * @param request (old password, new password, confirm password) {@link Map<String,String>}
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void changePassword(Map<String, String> request) {
        LOGGER.info("Change password");

        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        String confirmNewPassword = request.get("confirmNewPassword");

        if (!newPassword.equals(confirmNewPassword)) {
            LOGGER.trace("New password and new confirm password not correct");
            throw new EsmException(EsmException.NOT_PASSWORD_MATCH);
        }
        EsmUserDetail esmUserDetail = getCurrentAccountOrElseThrow();
        Account account = accountRepo.findByUsernameIgnoreCase(esmUserDetail.getUsername()).get();
        LOGGER.info("{} change password", account.getUsername());
        if (!passwordEncoder.matches(oldPassword, account.getPassword())) {
            throw new EsmException(EsmException.INCORRECT_PASSWORD);
        }

        LOGGER.info("change password successfully");
        account.setPassword(passwordEncoder.encode(newPassword));
    }

    /**
     * Get current login user from securityContext
     *
     * @return {@link EsmUserDetail}
     */
    private static Optional<EsmUserDetail> getCurrentAccountLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication()).map(authentication -> {
            if (authentication.getPrincipal() instanceof EsmUserDetail) {
                return (EsmUserDetail) authentication.getPrincipal();
            }
            return null;
        });
    }

    /**
     * Returns the current user if any, otherwise returns null
     *
     * @return {@link EsmUserDetail}
     */
    public static EsmUserDetail getCurrentAccountOrElseThrow() {
        return getCurrentAccountLogin().orElseThrow(() -> new EsmException(EsmException.USER_NOT_FOUND));
    }

}
