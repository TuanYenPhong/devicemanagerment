package com.deviceomi.payload.response;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.model.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
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

    public UserBorrowResponse(BorrowEntity borrowEntity){
        if(borrowEntity.getUserBorrow()!=null){
            this.setIdUser(borrowEntity.getUserBorrow().getId());
            this.setNameUser(borrowEntity.getUserBorrow().getFullname());
        }
        if(borrowEntity.getDeparmentBorrow()!=null)
            this.setDepartment(borrowEntity.getDeparmentBorrow().getNameDepartment());
    }

}
