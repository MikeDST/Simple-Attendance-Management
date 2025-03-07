package com.attendance.ServiceInterface;

import com.attendance.Entity.AppUser;

public interface UserService {
    public AppUser registerUser(String username, String email, String password);
}
