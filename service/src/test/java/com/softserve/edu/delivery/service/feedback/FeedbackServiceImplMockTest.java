package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.FeedbackFilter;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.repository.FeedbackRepository;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.repository.impl.FeedbackRepositoryCustomImpl;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import org.mockito.Mockito;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

@Test
public class FeedbackServiceImplMockTest extends AbstractTestNGSpringContextTests {

    private final Long FEEDBACK_ID = 1L;
    private final Long DELETED_FEEDBACK_ID = 10000000L;
    private final int COUNT = 2;
    private final String APPROVED_DRIVER_NAME = "Approved Driver";
    private final String APPROVED_DRIVER_EMAIL = "email0@gmail.com";

    private final FeedbackRepository mockFeedbackRepo = Mockito.mock(com.softserve.edu.delivery.repository.FeedbackRepository.class);
    private final FeedbackRepositoryCustomImpl mockFeedbackCustomRepo = Mockito.mock(
            com.softserve.edu.delivery.repository.impl.FeedbackRepositoryCustomImpl.class);
    private final UserRepository mockUserRepo = Mockito.mock(com.softserve.edu.delivery.repository.UserRepository.class);
    private final OrderRepository mockOrderRepo = Mockito.mock(com.softserve.edu.delivery.repository.OrderRepository.class);

    private FeedbackServiceImplTest feedbackServiceImplTest;
    private FeedbackService feedbackService;

    private FeedbackDto feedbackDto;
    private Feedback feedback;
    private Optional<Feedback> oFeedback;

    @Override
    @BeforeSuite
    protected void springTestContextPrepareTestInstance() throws Exception {
        super.springTestContextPrepareTestInstance();
    }

    @BeforeClass
    void injectMockDAO() {
        feedbackService = new FeedbackServiceImpl(mockFeedbackRepo, mockFeedbackCustomRepo, mockUserRepo, mockOrderRepo);
        feedbackServiceImplTest = new FeedbackServiceImplTest(feedbackService);

        feedbackDto = feedbackServiceImplTest.createMockFeedbackDTO();
        feedback = feedbackServiceImplTest.createMockFeedback();
        oFeedback = Optional.of(feedbackServiceImplTest.createMockFeedback());
    }

