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

import static org.mockito.Mockito.*;

/**
 * @author Petro Shtenovych
 *
 * The goal of this test is testing verificationLogin(UserAuthDTO user) method
 * from UserServiceImpl. Method should return true if given user with his credentials
 * exists in database, and return false if user isn't exist.
 *
 * */
public class VerificationLoginTest {

    private EntityManager em;


    private UserDao userMockDao = mock(UserDaoImpl.class);
    private UserService userService = new UserServiceImpl(userMockDao);

    //User entities for testing method
    User user1 = new User().setEmail("user1@domain.com").setPassport("user1");
    User user2 = new User().setEmail("user2@domain.com").setPassport("user2");
    User user3 = new User().setEmail("user3@domain.com").setPassport("user3");
    User user4 = new User().setEmail("user4@domain.com").setPassport("user4");
    User user5 = new User().setEmail("user5@domain.com").setPassport("user5");

    //Legal credentials for testing method
    UserAuthDTO userDTO1 = new UserAuthDTO(user1.getEmail(), user1.getPassword());
    UserAuthDTO userDTO2 = new UserAuthDTO(user2.getEmail(), user2.getPassword());
    UserAuthDTO userDTO3 = new UserAuthDTO(user3.getEmail(), user3.getPassword());
    UserAuthDTO userDTO4 = new UserAuthDTO(user4.getEmail(), user4.getPassword());
    UserAuthDTO userDTO5 = new UserAuthDTO(user5.getEmail(), user5.getPassword());

    //Wrong credentials
    UserAuthDTO NULL = null; //expect IllegalArgumentException
    UserAuthDTO UserDTOwrong2 = new UserAuthDTO(null, null);
    UserAuthDTO UserDTOwrong3 = new UserAuthDTO(null, "Password");
    UserAuthDTO UserDTOwrong4 = new UserAuthDTO("wrongUser@domain.com", null);
    UserAuthDTO UserDTOwrong5 = new UserAuthDTO("notExist@domain.com", "notExistPassword");

    @BeforeClass
    void initDatabaseProvider() {
        em = Jpa.getEntityManager();
    }


    @BeforeClass
    void saveTestUserEntitiesToDatabase() {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            userMockDao.save(user1);
            userMockDao.save(user2);
            userMockDao.save(user3);
            userMockDao.save(user4);
            userMockDao.save(user5);
            tx.commit();
        }catch (RuntimeException exception) {
            if (tx != null) tx.rollback();
            throw new AssertionError("Test: Some problems occur when save test entities to db", exception);
        }
    }

    @Test
    void testLegalUserCredentials() {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Assert.assertTrue(userService.verificationLogin(userDTO1));
            Assert.assertTrue(userService.verificationLogin(userDTO2));
            Assert.assertTrue(userService.verificationLogin(userDTO3));
            Assert.assertTrue(userService.verificationLogin(userDTO4));
            Assert.assertTrue(userService.verificationLogin(userDTO5));
            tx.commit();
        }catch (RuntimeException exception) {
            if (tx != null) tx.rollback();
            throw new AssertionError("Test: Some problems occur when read from db", exception);
        }

    }

    @Test
    void testIllegalUserCredentials() {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Assert.assertFalse(userService.verificationLogin(UserDTOwrong2));
            Assert.assertFalse(userService.verificationLogin(UserDTOwrong3));
            Assert.assertFalse(userService.verificationLogin(UserDTOwrong4));
            Assert.assertFalse(userService.verificationLogin(UserDTOwrong5));
            tx.commit();
        }catch (RuntimeException exception) {
            if (tx != null) tx.rollback();
            throw new AssertionError("Test: Some problems occur when read from db", exception);
        }
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
            userMockDao.delete(user1); //Maybe user's entities should have persistence state before delete from db?
            userMockDao.delete(user2);
            userMockDao.delete(user3);
            userMockDao.delete(user4);
            userMockDao.delete(user5);
            tx.commit();
        }catch (Exception exception) {
            if (tx != null) tx.rollback();
            throw new AssertionError("Test: Some problems occur when delete test entities from db", exception);
        }
    }

    @AfterClass
    void closeDatabaseProvider() {
        Jpa.close();
    }
}
