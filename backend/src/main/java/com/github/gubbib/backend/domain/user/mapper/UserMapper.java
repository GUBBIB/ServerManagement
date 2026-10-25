package com.github.gubbib.backend.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper {

    HashMap<String, Object> selectActiveAndPendingUser(HashMap<String, Object> parameters);
}
