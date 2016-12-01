package com.softserve.edu.delivery.service.verification;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.dao.impl.UserDaoImpl;
import com.softserve.edu.delivery.domain.Role;
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
 * exists in database, and return false if user isn't exist. Method also throws
 * IllegalArgumentException if get null
 *
 * */
public class VerificationLoginTest {

    private EntityManager em = Jpa.getEntityManager();

    private UserDao userDao = new UserDaoImpl(); //mock(UserDaoImpl.class);
    private UserService userService = new UserServiceImpl(userDao);

    //User entity for testing
    private User testUserEntity = new User();

    {
        testUserEntity.setApproved(true);
        testUserEntity.setUserRole(Role.ADMIN);
        testUserEntity.setBlocked(false);
        testUserEntity.setEmail("shtenyovych@live.com");
        testUserEntity.setPassword("Example123");
        testUserEntity.setPassport("CE12345");
        testUserEntity.setFirstName("Petro");
        testUserEntity.setLastName("Shtenovych");
        testUserEntity.setPhone("+38(050)-123-45-67");
    }

    //Legal credentials for testing method
    private UserAuthDTO legalUserDTO = new UserAuthDTO(testUserEntity.getEmail(), testUserEntity.getPassword());

    //Wrong credentials
    /*@DataProvider(name = "WrongCredentialsDataProvider")*/
    public Object [][] wrongUserDTOCredentialsDataProvider() {
        return new Object[][] {
                /*{"WrongUserDTO_1", new UserAuthDTO(null, null)},
                {"WrongUserDTO_2", new UserAuthDTO(null, "Password")},
                {"WrongUserDTO_3", new UserAuthDTO("wrongUser@domain.com", null)},
                {"WrongUserDTO_4", new UserAuthDTO("wrongUser@domain.com", "WrongPassword")},*/
        };
    }

    //Save to database test user entity
    @BeforeClass
    void saveTestUserEntitiesToDatabase() {
       /* EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            userDao.save(testUserEntity);
            tx.commit();
        }catch (RuntimeException exception) {
            if (tx != null) tx.rollback();
            throw new AssertionError("Test: Some problems occur when save test entities to db", exception);
        }*/
    }

    //Legal credentials testing. Return true - user exists in database
    @Test
    void testLegalUserCredentials() {
           /* Assert.assertTrue(userService.verificationLogin(legalUserDTO));*/
    }

    //Wrong credentials. Return false - user isn't exist in database
    @Test/*(dataProvider = "WrongCredentialsDataProvider")*/
    void testIllegalUserCredentials(String s, UserAuthDTO wrongDTO) {
       /* Assert.assertFalse(userService.verificationLogin(wrongDTO));*/
    }

    //Expect IllegalArgumentException if method gets null.
    @Test/*(expectedExceptions = IllegalArgumentException.class)*/
    void testNullReference() {
        /*userService.verificationLogin(null);*/
    }

    //Remove test entity from database
    @AfterClass
    void removeTestEntitiesFromDatabase() {
        /*EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            userDao.delete(testUserEntity);
            tx.commit();
        }catch (Exception exception) {
            if (tx != null) tx.rollback();
            throw new AssertionError("Test: Some problems occur when delete test entities from db", exception);
        }finally {
            Jpa.close();
        }*/
    }
}
