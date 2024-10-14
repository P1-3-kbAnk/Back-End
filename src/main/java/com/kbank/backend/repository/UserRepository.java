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

    @Query("SELECT u FROM User u JOIN FETCH u.authUser au " +
            "WHERE au.authUserPk = :authUserId")
    Optional<User> findByUserWithAuthUserId(@Param("authUserId") Long authUserId);

    Optional<User> findUserByUserNmAndFirstNoAndLastNo(String userNm, String firstNo, String lastNo);

//    @Query("SELECT u FROM AuthUser u WHERE u.socialId = :id AND u.provider = :provider")
//    Optional<UserRepository.UserSecurityForm> findBySocialIdAndEProvider(@Param("id") String id, @Param("provider") Provider provider);
//
//    interface UserSecurityForm {
//
//        static UserRepository.UserSecurityForm invoke(User user) {
//
//            return new UserRepository.UserSecurityForm() {
//                @Override
//                public Long getId() {
//                    return user.getUserPk();
//                }
//
//                @Override
//                public Role getRole() {
//                    return user.getRole();
//                }
//            };
//        }
//
//        Long getId();
//        Role getRole();
//    }

}
