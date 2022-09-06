package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.PicToCustomerDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.model.Employee;
import com.lawencon.ticket.model.PicToCustomer;

@Profile("Native")
@Repository(value = "PicToCustomerDaoImpl")
public class PicToCustomerDaoImpl extends BaseEntityManager implements PicToCustomerDao {

	@Override
	public PicToCustomer findById(Long id) throws Exception {
		PicToCustomer ptc = em.find(PicToCustomer.class, id);
		return ptc;
	}

	@Override
	public List<PicToCustomer> findAll() throws Exception {
		String sql = " SELECT tptc.id, tptc.customer_id, tc.first_name, tc.last_name, tptc.pic_id, te.full_name, "
				+ " tptc.is_active, tptc.\"version\" "
				+ " FROM t_pic_to_customer tptc " + " INNER JOIN t_customer tc ON tptc.customer_id = tc.id "
				+ " INNER JOIN t_employee te ON tptc.pic_id = te.id ";

		List<PicToCustomer> ptcs = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			PicToCustomer ptc = new PicToCustomer();
			ptc.setId(Long.valueOf(objArr[0].toString()));

			Customer cust = new Customer();
			cust.setId(Long.valueOf(objArr[1].toString()));
			cust.setFirstName(objArr[2].toString());
			cust.setLastName(objArr[3].toString());
			ptc.setCustomer(cust);
			
			Employee emp = new Employee();
			emp.setId(Long.valueOf(objArr[4].toString()));
			emp.setFullName(objArr[5].toString());
			ptc.setPic(emp);
			
			ptc.setIsActive(Boolean.valueOf(objArr[6].toString()));
			ptc.setVersion(Integer.valueOf(objArr[7].toString()));

			ptcs.add(ptc);
		});

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
		String sql = " DELETE FROM t_pic_to_customer WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public List<PicToCustomer> findAllCustomer(Long id) throws Exception {
		String sql = " SELECT tptc.id, tptc.pic_id, te.full_name, "
				+ " tptc.customer_id, tc.first_name, tc.last_name, tc.phone_numb, tc.address, tptc.is_active, tptc.\"version\" "
				+ " FROM t_pic_to_customer tptc " + " INNER JOIN t_employee te ON te.id = tptc.pic_id "
				+ " INNER JOIN t_customer tc ON tc.id = tptc.customer_id " + " WHERE tptc.pic_id = :id";

		List<PicToCustomer> ptcs = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).setParameter("id", id).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			PicToCustomer ptc = new PicToCustomer();
			ptc.setId(Long.valueOf(objArr[0].toString()));

			Employee emp = new Employee();
			emp.setId(Long.valueOf(objArr[1].toString()));
			emp.setFullName(objArr[2].toString());
			ptc.setPic(emp);

			Customer cust = new Customer();
			cust.setId(Long.valueOf(objArr[3].toString()));
			cust.setFirstName(objArr[4].toString());
			cust.setLastName(objArr[5].toString());
			cust.setPhoneNumb(objArr[6].toString());
			cust.setAddress(objArr[7].toString());
			ptc.setCustomer(cust);

			ptc.setIsActive(Boolean.valueOf(objArr[8].toString()));
			ptc.setVersion(Integer.valueOf(objArr[9].toString()));
			ptcs.add(ptc);
		});

		return ptcs;
	}

	@Override
	public Long findIdFromCustomer(Long id) throws Exception {
		String sql = " SELECT tptc.id FROM t_pic_to_customer tptc INNER JOIN t_customer tc ON tptc.customer_id = tc.id "
				+ " WHERE tptc.customer_id = :id ";

		try {
			Object result = em.createNativeQuery(sql).setParameter("id", id).getSingleResult();
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
