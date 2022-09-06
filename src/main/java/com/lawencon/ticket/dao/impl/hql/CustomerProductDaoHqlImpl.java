package com.lawencon.ticket.dao.impl.hql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.CustomerProductDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.CustomerProduct;

@Profile("HQL")
@Repository(value = "CustomerProductDaoHqlImpl")
public class CustomerProductDaoHqlImpl extends BaseEntityManager implements CustomerProductDao {

	@Override
	public CustomerProduct findById(Long id) throws Exception {
		CustomerProduct custProduct = em.find(CustomerProduct.class, id);
		return custProduct;
	}

	@Override
	public List<CustomerProduct> findAll() throws Exception {
		String sql = " FROM CustomerProduct te ";
		List<CustomerProduct> custProducts = new ArrayList<>();
		custProducts = em.createQuery(sql, CustomerProduct.class).getResultList();
		return custProducts;
	}

	@Override
	public CustomerProduct insert(CustomerProduct product) throws Exception {
		em.persist(product);
		return product;
	}

	@Override
	public CustomerProduct update(CustomerProduct product) throws Exception {
		CustomerProduct customerProductUpdate = em.merge(product);
		em.flush();

		return customerProductUpdate;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = " DELETE FROM CustomerProduct WHERE id = :id ";
		int res = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public List<CustomerProduct> findAllProduct(Long id) throws Exception {
		String sql = " FROM CustomerProduct tcp " + " WHERE tcp.customer.id = :id ";

		List<CustomerProduct> custProducts = em.createQuery(sql, CustomerProduct.class).setParameter("id", id)
				.getResultList();
		return custProducts;
	}
	
	@Override
	public CustomerProduct findByProductId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
