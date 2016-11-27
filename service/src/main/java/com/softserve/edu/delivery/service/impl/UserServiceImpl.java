package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.EmailVerificationToken;
import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
import com.softserve.edu.delivery.repository.EmailTokenRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.service.event.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private UserRepository userRepository;

    private Long totalItems;

    @Autowired
    private EmailTokenRepository tokenRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


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
        List<GrantedAuthority> listUserRoles = Arrays.asList(new SimpleGrantedAuthority(role));

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
            throw new IllegalArgumentException("Email already exist: " + userRegDTO.getUserEmail());
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
    public boolean tokenExists(String token) {
        return Objects.nonNull(tokenRepository.findByToken(token));
    }

    @Override
    public void verifyRegistration(String token) {
        if (this.tokenExists(token)) {
            EmailVerificationToken verificationToken = tokenRepository.findByToken(token);
            verificationToken.getUser().setApproved(true);
            tokenRepository.delete(verificationToken);
        } else {
            throw new IllegalArgumentException("Token isn't exist");
        }
    }

    @Override
    public UserProfileDto changeUserStatus(String email, boolean blocked) {
        return userRepository
                .findOneOpt(email)
                .map(user -> userRepository.save(user.setBlocked(!blocked)))
                .map(UserProfileDto::create)
                .orElseThrow(() -> new IllegalStateException("User: " + email + " not found!"));
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
    public List<UserProfileDto> findUsers(UserProfileDto filter) {
        Sort.Order sortOrder = new Sort.Order(Sort.Direction.fromString(
                filter.getSortReverse() ? "ASC" : "DESC"),
                filter.getSortType());

        User user = createUserFromDto(filter);

        Page<User> resultPage = userRepository.findFilteredByExample(user,
                new PageRequest(filter.getCurrentPage() - 1, filter.getRows(), new Sort(sortOrder)));

        totalItems = resultPage.getTotalElements();

        return resultPage
                .getContent()
                .stream()
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
    }

    @Override
    public Long countItems() {
        return totalItems;
    }

    @Override
    public List<Long> countUsersByRole() {
        List<Long> usersData = new ArrayList<>();
        usersData.add(userRepository.countByUserRole(Role.CUSTOMER));
        usersData.add(userRepository.countByUserRole(Role.DRIVER));
        usersData.add(userRepository.countByUserRole(Role.MODERATOR));
        usersData.add(userRepository.countByUserRole(Role.ADMIN));
        return usersData;
    }

    @Override
    public List<Long> countUsersByRate() {
        List<Long> users = new ArrayList<>();
        for (int i = 1; i < 50; i = i + 10)
            users.add(userRepository.countByRate(i, i + 9));
        return users;
    }

    @Override
    public List<UserProfileDto> findTopFiveDriversByRate() {
        return userRepository
                .findTop5ByUserRoleOrderByRateDesc(Role.DRIVER)
                .stream()
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
    }

    @Override
    public User findOne(String email) {
        return userRepository.findOne(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(String email, CharSequence password) {
        return passwordEncoder.matches(password, findOne(email).getPassword());
    }

    @Override
    public void changePassword(String email, CharSequence newPassword) {

        User currentUser = findOne(email);
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        save(currentUser);

    }

    private User createUser(UserRegistrationDTO userRegDTO) {
        User newUser = new User();
        newUser.setEmail(userRegDTO.getUserEmail());
        newUser.setPassword(this.passwordEncoder.encode(userRegDTO.getUserPassword()));
        newUser.setFirstName(userRegDTO.getUserFirstName());
        newUser.setLastName(userRegDTO.getUserLastName());
        newUser.setPhone(userRegDTO.getUserPhoneNumber());
        newUser.setGender(userRegDTO.getGender());

        newUser.setBlocked(false);
        newUser.setApproved(false);
        newUser.setUserRole(Role.CUSTOMER);

        return newUser;
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    private User createUserFromDto(UserProfileDto userProfileDto) {

        return new User()
                .setEmail(userProfileDto.getEmail())
                .setFirstName(userProfileDto.getFirstName())
                .setLastName(userProfileDto.getLastName())
                .setBlocked(userProfileDto.getBlocked())
                .setUserRole(userProfileDto.getRole() == null ? null : userProfileDto.getUserRole())
                .setRate(userProfileDto.getRate())
                .setPhone(userProfileDto.getPhone())
                .setPhotoUrl(userProfileDto.getPhotoUrl())
                .setPassport(userProfileDto.getPassport())
                .setApproved(userProfileDto.getApproved());
    }
}