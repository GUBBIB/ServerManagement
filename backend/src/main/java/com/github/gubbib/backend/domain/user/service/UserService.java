package com.github.gubbib.backend.domain.user.service;

import java.util.HashMap;

public interface UserService {

    HashMap<String, Object> selectActiveAndPendingUser(HashMap<String, Object> parameters);
}
