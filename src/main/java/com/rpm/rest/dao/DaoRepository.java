package com.rpm.rest.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rpm.rest.model.DeviceDataSimEntity;

@Repository
@Transactional
public interface DaoRepository extends CrudRepository<DeviceDataSimEntity, Long> {

	@Query(value = "select ddsd.ID from DVC_DEVICE_SIM_DETAILS ddsd where ddsd.SERIAL_NUMBER =:serialNo", nativeQuery = true)
	Optional<Integer>  getAllSimOfDevice(@Param("serialNo") String serialNo);

	@Modifying
	@Query(value = "UPDATE DVC_DEVICE_SIM_DETAILS b SET b.IS_DEVICE_ACTIVE = :action,b.DEVICE_UPDATE_DATE = sysdate WHERE b.id = :id", nativeQuery = true)
	void updateDeviceStatus(@Param("id") int paramInt, @Param("action") String action);

	@Modifying
	@Query(value = "UPDATE DVC_DEVICE_SIM_DETAILS b SET b.IS_SIM_SUSPENDED = :action,b.SIM_UPDATE_DATE = sysdate WHERE b.id = :id", nativeQuery = true)
	void updateDeviceSimStatus(@Param("id") int paramInt, @Param("action") String action);

	@Query(value = "select ddsd.SIM from DVC_DEVICE_SIM_DETAILS ddsd where ddsd.ID =:id ", nativeQuery = true)
	String getSimOfDevice(@Param("id") int id);

	/*
	 * @Query("select report from BulkReportEntity report where report.id = :id"
	 * ) DeviceEntity getAllSchduledReportById(@Param("id") int paramInt);
	 * 
	 * @Modifying
	 * 
	 * @Query(
	 * "UPDATE BulkReportEntity b SET b.status = :status,b.DISCRIPTION = :action WHERE b.id = :id"
	 * ) void updateStatus(@Param("id") int paramInt, @Param("status") String
	 * paramString1, @Param("action") String paramString2);
	 * 
	 * @Query(value =
	 * "SELECT * FROM PTL_BULK_REPORTS WHERE CREATED_ON <= TRUNC(SYSDATE) - 3 ",
	 * nativeQuery = true) List<DeviceEntity> reportgetbefor3days();
	 * 
	 * @Modifying
	 * 
	 * @Query("DELETE FROM BulkReportEntity e WHERE e.id = :id") void
	 * deleteReport(@Param("id") int paramInt);
	 */
}
