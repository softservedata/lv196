package com.softserve.edu.delivery.service.feedback;

import static com.softserve.edu.delivery.dto.UserProfileDto.create;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserProfileFilterDto;
import com.softserve.edu.delivery.service.impl.UserServiceImpl;

public class UserProfileServiceTest {

	private UserDao userDao = mock(UserDao.class);
	
	private UserServiceImpl userService = new UserServiceImpl(userDao);

	@Test(enabled = false)
	public void registerUser() {
		User user = mock(User.class);
		String userMail = "some@mail.com";

		when(user.getEmail()).thenReturn(userMail);
		doReturn(false).when(userDao).exists(userMail);

		userService.register(user);

		verify(userDao).exists(userMail);
		verify(userDao).save(user);
	}

	@Test(enabled = false, expectedExceptions = IllegalArgumentException.class)
	public void registerUserWhenMailDoesntExist() {
		User user = mock(User.class);
		String userMail = "some@mail.com";

		when(user.getEmail()).thenReturn(userMail);
		doReturn(true).when(userDao).exists(userMail);

		userService.register(user);
	}

	@Test(enabled = false)
	public void getAllUsersInRange() {
		User user1 = new User();
		User user2 = new User();

		user1.setEmail("123");
		user2.setEmail("456");
		user1.setUserRole(Role.MODERATOR);
		user2.setUserRole(Role.ADMIN);

		UserProfileFilterDto filter = mock(UserProfileFilterDto.class);

		when(filter.test(Mockito.any(User.class))).thenReturn(true);

		List<User> userList = asList(user1, user2);
		int page = 1;
		int size = 2;

		when(userDao.getAllUsersInRange(page, size)).thenReturn(userList);

		List<UserProfileDto> allUsers = userService.getAllUsers(page, size, filter);

		assertEquals(allUsers, asList(create(user1), create(user2)));

		verify(userDao).getAllUsersInRange(page, size);
		verify(filter).test(user1);
		verify(filter).test(user2);
	}

	@Test(enabled = false)
	public void changeUserStatus() {
		User user1 = new User();
		User user2 = new User();

		String userMail = "some@mail.com";
		user1.setEmail(userMail);
		user2.setEmail(userMail);
		user1.setUserRole(Role.CUSTOMER);
		user2.setUserRole(Role.CUSTOMER);

		boolean isblocked = true;

		when(userDao.findOne(userMail)).thenReturn(Optional.of(user1));
		when(userDao.update(user1.setBlocked(isblocked))).thenReturn(user2);

		UserProfileDto userProfile = userService.changeUserStatus(userMail, isblocked);

		assertEquals(userProfile, create(user2));

		verify(userDao).findOne(userMail);
		verify(userDao).update(user1.setBlocked(isblocked));

	}

	@Test(enabled = false, expectedExceptions = IllegalArgumentException.class)
	public void changeStatusWhenUserDoesntExist() {
		User user1 = new User();
		User user2 = new User();

		String userMail1 = "123";
		String userMail2 = "456";
		// user1.setEmail(userMail1);
		user2.setEmail(userMail2);
		// user1.setUserRole(Role.CUSTOMER);
		user2.setUserRole(Role.CUSTOMER);

		boolean isblocked = true;

		when(userDao.findOne(userMail1)).thenReturn(Optional.of(user1));
		when(userDao.update(user1.setBlocked(isblocked))).thenReturn(user2);

		UserProfileDto userProfile = userService.changeUserStatus(userMail1, isblocked);

		assertEquals(userProfile, create(user2));
		verify(userDao).findOne(userMail1);
	}

	@Test(enabled = true)
	public void changeUsersStatus() {
		String userMail1 = "123";
		String userMail2 = "123";
		boolean isblocked1 = true;
		boolean isblocked2 = true;

		Map<String, Boolean> map = mock(Map.class);
		map.put(userMail1, isblocked1);
		map.put(userMail2, isblocked2);

		List<UserProfileDto> allUsers = userService.changeUsersStatus(map);

	}

}