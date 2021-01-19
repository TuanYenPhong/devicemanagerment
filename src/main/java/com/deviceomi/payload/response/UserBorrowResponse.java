package com.deviceomi.payload.response;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.model.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Component
public class UserBorrowResponse {
    /**
     * Id user
     */
    private Long idUser;

    /**
     * Full name user
     */
    private String nameUser;

    /**
     * Phong ban
     */
    private String department;

    /**
     * List borrow
     */
    private int countBorow;

    /**
     * List borrow
     */
    private Set<BorrowResponse> borrows;

    public UserBorrowResponse toBorrowResponse(BorrowEntity borrowEntity){
        UserBorrowResponse userBorrowResponse=new UserBorrowResponse();
        userBorrowResponse.setIdUser(borrowEntity.getUserBorrow().getId());
        userBorrowResponse.setNameUser(borrowEntity.getUserBorrow().getFullname());
        userBorrowResponse.setDepartment(borrowEntity.getDeparmentBorrow().getNameDepartment());
        return userBorrowResponse;
    }

}
