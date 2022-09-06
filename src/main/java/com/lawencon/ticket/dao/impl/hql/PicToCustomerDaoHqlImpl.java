package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.PicToCustomerDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.PicToCustomer;

@Profile("HQL")
@Repository(value = "PicToCustomerDaoHqlImpl")
public class PicToCustomerDaoHqlImpl extends BaseEntityManager implements PicToCustomerDao {

	@Override
	public PicToCustomer findById(Long id) throws Exception {
		PicToCustomer ptc = em.find(PicToCustomer.class, id);
		return ptc;
	}

	@Override
	public List<PicToCustomer> findAll() throws Exception {
		String sql = "FROM PicToCustomer";

		List<PicToCustomer> ptcs = new ArrayList<>();
		ptcs = em.createQuery(sql, PicToCustomer.class).getResultList();
		return ptcs;
	}

	@Override
	public PicToCustomer insert(PicToCustomer picToCustomer) throws Exception {
		em.persist(picToCustomer);
		return picToCustomer;
	}

	@Override
	public PicToCustomer update(PicToCustomer picToCustomer) throws Exception {
		PicToCustomer picToCustomerUpdate = em.merge(picToCustomer);
		em.flush();
		return picToCustomerUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM PicToCustomer WHERE id = :id";
		int res = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public List<PicToCustomer> findAllCustomer(Long id) throws Exception {
		String sql = " FROM PicToCustomer tptc " + " WHERE tptc.pic.id = :id";

		List<PicToCustomer> ptcs = em.createQuery(sql, PicToCustomer.class).setParameter("id", id).getResultList();
		return ptcs;
	}

	@Override
	public Long findIdFromCustomer(Long id) throws Exception {
		String sql = " SELECT tptc.id FROM PicToCustomer tptc " + " WHERE tptc.customer.id = :id ";

		try {
			Object result = em.createQuery(sql).setParameter("id", id).getSingleResult();
			Long idPicToCustomer = Long.valueOf(result.toString());
			return idPicToCustomer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public PicToCustomer findByCustomerId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
