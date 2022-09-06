package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.CustomerProductDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.model.CustomerProduct;
import com.lawencon.ticket.model.MasterProduct;

@Profile("Native")
@Repository(value = "CustomerProductDaoImpl")
public class CustomerProductDaoImpl extends BaseEntityManager implements CustomerProductDao {

	@Override
	public CustomerProduct findById(Long id) throws Exception {
		CustomerProduct custProduct = em.find(CustomerProduct.class, id);
		return custProduct;
	}

	@Override
	public List<CustomerProduct> findAll() throws Exception {
		String sql = " SELECT * FROM t_customer_product te ";
		List<CustomerProduct> custProducts = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			CustomerProduct customerProduct = new CustomerProduct();
			customerProduct.setId(Long.valueOf(objArr[0].toString()));

			Customer customer = new Customer();
			customer.setId(Long.valueOf(objArr[1].toString()));
			customerProduct.setCustomer(customer);

			MasterProduct masterProduct = new MasterProduct();
			masterProduct.setId(Long.valueOf(objArr[2].toString()));
			customerProduct.setProduct(masterProduct);

			custProducts.add(customerProduct);
		});

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
		String sql = " DELETE FROM t_customer_product WHERE id = :id ";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}

	@Override
	public List<CustomerProduct> findAllProduct(Long id) throws Exception {
		String sql = " SELECT tcp.id, tcp.customer_id, tc.first_name, tc.last_name, tc.phone_numb, tc.address, "
				+ " tcp.product_id, tmp.product_name, tmp.product_code, "
				+ " tcp.created_by, tcp.created_at, tcp.is_active, tcp.\"version\" " + " FROM t_customer_product tcp "
				+ " INNER JOIN t_customer tc ON tc.id = tcp.customer_id "
				+ " INNER JOIN t_m_product tmp ON tmp.id = tcp.product_id " + " WHERE tcp.customer_id = :id ";

		List<CustomerProduct> custProducts = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).setParameter("id", id).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			CustomerProduct custProduct = new CustomerProduct();
			custProduct.setId(Long.valueOf(objArr[0].toString()));

			Customer cust = new Customer();
			cust.setId(Long.valueOf(objArr[1].toString()));
			cust.setFirstName(objArr[2].toString());
			cust.setLastName(objArr[3].toString());
			cust.setPhoneNumb(objArr[4].toString());
			cust.setAddress(objArr[5].toString());
			custProduct.setCustomer(cust);

			MasterProduct product = new MasterProduct();
			product.setId(Long.valueOf(objArr[6].toString()));
			product.setProductName(objArr[7].toString());
			product.setProductCode(objArr[8].toString());
			custProduct.setProduct(product);

			custProduct.setIsActive(Boolean.valueOf(objArr[11].toString()));
			custProduct.setVersion(Integer.valueOf(objArr[12].toString()));

			custProducts.add(custProduct);
		});

		return custProducts;
	}
	
	@Override
	public CustomerProduct findByProductId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
