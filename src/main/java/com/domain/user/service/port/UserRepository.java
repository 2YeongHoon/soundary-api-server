package com.domain.user.service.port;

import com.domain.user.domain.User;

public interface UserRepository {

    void save(User user);
}
