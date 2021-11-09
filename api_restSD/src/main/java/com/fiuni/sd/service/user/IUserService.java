package com.fiuni.sd.service.user;

import com.fiuni.sd.dto.user.UserDTO;
import com.fiuni.sd.dto.user.UserResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IUserService extends IBaseService<UserDTO, UserResult>{
	public UserResult getAllUsers();
}
