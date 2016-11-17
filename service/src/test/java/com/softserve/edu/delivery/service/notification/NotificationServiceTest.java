package com.softserve.edu.delivery.service.notification;

import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.repository.NotificationRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.service.impl.NotificationServiceImpl;
import org.mockito.*;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.thymeleaf.TemplateEngine;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class NotificationServiceTest {

    @Mock
    NotificationRepository notificationRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    Environment env;
    @Mock
    TemplateEngine templateEngine;
    @Mock
    JavaMailSender mailSender;
    @InjectMocks
    NotificationServiceImpl notificationService;
    @Captor
    ArgumentCaptor<Notification> notificationCaptor;

    private final String email = "false@gmail.com";

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void mockDaos() {
        doReturn(null).when(notificationRepository).save(any(Notification.class));
        when(userRepository.findOneOpt(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.findOneOpt(email)).thenReturn(Optional.of(new User().setEmail(email)));
    }

    @Test
    public void addNotification() {
        notificationService.addNotification(NotificationStatus.WARNING, "message", email );

        verify(notificationRepository, times(1)).save(notificationCaptor.capture());

        Notification captured = notificationCaptor.getValue();

        Assert.assertEquals(captured.getNotificationStatus(), NotificationStatus.WARNING);
        Assert.assertEquals(captured.getMessage(), "message");
        Assert.assertEquals(captured.getUser().getEmail(), email);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addOrderDtoNull() {
        notificationService.addNotification(null, email, null);
    }
}
