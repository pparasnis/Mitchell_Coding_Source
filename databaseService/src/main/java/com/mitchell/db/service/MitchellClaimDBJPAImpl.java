package com.mitchell.db.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.mitchell.db.iService.IMitchellClaimDBInterface;
import com.mitchell.db.utils.EntityManagerProvider;
import com.mitchell.db.utils.NullAwareBeanUtilsBean;
import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.VehicleInfoType;

public class MitchellClaimDBJPAImpl implements IMitchellClaimDBInterface {

	private EntityManagerFactory getFactory() {
		EntityManagerProvider entityManagerProvider = EntityManagerProvider
				.getInstance();
		return entityManagerProvider.getEntityManagerFactory();
	}

	@Override
	public void createClaim(MitchellClaimType mitchellClaimType)
			throws Exception {
		EntityManager em = getFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(mitchellClaimType);
		em.getTransaction().commit();
		em.close();
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClaim(MitchellClaimType mitchellClaimType)
			throws Exception {
		EntityManager em = getFactory().createEntityManager();
		TypedQuery<MitchellClaimType> query = em
				.createQuery(
						"SELECT mct FROM MitchellClaimType mct where mct.claimNumber=:arg1",
						MitchellClaimType.class);
		query.setParameter("arg1", mitchellClaimType.getClaimNumber());
		MitchellClaimType mitchellClaimTypeOrig = query.getSingleResult();
		mitchellClaimType.setId(mitchellClaimTypeOrig.getId());
		BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
		if (mitchellClaimType.getVehicles() != null
				&& mitchellClaimTypeOrig.getVehicles() != null) {
			mitchellClaimType.getVehicles().setId(
					mitchellClaimTypeOrig.getVehicles().getId());
		}
		if (mitchellClaimType.getVehicles().getVehicleDetails().size() > 0) {
			for (VehicleInfoType vehicleInfoType : mitchellClaimType
					.getVehicles().getVehicleDetails()) {
				String vin = vehicleInfoType.getVin();
				if (mitchellClaimTypeOrig.getVehicles().getVehicleDetails()
						.size() > 0) {
					for (VehicleInfoType vehicleInfoTypeOrig : mitchellClaimTypeOrig
							.getVehicles().getVehicleDetails()) {
						if (vin.equals(vehicleInfoTypeOrig.getVin())) {
							notNull.copyProperties(vehicleInfoTypeOrig,
									vehicleInfoType);
							mitchellClaimType.getVehicles().getVehicleDetails()
									.remove(vehicleInfoType);
							mitchellClaimType.getVehicles().getVehicleDetails()
									.add(vehicleInfoTypeOrig);

						} else {
							mitchellClaimType.getVehicles().getVehicleDetails()
									.add(vehicleInfoTypeOrig);
						}
					}
				}
			}
		}
		notNull.copyProperties(mitchellClaimTypeOrig, mitchellClaimType);

		em.getTransaction().begin();
		em.merge(mitchellClaimTypeOrig);
		em.getTransaction().commit();
		em.close();

		// TODO Auto-generated method stub

	}

	@Override
	public List<MitchellClaimType> findClaimsByLossDate(Date startDate,
			Date endDate) throws Exception {
		EntityManager em = getFactory().createEntityManager();
		TypedQuery<MitchellClaimType> query = em
				.createQuery(
						"SELECT mct FROM MitchellClaimType mct where mct.lossDate BETWEEN :arg1 and :arg2",
						MitchellClaimType.class);
		query.setParameter("arg1", startDate);
		query.setParameter("arg2", endDate);

		List<MitchellClaimType> mitchellClaimTypeList;

		mitchellClaimTypeList = query.getResultList();

		System.out.println(mitchellClaimTypeList);
		return mitchellClaimTypeList;
	}

	@Override
	public MitchellClaimType findClaimByClaimId(String claimId)
			throws Exception {
		EntityManager em = getFactory().createEntityManager();
		TypedQuery<MitchellClaimType> query = em
				.createQuery(
						"SELECT mct FROM MitchellClaimType mct where mct.claimNumber=:arg1",
						MitchellClaimType.class);
		query.setParameter("arg1", claimId);
		MitchellClaimType mitchellClaimType;

		mitchellClaimType = query.getSingleResult();

		System.out.println(mitchellClaimType);
		return mitchellClaimType;
	}

	@Override
	public void deleteClaimByClaimId(String claimId) throws Exception {
		EntityManager em = getFactory().createEntityManager();
		TypedQuery<MitchellClaimType> query = em
				.createQuery(
						"SELECT mct FROM MitchellClaimType mct where mct.claimNumber=:arg1",
						MitchellClaimType.class);
		query.setParameter("arg1", claimId);
		MitchellClaimType mitchellClaimType = query.getSingleResult();
		em.getTransaction().begin();
		em.remove(mitchellClaimType);
		em.getTransaction().commit();
		em.close();

	}

}
