package org.example.fileimport.repo;

import org.example.fileimport.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepo extends JpaRepository<Employee,Integer> {

//    @Query(value = "SELECT  * FROM Employee WHERE LOWER(address) LIKE LOWER(concat("%", :searchtext,"%")) or lower(department)like lower(concat("%", :searchtext,"%"))", nativeQuery = true)
    @Query(value = "select * from employee where lower(address) like lower(concat('%', :searchtext, '%')) or lower(department) like lower(concat('%', :searchtext, '%')) ", nativeQuery = true)
    List<Employee> findEmployeeBySearch(String searchtext);

}
