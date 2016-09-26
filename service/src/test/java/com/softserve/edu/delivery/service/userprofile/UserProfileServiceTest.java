package com.softserve.edu.delivery.service.userprofile;

import static com.softserve.edu.delivery.dto.UserProfileDto.create;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
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

	@Test(enabled = true)
	public void registerUser() {
		User user = mock(User.class);
		String userMail = "some@mail.com";

		when(user.getEmail()).thenReturn(userMail);
		doReturn(false).when(userDao).exists(userMail);

		userService.register(user);

		verify(userDao).exists(userMail);
		verify(userDao).save(user);
	}

	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void registerUserWhenMailDoesntExist() {
		User user = mock(User.class);
		String userMail = "some@mail.com";

		when(user.getEmail()).thenReturn(userMail);
		doReturn(true).when(userDao).exists(userMail);

		userService.register(user);
	}

	@Test(enabled = true)
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
		int page = 0;
		int size = 2;

		when(userDao.getAllUsersInRange(page, size)).thenReturn(userList);

		List<UserProfileDto> allUsers = userService.getAllUsers(page, size, filter);

		assertEquals(allUsers.size(), asList(create(user1), create(user2)).size());

		verify(userDao).getAllUsersInRange(page, size);
		verify(filter).test(user1);
		verify(filter).test(user2);
	}

	@Test(enabled = true)
	public void changeUserStatus() {
		User user = new User();
		String userMail = "some@mail.com";
		user.setEmail(userMail);
		user.setUserRole(Role.CUSTOMER);

		Boolean isblocked = true;

		when(userDao.findOne(Mockito.any(String.class))).thenReturn(Optional.of(user));
		when(userDao.update(user.setBlocked(isblocked))).thenReturn(user);

		UserProfileDto userProfile = userService.changeUserStatus(userMail, isblocked);

		verify(userDao).findOne(userMail);
		verify(userDao).update(user.setBlocked(isblocked));
	}
	
	@Test(enabled = false, expectedExceptions = IllegalArgumentException.class)
	public void changeStatusWhenUserDoesntExist() {
		User user = new User();

		String userMail = "some@mail.com";
		user.setEmail(userMail);
		user.setUserRole(Role.CUSTOMER);


		Boolean isblocked = true;

		when(userDao.findOne(userMail)).thenReturn(Optional.of(user));
		when(userDao.update(user.setBlocked(isblocked))).thenReturn(Mockito.any(User.class));

		UserProfileDto userProfile = userService.changeUserStatus(userMail, isblocked);

		assertEquals(userProfile.getBlocked(), create(user).getBlocked());

		verify(userDao).findOne(userMail);
		verify(userDao).update(user.setBlocked(isblocked));

	}



	@Test(enabled = false)
	public void changeUsersStatus() {
		User user1 = new User();
		User user2 = new User();
		String userMail1 = "123";
		String userMail2 = "123";
		boolean isblocked1 = true;
		boolean isblocked2 = true;
		user1.setEmail(userMail1);
		user2.setEmail(userMail2);
		user1.setUserRole(Role.MODERATOR);
		user2.setUserRole(Role.ADMIN);
		user1.setBlocked(isblocked1);
		user1.setBlocked(isblocked2);

		Map<String, Boolean> map = mock(Map.class);
		map.put(userMail1, isblocked1);
		map.put(userMail2, isblocked2);

		List<UserProfileDto> allUsers = userService.changeUsersStatus(map);
		
		assertEquals(allUsers, asList(create(user1), create(user2)));
		

	}

}