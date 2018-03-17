package com.abex.arena.core.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.abex.arena.core.persistence.dao.PrivilegeRepository;
import com.abex.arena.core.persistence.dao.UserRepository;
import com.abex.arena.core.persistence.dao.UserRoleRepository;
import com.abex.arena.core.persistence.model.user.Privilege;
import com.abex.arena.core.persistence.model.user.User;
import com.abex.arena.core.persistence.model.user.UserRole;

import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(ContextRefreshedListener.class);
	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean isAlreadySetup() {
		return alreadySetup;
	}

	public void setAlreadySetup(boolean alreadySetup) {
		this.alreadySetup = alreadySetup;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserRoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(UserRoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public PrivilegeRepository getPrivilegeRepository() {
		return privilegeRepository;
	}

	public void setPrivilegeRepository(PrivilegeRepository privilegeRepository) {
		this.privilegeRepository = privilegeRepository;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ContextRefreshedListener.getLogger().info("ContextRefreshedListener.onapplicationEvent created");
		if (alreadySetup) {
			return;
		}

		final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
		final Privilege passwordPrivilege = createPrivilegeIfNotFound("CHANGE_PASSWORD_PRIVILEGE");

		final List<Privilege> adminPrivileges = new ArrayList<Privilege>();
		adminPrivileges.add(readPrivilege);
		adminPrivileges.add(writePrivilege);
		adminPrivileges.add(passwordPrivilege);
		
		
		final List<Privilege> userPrivileges = new ArrayList<Privilege>();
		userPrivileges.add(readPrivilege);
		userPrivileges.add(passwordPrivilege);
	
		final UserRole adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", userPrivileges);

		// == create initial user
		createUserIfNotFound("admin@abex.areana.com", "Areana", "Admin", "password",
				new ArrayList<UserRole>(Arrays.asList(adminRole)));

		alreadySetup = true;
	}

	@Transactional
	private final Privilege createPrivilegeIfNotFound(final String name) {
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilege = privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private final UserRole createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
		UserRole role = roleRepository.findByName(name);
		if (role == null) {
			role = new UserRole(name);
		}
		role.setPrivileges(privileges);
		role = roleRepository.save(role);
		return role;
	}

	@Transactional
	private final User createUserIfNotFound(final String email, final String firstName, final String lastName,
			final String password, final Collection<UserRole> roles) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(email);
			user.setEnabled(true);
		}
		user.setRoles(roles);
		user = userRepository.save(user);
		return user;
	}
}