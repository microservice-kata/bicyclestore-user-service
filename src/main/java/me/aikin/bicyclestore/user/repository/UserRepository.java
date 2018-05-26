package me.aikin.bicyclestore.user.repository;

import me.aikin.bicyclestore.user.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {
    @Select("SELECT * FROM user WHERE id = #{userId}")
    User findOne(Long userId);

    @Insert("INSERT into user(ID, USERNAME, CREATED_AT) VALUES(#{id}, #{userName}, #{createdAt})")
    void insert(User user);
}
