package com.deviceomi.repository;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.model.DepartmentEntity;
import com.deviceomi.payload.request.BorrowRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowRepository extends JpaRepository<BorrowEntity, Long> {

    @Query("select b from BorrowEntity b where b.dateReturn='' and 2<=(select count(*) from BorrowEntity b group by b.userBorrow)")
    List<BorrowEntity> findAllBorrowIfCount();

//    @Query("select b from BorrowEntity b where b.userBorrow.id=?1")
    List<BorrowEntity> findByUserBorrow_Id(Long id);

    /**
     * T�m ki?m mu?n tr? d?a v�o ph�ng ban
     * */
    @Query("select b from BorrowEntity b where b.deparmentBorrow.id = ?1")
    List<BorrowEntity> findByDepartmentId(Long departmentId);

//    Page<BorrowEntity> findPage(BorrowRequest borrowRequest);

}
