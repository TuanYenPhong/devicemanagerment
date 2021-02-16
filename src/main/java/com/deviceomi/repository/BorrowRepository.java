package com.deviceomi.repository;

import com.deviceomi.model.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BorrowRepository extends JpaRepository<BorrowEntity, Long> {

    @Query("select b from BorrowEntity b where b.dateReturn>?1 and b.dateReturn is not null and b.userBorrow is not null")
    List<BorrowEntity> findByUserBorrowDate(Date date);

    /**
     * T�m ki?m mu?n tr? d?a v�o ph�ng ban
     * */
    @Query("select b from BorrowEntity b where b.deparmentBorrow.id = ?1")
    List<BorrowEntity> findByDepartmentId(Long departmentId);

//    @Query("select b from BorrowEntity b where (?1 is null or b.deviceBorrow.codeDevice like %?1%) or ((?2 is null or b.dateBorrow>?2) " +
//            "and (b.dateBorrow<?3) and (b.dateReturn>?4) and (?5 is null or b.dateReturn>?5))")
//    List<BorrowEntity> searchBorrow(String type,Date fromDateBorrow,Date toDateBorrow,Date fromDateReturn,Date toDateReturn);
}
