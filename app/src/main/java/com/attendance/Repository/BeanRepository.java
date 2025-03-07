package com.attendance.Repository;

import com.attendance.Entity.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BeanRepository extends JpaRepository<Bean, UUID>{
    @Query(value = "select * from beans b where b.sold > 0", nativeQuery = true)
    List<Bean> findSoldBeans();
}
