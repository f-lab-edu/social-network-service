package com.zudbs.project.mapper;

import com.zudbs.project.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper /* My-batis Mapper로 등록 */
public interface UserMapper {
    void  insertUser(User user);
}
