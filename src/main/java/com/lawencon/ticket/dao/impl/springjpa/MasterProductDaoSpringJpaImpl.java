package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.MasterProductDao;
import com.lawencon.ticket.model.MasterProduct;
import com.lawencon.ticket.repo.MasterProductRepo;

@Profile(value = "springjpa")
@Repository
public class MasterProductDaoSpringJpaImpl implements MasterProductDao {

	private MasterProductRepo productRepo;

	@Autowired
	public void setProductRepo(MasterProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public MasterProduct findById(Long id) throws Exception {
		return productRepo.findById(id).get();
	}

	@Override
	public List<MasterProduct> findAll() throws Exception {
		return productRepo.findAll();
	}

	@Override
	public MasterProduct insert(MasterProduct masterProduct) throws Exception {
		return productRepo.save(masterProduct);
	}

	@Override
	public MasterProduct update(MasterProduct masterProduct) throws Exception {
		return productRepo.saveAndFlush(masterProduct);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int result = productRepo.removeById(id);
		return result > 0;
	}
}