    @BeforeMethod
    void setUp() {
        User mockUser = feedbackServiceImplTest.createMockUser();
        Order mockOrder = feedbackServiceImplTest.createMockOrder();

        when(mockFeedbackRepo.getApprovedDriverName(anyLong())).thenReturn(Optional.of(APPROVED_DRIVER_NAME));
        when(mockFeedbackRepo.getApprovedDriverEmail(anyLong())).thenReturn(Optional.of(APPROVED_DRIVER_EMAIL));
        when(mockUserRepo.findOneOpt(anyString())).thenReturn(Optional.of(mockUser));
        when(mockOrderRepo.findOneOpt(anyLong())).thenReturn(Optional.of(mockOrder));

        when(mockFeedbackRepo.save(any(Feedback.class))).thenReturn(null);

    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which copies fields from an object of Feedback.class
     * to an object of feedbackDTO.class
     */
    public void testCopyFeedbackToDTO() {

        FeedbackDto feedbackDTO = feedbackService.copyFeedbackToDTO(feedback);

        Assert.assertTrue(feedback.getFeedbackId().equals(feedbackDTO.getFeedbackId()) &&
                feedback.getOrder().getId().equals(feedbackDTO.getOrderId()) &&
                feedback.getText().equals(feedbackDTO.getText()) &&
                feedback.getUser().getEmail().equals(feedbackDTO.getUserEmail()) &&
                feedbackDTO.getUserName() != null &&
                feedbackDTO.getTransporterName() != null &&
                feedback.getRate().equals(feedbackDTO.getRate()) &&
                feedback.getApproved().equals(feedbackDTO.getApproved()) &&
                feedback.getCreatedOn().equals(feedbackDTO.getCreatedOn()));
    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which copies fields from an object of FeedbackDTO.class
     * to an object of Feedback.class
     */
    public void testCopyDTOToFeedback() {

        Feedback feedback = feedbackService.copyDTOToFeedback(feedbackDto);

        Assert.assertTrue(feedback.getFeedbackId().equals(feedbackDto.getFeedbackId()) &&
                feedback.getOrder().getId().equals(feedbackDto.getOrderId()) &&
                feedback.getText().equals(feedbackDto.getText()) &&
                feedback.getUser().getEmail().equals(feedbackDto.getUserEmail()) &&
                feedback.getUser().getFirstName() != null &&
                feedback.getUser().getLastName() != null &&
                feedback.getRate().equals(feedbackDto.getRate()) &&
                feedback.getApproved().equals(feedbackDto.getApproved()) &&
                feedback.getCreatedOn().equals(feedbackDto.getCreatedOn()));
    }

    @Test(groups = {"mock"})
    /**
     *  test for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *
     *  the method check the contents and the size of the list - objects must not be null
     */
    public void testGetAllFeedbacksMock() {

        List<Feedback> feedbackList = new ArrayList<>();

        for (int i = 0; i < COUNT; i++) {
            feedbackList.add(feedbackServiceImplTest.createMockFeedback());
        }

        when(mockFeedbackRepo.findAll()).thenReturn(feedbackList);

        List<FeedbackDto> feedbackDTOList = feedbackService.findAll();

        boolean passed = true;

        for (FeedbackDto f : feedbackDTOList) {
            if (f == null) {
                passed = false;
            }
        }

        Assert.assertTrue(passed);
        Assert.assertTrue(COUNT == feedbackDTOList.size());

    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which changes status of a feedback in the db
     */
    public void testChangeFeedbackStatus() {

        long feedbackId = feedbackDto.getFeedbackId();

        Boolean previousStatus = feedbackDto.getApproved();

        feedbackDto.setApproved(!previousStatus);

        Feedback feedback = feedbackService.copyDTOToFeedback(feedbackDto);

        when(mockFeedbackRepo.findOneOpt(anyLong())).thenReturn(Optional.of(feedback));

        feedbackService.changeFeedbackStatus(feedbackDto.getFeedbackId(), feedbackDto.getApproved());

        FeedbackDto feedbackDto1 = feedbackService.findByFeedbackId(feedbackId);

        Assert.assertFalse(previousStatus.equals(feedbackDto1.getApproved()));
    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which saves an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testSave() {

        when(mockFeedbackRepo.findOneOpt(anyLong())).thenReturn(oFeedback);

        feedbackService.save(feedbackDto);

        long id = feedbackDto.getFeedbackId();

        FeedbackDto feedbackDto1 = feedbackService.findByFeedbackId(id);

        Assert.assertTrue(feedbackDto.equals(feedbackDto1));
    }

    @Test(groups = {"mock"}, dependsOnMethods = {"testSave"})
    /**
     * tests method from FeedbackServiceImpl.class, which updates an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testUpdate() {

        when(mockFeedbackRepo.findOneOpt(anyLong())).thenReturn(oFeedback);
        when(mockFeedbackRepo.exists(anyLong())).thenReturn(true);

        FeedbackDto feedbackDto0 = feedbackService.findByFeedbackId(FEEDBACK_ID);

        feedbackServiceImplTest.changeData(feedbackDto0);

        feedbackService.update(feedbackDto0);

        FeedbackDto feedbackDto1 = feedbackService.findByFeedbackId(FEEDBACK_ID);

        Assert.assertFalse(feedbackDto0.equals(feedbackDto1));
    }

    @Test(groups = {"mock"}, expectedExceptions = NoSuchElementException.class)
    /**
     * tests method from FeedbackServiceImpl.class, which deletes an object of FeedbackDTO.class with a given id
     * from the db
     */
    public void testDelete() {

        doNothing().when(mockFeedbackRepo).delete(feedback);
        when(mockFeedbackRepo.findOneOpt(DELETED_FEEDBACK_ID)).thenThrow(new NoSuchElementException());

        feedbackService.delete(DELETED_FEEDBACK_ID);

        feedbackService.findByFeedbackId(DELETED_FEEDBACK_ID);
    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of FeedbackDTO.class
     * with a given id
     */
    public void testFindOne() {

        when(mockFeedbackRepo.findOneOpt(anyLong())).thenReturn(oFeedback);

        Assert.assertNotNull(feedbackService.findOne(FEEDBACK_ID));
    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of User.class
     * with a given id
     */
    public void testGetUser() {
        User mockUser0 = feedbackServiceImplTest.createMockUser();
        User mockUser1 = feedbackService.getUser("");
        Assert.assertTrue(mockUser0.equals(mockUser1));
    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of Order.class
     * with a given id
     */
    public void testGetOrder() {
        Order mockOrder0 = feedbackServiceImplTest.createMockOrder();
        Order mockOrder1 = feedbackService.getOrder(1L);
        Assert.assertTrue(mockOrder0.equals(mockOrder1));
    }

    @Test(groups = {"mock"}, expectedExceptions = NullPointerException.class)
    /**
     * tests method from FeedbackServiceImpl.class, which return list of Feedback.class objects, filtered acc.
     * to FeedbackFilterDTO.class
     */
    public void testFindFiltered() {

        List<FeedbackDto> feedbackDtos = new ArrayList<>();

        feedbackDtos.add(feedbackServiceImplTest.createMockFeedbackDTO());

        when(mockFeedbackCustomRepo.findFiltered(any(FeedbackFilter.class))).thenReturn(feedbackDtos);

        Assert.assertNotNull(feedbackService.findFiltered(feedbackServiceImplTest.createMockFeedbackFilterDTO()));
    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which gets an object of FeedbackDTO.class with a given id
     *
     */
    public void testFindByFeedbackId() {

        FeedbackDto feedbackDTO0 = feedbackServiceImplTest.createMockFeedbackDTO();

        Feedback feedback = feedbackService.copyDTOToFeedback(feedbackDTO0);

        when(mockFeedbackRepo.findOneOpt(anyLong())).thenReturn(Optional.of(feedback));

        FeedbackDto feedbackDTO1 = feedbackService.findByFeedbackId(FEEDBACK_ID);

        Assert.assertTrue(feedbackDTO0.equals(feedbackDTO1));
    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which gets an object of FeedbackDTO.class with a given id
     * from a user with a Customer role
     */
    public void testGetCustomerFeedback() {

        when(mockFeedbackRepo.getCustomerFeedback(anyLong())).thenReturn(feedback);

        FeedbackDto feedbackDto = feedbackService.getCustomerFeedback(FEEDBACK_ID);

        Assert.assertNotNull(feedbackDto);
    }

    @Test(groups = {"mock"}, expectedExceptions = IllegalArgumentException.class)
    /**
     * tests method from FeedbackServiceImpl.class, which adds a feedback from FeedbackDto and user email
     */
    public void testAddFeedback() {

        feedbackService.addFeedback(null, null);

    }

    @Test(groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which finds a feedback by id and user email
     */
    public void testGetFeedback() {

        List<Feedback> feedbacks = new ArrayList<>();

        feedbacks.add(feedback);

        when(mockFeedbackRepo.getFeedbackByOrderIdAndEmail(anyLong(), anyString())).thenReturn(feedbacks);

        FeedbackDto feedbackDto = feedbackService.getFeedback(FEEDBACK_ID, APPROVED_DRIVER_EMAIL);

        Assert.assertNotNull(feedbackDto);
    }
}
