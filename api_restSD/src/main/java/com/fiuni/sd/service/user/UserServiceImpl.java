package com.fiuni.sd.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.user.IUserDao;
import com.fiuni.sd.domain.credentials.User;
import com.fiuni.sd.dto.user.UserDTO;
import com.fiuni.sd.dto.user.UserResult;
import com.fiuni.sd.service.base.BaseServiceImpl;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO, User, UserResult> implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	@Override
	public UserDTO save(UserDTO dto) {
		final User userDomain = convertDtoToDomain(dto);
		final User user = userDao.save(userDomain);
		return convertDomainToDto(user);
	}

	@Override
	public UserDTO getById(Integer id) {
		Optional<User> result = userDao.findById(id);
        UserDTO userDto = null;
        if(result.isPresent()){
        	userDto = convertDomainToDto(result.get());
        } else {
            throw new RuntimeException("Did not find employee id: " + id);
        }
        return userDto;
	}

	@Override
	public UserResult getAll(Pageable pageable) {
		final List<UserDTO> usersDto = new ArrayList<>();
		Page<User> results = userDao.findAll(pageable);
		for (User userDomain : results) {
			usersDto.add(convertDomainToDto(userDomain));
		}
		UserResult userResult = new UserResult();
		userResult.setUsers(usersDto);
		return userResult;
	}

	@Override
	public UserResult getAllUsers() {
		final List<UserDTO> usersDto = new ArrayList<>();
		List<User> results = userDao.findAll();
		for (User userDomain : results) {
			usersDto.add(convertDomainToDto(userDomain));
		}
		UserResult userResult = new UserResult();
		userResult.setUsers(usersDto);
		return userResult;
	}

	@Override
	public void deleteById(int id) {
		userDao.deleteById(id);
	}

	@Override
	protected UserDTO convertDomainToDto(User domain) {
		UserDTO dto = new UserDTO();
		dto.setId(domain.getId());
		dto.setFirstName(domain.getFirstName());
		dto.setLastName(domain.getLastName());
		dto.setEmail(domain.getEmail());
		dto.setUsername(domain.getUsername());
        return dto;
	}

	@Override
	protected User convertDtoToDomain(UserDTO dto) {
		final User domain = new User();
		domain.setId(dto.getId());
		domain.setFirstName(dto.getFirstName());
		domain.setLastName(dto.getLastName());
		domain.setEmail(dto.getEmail());
		domain.setUsername(dto.getUsername());
        return domain;
	}

}
