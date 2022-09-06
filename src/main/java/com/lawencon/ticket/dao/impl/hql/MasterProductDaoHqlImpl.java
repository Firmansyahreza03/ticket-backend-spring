package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.MasterProductDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.MasterProduct;

@Profile("HQL")
@Repository(value = "MasterProductDaoHqlImpl")
public class MasterProductDaoHqlImpl extends BaseEntityManager implements MasterProductDao {

	@Override
	public MasterProduct findById(Long id) throws Exception {
		MasterProduct masterProduct = em.find(MasterProduct.class, id);
		return masterProduct;
	}

	@Override
	public List<MasterProduct> findAll() throws Exception {
		String sql = " FROM MasterProduct";

		List<MasterProduct> masterProducts = new ArrayList<>();
		masterProducts = em.createQuery(sql, MasterProduct.class).getResultList();
		return masterProducts;
	}

	@Override
	public MasterProduct insert(MasterProduct masterProduct) throws Exception {
		em.persist(masterProduct);
		return masterProduct;
	}

	@Override
	public MasterProduct update(MasterProduct masterProduct) throws Exception {

		MasterProduct productUpdate = em.merge(masterProduct);
		em.flush();
		return productUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM MasterProduct WHERE id = :id ";
		int res = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
}
