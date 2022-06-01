package com.stdio.esm.service;

import com.stdio.esm.exception.EsmException;
import com.stdio.esm.model.Account;
import com.stdio.esm.repository.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo ;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(String userName) {

        Account account = userRepo.findByUsername(userName)
                .orElseThrow(() -> new EsmException(EsmException.USER_NOT_FOUND));

        String newStringPassWord = RandomStringUtils.random(8, true, true);
        String newEncodePassWord = passwordEncoder.encode(newStringPassWord);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(account.getUsername());
        message.setSubject("[Stdio] Reset password successfully");
        message.setText("Please sign in and change this new password: " + newStringPassWord);
        mailSender.send(message);

        account.setPassword(newEncodePassWord);
    }
}
