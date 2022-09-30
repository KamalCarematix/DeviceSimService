package com.rpm.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rpm.rest.model.DeviceDataSimEntity;

@Repository
@Transactional
public interface DaoRepository extends CrudRepository<DeviceDataSimEntity, Long> {
	
  @Query(value="select ddsd.SIM from DVC_DEVICE_SIM_DETAILS ddsd where ddsd.SERIAL_NUMBER =:serialNo", nativeQuery=true)
  List<String> getAllSimOfDevice(@Param("serialNo") String serialNo);
  
 /* @Query("select report from BulkReportEntity report where report.id = :id")
  DeviceEntity getAllSchduledReportById(@Param("id") int paramInt);
  
  @Modifying
  @Query("UPDATE BulkReportEntity b SET b.status = :status,b.DISCRIPTION = :action WHERE b.id = :id")
  void updateStatus(@Param("id") int paramInt, @Param("status") String paramString1, @Param("action") String paramString2);
  
  @Query(value = "SELECT * FROM PTL_BULK_REPORTS WHERE CREATED_ON <= TRUNC(SYSDATE) - 3 ", nativeQuery = true)
  List<DeviceEntity> reportgetbefor3days();
  
  @Modifying
  @Query("DELETE FROM BulkReportEntity e WHERE e.id = :id")
  void deleteReport(@Param("id") int paramInt);*/
}
