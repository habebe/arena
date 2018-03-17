package com.abex.arena.core.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abex.arena.core.dto.UserDto;
import com.abex.arena.core.error.UserAlreadyExistException;
import com.abex.arena.core.persistence.dao.PasswordResetTokenRepository;
import com.abex.arena.core.persistence.dao.PrivilegeRepository;
import com.abex.arena.core.persistence.dao.UserRepository;
import com.abex.arena.core.persistence.dao.UserRoleRepository;
import com.abex.arena.core.persistence.dao.VerificationTokenTokenRepository;
import com.abex.arena.core.persistence.model.user.PasswordResetToken;
import com.abex.arena.core.persistence.model.user.User;
import com.abex.arena.core.persistence.model.user.UserRole;
import com.abex.arena.core.persistence.model.user.VerificationToken;
import com.abex.arena.core.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private PasswordResetTokenRepository passwordResetTokenRepository;

	private PrivilegeRepository privilegeRepository;

	private UserRepository userRepository;

	private UserRoleRepository userRoleRepository;

	private VerificationTokenTokenRepository verificationTokenTokenRepository;

	private PasswordEncoder passwordEncoder;


	@Override
	public UserRoleRepository getUserRoleRepository() {
		return userRoleRepository;
	}

	@Override
	public UserRole createUserRole(UserRole entity) {
		return userRoleRepository.save(entity);
	}

	public UserRoleRepository getRoleRepository() {
		return userRoleRepository;
	}

	public PasswordResetTokenRepository getPasswordResetTokenRepository() {
		return passwordResetTokenRepository;
	}

	@Autowired
	public void setPasswordResetTokenRepository(PasswordResetTokenRepository passwordResetTokenRepository) {
		this.passwordResetTokenRepository = passwordResetTokenRepository;
	}

	public PrivilegeRepository getPrivilegeRepository() {
		return privilegeRepository;
	}

	@Autowired
	public void setPrivilegeRepository(PrivilegeRepository privilegeRepository) {
		this.privilegeRepository = privilegeRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public VerificationTokenTokenRepository getVerificationTokenTokenRepository() {
		return verificationTokenTokenRepository;
	}

	@Autowired
	public void setVerificationTokenTokenRepository(VerificationTokenTokenRepository verificationTokenTokenRepository) {
		this.verificationTokenTokenRepository = verificationTokenTokenRepository;
	}

	@Autowired
	public void setUserRoleRepository(UserRoleRepository roleRepository) {
		this.userRoleRepository = roleRepository;
	}


	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	private boolean emailExist(final String email) {
		return this.getUserRepository().findByEmail(email) != null;
	}

	@Override
	public User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException {
		if (emailExist(accountDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
		}
		final User user = new User();

		user.setFirstName(accountDto.getFirstName());
		user.setLastName(accountDto.getLastName());
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		user.setEmail(accountDto.getEmail());
		user.setUsing2FA(accountDto.isUsing2FA());
		user.setRoles(Arrays.asList(this.getUserRoleRepository().findByName("ROLE_USER")));
		return this.getUserRepository().save(user);
	}

	@Override
	public User getUser(String verificationToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRegisteredUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createVerificationTokenForUser(User user, String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerificationToken generateNewVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserPassword(User user, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkIfValidOldPassword(User user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateQRUrl(User user) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser2FA(boolean use2fa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getUsersFromSessionRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

}
