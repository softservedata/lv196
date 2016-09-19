package com.softserve.edu.delivery.service.verification;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.dao.impl.UserDaoImpl;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.service.impl.UserServiceImpl;
import com.softserve.edu.delivery.utils.Jpa;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @author Petro Shtenovych
 *
 * The goal of this test is testing verificationLogin(UserAuthDTO user) method
 * from UserServiceImpl. Method should return true if given user with his credentials
 * exists in database, and return false if user isn't exist.
 *
 * */
public class VerificationLoginTest {

    private EntityManager em = Jpa.getEntityManager();

    private UserDao userDao = new UserDaoImpl(); //mock(UserDaoImpl.class);
    private UserService userService = new UserServiceImpl(userDao);

    //User entities for testing method
    private User user1 = new User().setEmail("user1@domain.com").setPassword("user1");
    private User user2 = new User().setEmail("user2@domain.com").setPassword("user2");
    private User user3 = new User().setEmail("user3@domain.com").setPassword("user3");
    private User user4 = new User().setEmail("user4@domain.com").setPassword("user4");
    private User user5 = new User().setEmail("user5@domain.com").setPassword("user5");

    //Legal credentials for testing method
    private UserAuthDTO userDTO1 = new UserAuthDTO(user1.getEmail(), user1.getPassword());
    private UserAuthDTO userDTO2 = new UserAuthDTO(user2.getEmail(), user2.getPassword());
    private UserAuthDTO userDTO3 = new UserAuthDTO(user3.getEmail(), user3.getPassword());
    private UserAuthDTO userDTO4 = new UserAuthDTO(user4.getEmail(), user4.getPassword());
    private UserAuthDTO userDTO5 = new UserAuthDTO(user5.getEmail(), user5.getPassword());

    //Wrong credentials
    private UserAuthDTO NULL = null; //expect IllegalArgumentException
    private UserAuthDTO UserDTOwrong2 = new UserAuthDTO(null, null);
    private UserAuthDTO UserDTOwrong3 = new UserAuthDTO(null, "Password");
    private UserAuthDTO UserDTOwrong4 = new UserAuthDTO("wrongUser@domain.com", null);
    private UserAuthDTO UserDTOwrong5 = new UserAuthDTO("notExist@domain.com", "notExistPassword");


    @BeforeClass
    void saveTestUserEntitiesToDatabase() {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            userDao.save(user1);
            userDao.save(user2);
            userDao.save(user3);
            userDao.save(user4);
            userDao.save(user5);
            tx.commit();
        }catch (RuntimeException exception) {
            if (tx != null) tx.rollback();
            throw new AssertionError("Test: Some problems occur when save test entities to db", exception);
        }
    }

    @Test
    void testLegalUserCredentials() {
            Assert.assertTrue(userService.verificationLogin(userDTO1));
            Assert.assertTrue(userService.verificationLogin(userDTO2));
            Assert.assertTrue(userService.verificationLogin(userDTO3));
            Assert.assertTrue(userService.verificationLogin(userDTO4));
            Assert.assertTrue(userService.verificationLogin(userDTO5));
    }

    @Test
    void testIllegalUserCredentials() {
            Assert.assertFalse(userService.verificationLogin(UserDTOwrong2));
            Assert.assertFalse(userService.verificationLogin(UserDTOwrong3));
            Assert.assertFalse(userService.verificationLogin(UserDTOwrong4));
            Assert.assertFalse(userService.verificationLogin(UserDTOwrong5));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testNullRefference() {
        userService.verificationLogin(NULL);
    }

    @AfterClass
    void removeTestEntitiesFromDatabase() {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            userDao.delete(user1); //Maybe user's entities should have persistence state before delete from db?
            userDao.delete(user2);
            userDao.delete(user3);
            userDao.delete(user4);
            userDao.delete(user5);
            tx.commit();
        }catch (Exception exception) {
            if (tx != null) tx.rollback();
            throw new AssertionError("Test: Some problems occur when delete test entities from db", exception);
        }finally {
            Jpa.close();
        }
    }
}
