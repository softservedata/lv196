package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.EmailVerificationToken;
import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.DriverRegistrationDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
import com.softserve.edu.delivery.exception.EmailExistsException;
import com.softserve.edu.delivery.exception.TokenNotFoundException;
import com.softserve.edu.delivery.repository.CarRepository;
import com.softserve.edu.delivery.repository.EmailTokenRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.service.event.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private Long pages;
    private final UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EmailTokenRepository tokenRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!this.exists(email)) {
            throw new UsernameNotFoundException("Email " + email + " is not registered");
        }

        User user = userRepository.findOne(email);

        boolean enabled = user.getApproved();
        boolean accountNonExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNonLocked = !user.getBlocked();
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
        if (this.exists(userRegDTO.getUserEmail())) {
            throw new EmailExistsException(userRegDTO.getUserEmail());
        } else {
            User newUser = createUser(userRegDTO);

            userRepository.save(newUser);

            String uuid = generateRandomUUID();
            EmailVerificationToken verificationToken = new EmailVerificationToken(uuid, newUser);
            tokenRepository.save(verificationToken);

            OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(newUser.getEmail(), url, verificationToken.getToken());
            eventPublisher.publishEvent(event);
        }
    }

    @Override
    public void register(DriverRegistrationDTO driverRegDTO, String url) {
        if (this.exists(driverRegDTO.getDriverEmail())) {
            throw new EmailExistsException(driverRegDTO.getDriverEmail());
        } else {
            User newDriver = createDriver(driverRegDTO);
            userRepository.save(newDriver);

            Car newCar = createCar(driverRegDTO);
            carRepository.save(newCar);

            String uuid = generateRandomUUID();
            EmailVerificationToken verificationToken = new EmailVerificationToken(uuid, newDriver);
            tokenRepository.save(verificationToken);

            OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(newDriver.getEmail(), url, verificationToken.getToken());
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
    public UserProfileDto changeUserStatus(String email, boolean blocked) {
        return userRepository
                .findOneOpt(email)
                .map(user -> userRepository.save(user.setBlocked(!blocked)))
                .map(UserProfileDto::create)
                .<IllegalStateException>orElseThrow(() -> new IllegalStateException("User: " + email + " not found!"));
    }

    @Override
    public List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map) {
        return map
                .keySet().stream()
                .map(mail -> changeUserStatus(mail, map.get(mail)))
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getUser(String email) {
        return UserProfileDto.create(userRepository.findOne(email));
    }

    @Override
    public List<UserProfileDto> findUsers(String fname, String lname, String email,
    									  String role, Boolean status, int size, int page) {
    	Page<User> resultPage = userRepository.findUsers(fname, lname, email, 
    									  				 role == null ? null : Role.valueOf(role.toUpperCase()),
    													 status, new PageRequest(page - 1, size));
    	pages = resultPage.getTotalElements();
		return resultPage
				.getContent()
				.stream()
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
    }
    
	@Override
	public Long getPages() {
		return pages;
	}

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }


    private User createUser(UserRegistrationDTO userRegDTO) {
        User newUser = new User();
        newUser.setEmail(userRegDTO.getUserEmail());
        newUser.setPassword(this.passwordEncoder.encode(userRegDTO.getUserPassword()));
        newUser.setFirstName(userRegDTO.getUserFirstName());
        newUser.setLastName(userRegDTO.getUserLastName());
        newUser.setPhone(userRegDTO.getUserPhoneNumber());
        newUser.setPassport(userRegDTO.getUserPassport());
        newUser.setPhotoUrl(userRegDTO.getUserPhotoUrl());

        newUser.setBlocked(false);
        newUser.setApproved(false);
        newUser.setUserRole(Role.CUSTOMER);

        return newUser;
    }

    private User createDriver(DriverRegistrationDTO driverRegDTO) {
        User newDriver = new User();
        newDriver.setEmail(driverRegDTO.getDriverEmail());
        newDriver.setPassword(this.passwordEncoder.encode(driverRegDTO.getDriverPassword()));
        newDriver.setFirstName(driverRegDTO.getDriverFirstName());
        newDriver.setLastName(driverRegDTO.getDriverLastName());
        newDriver.setPhone(driverRegDTO.getDriverPhoneNumber());
        newDriver.setPassport(driverRegDTO.getDriverPassport());
        newDriver.setPhotoUrl(driverRegDTO.getDriverPhotoUrl());

        newDriver.setBlocked(false);
        newDriver.setApproved(false);
        newDriver.setUserRole(Role.DRIVER);

        return newDriver;
    }

    private Car createCar(DriverRegistrationDTO driverRegDTO) {
        Car newCar = new Car();
        newCar.setVehicleName(driverRegDTO.getVehicleName());
        newCar.setVehicleNumber(driverRegDTO.getVehicleNumber());
        newCar.setVehicleVIN(driverRegDTO.getVehicleVIN());
        newCar.setVehicleFrontPhotoURL(driverRegDTO.getVehicleFrontPhotoURL());
        newCar.setVehicleBackPhotoURL(driverRegDTO.getVehicleBackPhotoURL());
        newCar.setVehicleWeight(driverRegDTO.getVehicleWeight());
        newCar.setVehicleLength(driverRegDTO.getVehicleLength());
        newCar.setVehicleWidth(driverRegDTO.getVehicleWidth());
        newCar.setVehicleHeight(driverRegDTO.getVehicleHeight());

        return newCar;
    }
}