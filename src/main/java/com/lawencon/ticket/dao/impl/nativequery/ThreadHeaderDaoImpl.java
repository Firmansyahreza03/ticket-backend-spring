package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.ThreadHeaderDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.model.CustomerProduct;
import com.lawencon.ticket.model.Employee;
import com.lawencon.ticket.model.File;
import com.lawencon.ticket.model.MasterProduct;
import com.lawencon.ticket.model.PicToCustomer;
import com.lawencon.ticket.model.Priority;
import com.lawencon.ticket.model.Status;
import com.lawencon.ticket.model.ThreadHeader;

@Profile("Native")
@Repository(value = "ThreadHeaderDaoImpl")
public class ThreadHeaderDaoImpl extends BaseEntityManager implements ThreadHeaderDao {

	@Override
	public ThreadHeader findById(Long id) throws Exception {
		ThreadHeader threadHeader = em.find(ThreadHeader.class, id);

		return threadHeader;
	}

	@Override
	public ThreadHeader insert(ThreadHeader threadHeader) throws Exception {
		em.persist(threadHeader);
		return threadHeader;
	}

	@Override
	public List<ThreadHeader> findAllByCustomerId(Long id) throws Exception {
		String sql = " SELECT tth.id, tth.code, tth.title, tth.description, "
				+ " tth.pic_to_user_id, tptc.pic_id, te.full_name, "
				+ " tptc.customer_id, tc.first_name, tc.last_name, tc.phone_numb, tc.address, "
				+ " tth.status_id, ts.status_name, ts.status_code, "
				+ " tth.priority_id, tp.priority_name, tp.priority_code, "
				+ " tth.product_customer_id, tcp.product_id, tmp.product_name, tth.file_id, tf.file, tf.extension, "
				+ " tth.is_active, tth.\"version\" " + " FROM t_thread_hdr tth "
				+ " INNER JOIN t_pic_to_customer tptc ON tptc.id = tth.pic_to_user_id "
				+ " INNER JOIN t_employee te ON te.id = tptc.pic_id"
				+ " INNER JOIN t_customer tc ON tc.id = tptc.customer_id "
				+ " INNER JOIN t_status ts ON ts.id = tth.status_id "
				+ " INNER JOIN t_priority tp ON tp.id = tth.priority_id "
				+ " INNER JOIN t_customer_product tcp ON tcp.id = tth.product_customer_id "
				+ " INNER JOIN t_m_product tmp ON tmp.id = tcp.product_id "
				+ " INNER JOIN t_file tf ON tf.id = tth.file_id " + " WHERE tc.id = :id "
				+ " ORDER BY tp.priority_point DESC";

		List<ThreadHeader> threadHeaders = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).setParameter("id", id).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			ThreadHeader threadHeader = new ThreadHeader();
			threadHeader.setId(Long.valueOf(objArr[0].toString()));
			threadHeader.setCode(objArr[1].toString());
			threadHeader.setTitle(objArr[2].toString());
			threadHeader.setDescription(objArr[3].toString());

			PicToCustomer ptc = new PicToCustomer();
			ptc.setId(Long.valueOf(objArr[4].toString()));

			Employee emp = new Employee();
			emp.setId(Long.valueOf(objArr[5].toString()));
			emp.setFullName(objArr[6].toString());
			ptc.setPic(emp);

			Customer cust = new Customer();
			cust.setId(Long.valueOf(objArr[7].toString()));
			cust.setFirstName(objArr[8].toString());
			cust.setLastName(objArr[9].toString());
			cust.setPhoneNumb(objArr[10].toString());
			cust.setAddress(objArr[11].toString());
			ptc.setCustomer(cust);
			threadHeader.setPicToCustomer(ptc);

			Status status = new Status();
			status.setId(Long.valueOf(objArr[12].toString()));
			status.setStatusName(objArr[13].toString());
			status.setStatusCode(objArr[14].toString());
			threadHeader.setStatus(status);

			Priority priority = new Priority();
			priority.setId(Long.valueOf(objArr[15].toString()));
			priority.setPriorityName(objArr[16].toString());
			priority.setPriorityCode(objArr[17].toString());
			threadHeader.setPriority(priority);

			CustomerProduct custProduct = new CustomerProduct();
			custProduct.setId(Long.valueOf(objArr[18].toString()));

			MasterProduct masterProduct = new MasterProduct();
			masterProduct.setId(Long.valueOf(objArr[19].toString()));
			masterProduct.setProductName(objArr[20].toString());
			custProduct.setProduct(masterProduct);
			threadHeader.setProductCustomer(custProduct);

			File file = new File();
			file.setId(Long.valueOf(objArr[21].toString()));
			file.setFileName(objArr[22].toString());
			file.setExtension(objArr[23].toString());
			threadHeader.setFile(file);

			threadHeader.setIsActive(Boolean.valueOf(objArr[24].toString()));
			threadHeader.setVersion(Integer.valueOf(objArr[25].toString()));

			threadHeaders.add(threadHeader);
		});
		return threadHeaders;
	}

	@Override
	public ThreadHeader updateStatus(ThreadHeader header) throws Exception {

		ThreadHeader threadHeaderUpdate = em.merge(header);
		em.flush();

		return threadHeaderUpdate;
	}
	
	@Override
	public List<ThreadHeader> findAllByPicId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String searchByCode(String code) throws Exception {
		String sql = " SELECT tth.code FROM t_thread_header tth WHERE tth.code = :code ";
		String threadCode = (String) em.createNativeQuery(sql).setParameter("code", code).getSingleResult();
		return threadCode;
	}
	
	@Override
	public List<ThreadHeader> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
