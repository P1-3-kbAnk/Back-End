package com.kbank.backend.repository;

import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserPk(Long userPk);

    Optional<User> findById(Long id);
    //처방전 create용 메서드 -김성헌
    List<User> findByUserNm(String userNm);


    @Query("SELECT u FROM User u WHERE u.socialId = :id AND u.provider = :provider")
    Optional<UserSecurityForm> findBySocialIdAndEProvider(@Param("id") String id, @Param("provider") Provider provider);

    interface UserSecurityForm {

        static UserSecurityForm invoke(User user) {

            return new UserSecurityForm() {
                @Override
                public Long getId() {
                    return user.getUserPk();
                }

                @Override
                public Role getRole() {
                    return user.getRole();
                }
            };
        }

        Long getId();
        Role getRole();
    }


}
