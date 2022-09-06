package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.dao.CustomerDao;
import com.lawencon.ticket.dao.CustomerProductDao;
import com.lawencon.ticket.dao.MasterProductDao;
import com.lawencon.ticket.dao.UserDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.customerproduct.CustomerProductData;
import com.lawencon.ticket.dto.customerproduct.FindAllByCustCustomerProductRes;
import com.lawencon.ticket.dto.customerproduct.FindAllCustomerProductRes;
import com.lawencon.ticket.dto.customerproduct.FindByIdCustomerProductRes;
import com.lawencon.ticket.dto.customerproduct.InsertCustomerProductReq;
import com.lawencon.ticket.dto.customerproduct.UpdatedByIdCustomerProductReq;
import com.lawencon.ticket.model.Customer;
import com.lawencon.ticket.model.CustomerProduct;
import com.lawencon.ticket.model.MasterProduct;
import com.lawencon.ticket.model.User;
import com.lawencon.ticket.service.CustomerProductService;

@Service
public class CustomerProductServiceImpl extends BaseServiceImpl implements CustomerProductService {
	private CustomerProductDao customerProductDao;
	private MasterProductDao productDao;
	private CustomerDao customerDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Autowired
	public void setProductDao(MasterProductDao productDao) {
		this.productDao = productDao;
	}

	@Autowired
	public void setCustomerProductDao(CustomerProductDao customerProductDao) {
		this.customerProductDao = customerProductDao;
	}

	@Override
	public FindByIdCustomerProductRes findById(Long id) throws Exception {

		CustomerProduct custProRes = customerProductDao.findById(id);

		CustomerProductData data = new CustomerProductData();
		data.setId(custProRes.getId());
		data.setCustomerName(custProRes.getCustomer().getFirstName() + " " + custProRes.getCustomer().getLastName());
		data.setProductName(custProRes.getProduct().getProductName());
		data.setIsActive(custProRes.getIsActive());
		data.setVersion(custProRes.getVersion());
		data.setCustomerId(custProRes.getCustomer().getId());
		data.setProductId(custProRes.getProduct().getId());

		FindByIdCustomerProductRes productRes = new FindByIdCustomerProductRes();
		productRes.setData(data);
		return productRes;
	}

	@Override
	public FindAllCustomerProductRes findAll() throws Exception {

		List<CustomerProduct> custProRes = customerProductDao.findAll();
		List<CustomerProductData> datas = new ArrayList<>();
		for (int i = 0; i < custProRes.size(); i++) {
			CustomerProductData data = new CustomerProductData();
			CustomerProduct product = custProRes.get(i);
			data.setId(product.getId());
			data.setCustomerId(product.getCustomer().getId());
			data.setCustomerName(product.getCustomer().getFirstName() + " " + product.getCustomer().getLastName());
			data.setProductId(product.getProduct().getId());
			data.setProductName(product.getProduct().getProductName());
			data.setIsActive(product.getIsActive());
			data.setVersion(product.getVersion());
			datas.add(data);
		}

		FindAllCustomerProductRes productRes = new FindAllCustomerProductRes();
		productRes.setDatas(datas);
		return productRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertCustomerProductReq custProduct) throws Exception {

		CustomerProduct customerProduct = new CustomerProduct();

		MasterProduct product = productDao.findById(custProduct.getProductId());
		customerProduct.setProduct(product);

		Customer customer = customerDao.findById(custProduct.getCustomerId());
		customerProduct.setCustomer(customer);
		customerProduct.setCreatedBy(getUserId());

		customerProduct.setIsActive(custProduct.getIsActive());
		CustomerProduct custProRes = customerProductDao.insert(customerProduct);

		InsertResData data = new InsertResData();
		data.setId(custProRes.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");

		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdatedByIdCustomerProductReq productReq) throws Exception {
		CustomerProduct customerProduct = customerProductDao.findById(productReq.getId());
		Customer customer = customerDao.findById(productReq.getCustomerId());
		customerProduct.setCustomer(customer);

		MasterProduct product = productDao.findById(productReq.getProductId());
		customerProduct.setProduct(product);
		customerProduct.setUpdatedBy(getUserId());
		customerProduct.setIsActive(productReq.getIsActive());
		customerProduct.setVersion(productReq.getVersion());
		CustomerProduct customerProductRes = customerProductDao.update(customerProduct);

		UpdateResData data = new UpdateResData();
		data.setVersion(customerProductRes.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Berhasil mengubah data");
		return updateRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		Boolean customerProductRes = customerProductDao.deleteById(id);

		DeleteRes deleteRes = new DeleteRes();
		if (customerProductRes) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}

	@Override
	public FindAllByCustCustomerProductRes findAllProductByCust() throws Exception {

		User customer = userDao.findById(getUserId());
		List<CustomerProduct> customerProductRes = customerProductDao.findAllProduct(customer.getId());
		List<CustomerProductData> datas = new ArrayList<>();
		for (int i = 0; i < customerProductRes.size(); i++) {
			CustomerProductData data = new CustomerProductData();
			CustomerProduct product = customerProductRes.get(i);
			data.setId(product.getId());
			data.setCustomerId(product.getCustomer().getId());
			data.setCustomerName(product.getCustomer().getFirstName() + " " + product.getCustomer().getLastName());
			data.setProductId(product.getProduct().getId());
			data.setProductName(product.getProduct().getProductName());
			data.setIsActive(product.getIsActive());
			data.setVersion(product.getVersion());
			datas.add(data);
		}

		FindAllByCustCustomerProductRes productRes = new FindAllByCustCustomerProductRes();
		productRes.setDatas(datas);
		return productRes;
	}
}
