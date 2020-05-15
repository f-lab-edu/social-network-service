package com.zudbs.project.mapper;

import com.zudbs.project.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/*
Mybatis가 DB에 관한 DataSource 정보를 토대로 SQL문이 정의된 Mapper.xml객체를 생성할 때
해당 어노테이션 또는 Mapper Interface를 Implements해서 객체를 생성하여 Mapper로 등록합니다.
이렇게 만들어지는 Mapper에는 Mapper.xml에서 작성한 SQL 문을 명시한 DB에서 실행하는 메소드들이
구현되어 있습니다.
*/
public interface UserMapper {

    void insertUser(User user);

    int deleteUser(User user);
}
