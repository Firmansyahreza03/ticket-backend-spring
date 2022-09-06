package com.lawencon.ticket.dao.impl.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.PicToCustomerDao;
import com.lawencon.ticket.model.PicToCustomer;
import com.lawencon.ticket.repo.PicToCustomerRepo;

@Profile(value = "springjpa")
@Repository
public class PicToCustomerSpringJpaImpl implements PicToCustomerDao {

	private PicToCustomerRepo customerRepo;

	@Autowired
	public void setCustomerRepo(PicToCustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public PicToCustomer findById(Long id) throws Exception {
		return customerRepo.findById(id).get();
	}

	@Override
	public Long findIdFromCustomer(Long id) throws Exception {
		Long result = customerRepo.findIdFromCustomer(id);
		return result;
	}

	@Override
	public List<PicToCustomer> findAll() throws Exception {
		return customerRepo.findAll();
	}

	@Override
	public List<PicToCustomer> findAllCustomer(Long id) throws Exception {
		List<PicToCustomer> res = customerRepo.findByPicUserId(id);
		return res;
	}

	@Override
	public PicToCustomer insert(PicToCustomer picToCustomer) throws Exception {
		return customerRepo.save(picToCustomer);
	}

	@Override
	public PicToCustomer update(PicToCustomer picToCustomer) throws Exception {
		return customerRepo.saveAndFlush(picToCustomer);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		int res = customerRepo.removeById(id);
		return res > 0;
	}
	
	@Override
	public PicToCustomer findByCustomerId(Long id) throws Exception {
		PicToCustomer res = customerRepo.findByCustomerId(id);
		return res;
	}
}
