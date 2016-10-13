package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.EmailVerificationToken;
import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserProfileFilterDto;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
import com.softserve.edu.delivery.exception.EmailExistsException;
import com.softserve.edu.delivery.exception.TokenNotFoundException;
import com.softserve.edu.delivery.repository.EmailTokenRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.service.event.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserRepository userRepository;

    @Autowired
    private EmailTokenRepository tokenRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if ( ! this.exists(email)) {
            throw new UsernameNotFoundException("Email " + email + " is not registered");
        }

        User user = userRepository.findOne(email);

        boolean enabled = user.getApproved();
        boolean accountNonExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNonLocked = ! user.getBlocked();
        String role = user.getUserRole().getName();
        List<GrantedAuthority> listUserRoles = new ArrayList<>();
        listUserRoles.add(new SimpleGrantedAuthority(role));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), enabled, accountNonExpired,
                credentialsNotExpired, accountNonLocked, listUserRoles);
    }

    @Override
    public boolean exists(String email) {
        return userRepository.exists(email);
    }

    @Override
    public void register(UserRegistrationDTO userRegDTO, String url) {
        if (this.exists(userRegDTO.getEmail())) {
            throw new EmailExistsException(userRegDTO.getEmail());
        } else {
            User newUser = createUser(userRegDTO);

            newUser.setUserRole(Role.CUSTOMER);
            userRepository.save(newUser);

            String uuid = generateRandomUUID();
            EmailVerificationToken verificationToken = new EmailVerificationToken(uuid, newUser);
            tokenRepository.save(verificationToken);

            OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(newUser.getEmail(), url, verificationToken.getToken());
            eventPublisher.publishEvent(event);

        }
    }

    @Override
    public void verifyRegistration(String token) {
        EmailVerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            throw new TokenNotFoundException();
        } else {
            verificationToken.getUser().setApproved(true);
            tokenRepository.delete(verificationToken);
        }
    }

    @Override
	public List<UserProfileDto> getAllUsers(int page, int size, UserProfileFilterDto filter) {
		return userDao
				.getAllUsersInRange(page, size)
				.stream()
				.filter(filter)
				.map(UserProfileDto::create)
				.collect(Collectors.toList());
	}

	@Override
	public UserProfileDto changeUserStatus(String email, boolean blocked){
		return userRepository
                .findOneOpt(email)
				.map(user -> userRepository.save(user.setBlocked(!blocked)))
                .map(UserProfileDto::create)
                .<IllegalStateException>orElseThrow(() -> new IllegalStateException("User: " + email + " not found!"));
	}

	@Override
	public List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map) {
		return 	map
				.keySet().stream()
				.map(mail -> changeUserStatus(mail, map.get(mail)))
				.collect(Collectors.toList());
	}

    @Override
    public List<UserProfileDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
    }
    
    @Override
    public UserProfileDto getUser(String email) {
        return UserProfileDto.create(userRepository.findOne(email));
    }
    
	@Override
	public List<UserProfileDto> findUsersByBanStatus(Boolean status) {
		return userRepository
				.findByBlockedOrderByLastNameAsc(status)
				.stream()
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
	}
	
	@Override
	public List<UserProfileDto> findUsersByEmail(String value) {
		return userRepository
				.findByEmailStartsWithOrderByLastNameAsc(value)
				.stream()
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
	}
	
	@Override
	public List<UserProfileDto> findUsersByFirstName(String value) {
		return userRepository
				.findByFirstNameStartsWithOrderByLastNameAsc(value)
				.stream()
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
	}
	
	@Override
	public List<UserProfileDto> findUsersByLastName(String value) {
		return userRepository
				.findByLastNameStartsWithOrderByLastNameAsc(value)
				.stream()
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
	}
	
	@Override
	public List<UserProfileDto> findUsersByRole(String value) {
		return userRepository
				.findByUserRoleOrderByLastNameAsc(Role.valueOf(value))
				.stream()
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
	}

	private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    private User createUser(UserRegistrationDTO userRegDTO) {
        User newUser = new User();
        newUser.setEmail(userRegDTO.getEmail());
        newUser.setPassword(this.passwordEncoder.encode(userRegDTO.getPassword()));
        newUser.setFirstName(userRegDTO.getFirstName());
        newUser.setLastName(userRegDTO.getLastName());
        newUser.setPhone(userRegDTO.getPhoneNumber());
        newUser.setPassport(userRegDTO.getPassport());
        newUser.setPhotoUrl(userRegDTO.getPhotoUrl());
        newUser.setBlocked(false);
        newUser.setApproved(false);
        return newUser;
    }
}