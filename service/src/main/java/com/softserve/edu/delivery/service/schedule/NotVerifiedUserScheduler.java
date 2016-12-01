package com.softserve.edu.delivery.service.schedule;

import com.softserve.edu.delivery.domain.EmailVerificationToken;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.repository.EmailTokenRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotVerifiedUserScheduler {

    private static final long SCHEDULE_TIME_EXEC_DELAY = 25 * 60 * 60 * 1000;

    private static final long SCHEDULE_TIME_EXECUTION = 4 * 60 * 60 * 1000;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTokenRepository emailTokenRepository;


    @Scheduled(initialDelay = SCHEDULE_TIME_EXEC_DELAY, fixedRate = SCHEDULE_TIME_EXECUTION)
    public void removeNotVerifiedUsers() {
        List<EmailVerificationToken> expiredTokens = this.emailTokenRepository.findAllExpired();
        for (EmailVerificationToken token : expiredTokens) {
            User expiredUser = token.getUser();
            this.emailTokenRepository.delete(token);
            this.userRepository.delete(expiredUser);
        }
    }
}