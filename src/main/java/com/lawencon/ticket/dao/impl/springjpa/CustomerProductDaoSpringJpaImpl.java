package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.CustomerProductDao;
import com.lawencon.ticket.model.CustomerProduct;
import com.lawencon.ticket.repo.CustomerProductRepo;

@Profile(value = "springjpa")
@Repository
public class CustomerProductDaoSpringJpaImpl implements CustomerProductDao {

	private CustomerProductRepo productRepo;

	@Autowired
	public void setProductRepo(CustomerProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public CustomerProduct findById(Long id) throws Exception {
		return productRepo.findById(id).get();
	}

	@Override
	public List<CustomerProduct> findAllProduct(Long id) throws Exception {
		List<CustomerProduct> res = productRepo.findByCustomerUserId(id);
		return res;
	}

	@Override
	public List<CustomerProduct> findAll() throws Exception {
		return productRepo.findAll();
	}

	@Override
	public CustomerProduct insert(CustomerProduct product) throws Exception {
		return productRepo.save(product);
	}

	@Override
	public CustomerProduct update(CustomerProduct product) throws Exception {
		return productRepo.saveAndFlush(product);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int res = productRepo.removeById(id);
		return res > 0;
	}
	
	@Override
	public CustomerProduct findByProductId(Long id) throws Exception {
		CustomerProduct res = productRepo.findByProductId(id);
		return res;
	}
}
