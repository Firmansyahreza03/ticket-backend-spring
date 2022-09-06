package com.lawencon.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.ticket.dao.MasterProductDao;
import com.lawencon.ticket.dto.DeleteRes;
import com.lawencon.ticket.dto.InsertRes;
import com.lawencon.ticket.dto.InsertResData;
import com.lawencon.ticket.dto.UpdateRes;
import com.lawencon.ticket.dto.UpdateResData;
import com.lawencon.ticket.dto.masterproduct.FindAllMasterProductRes;
import com.lawencon.ticket.dto.masterproduct.FindByIdMasterProductRes;
import com.lawencon.ticket.dto.masterproduct.InsertMasterProductReq;
import com.lawencon.ticket.dto.masterproduct.MasterProductData;
import com.lawencon.ticket.dto.masterproduct.UpdateMasterProductReq;
import com.lawencon.ticket.model.MasterProduct;
import com.lawencon.ticket.service.MasterProductService;

@Service
public class MasterProductServiceImpl extends BaseServiceImpl implements MasterProductService {
	private MasterProductDao masterProductDao;

	@Autowired
	public void setMasterProductDao(MasterProductDao masterProductDao) {
		this.masterProductDao = masterProductDao;
	}

	@Override
	public FindByIdMasterProductRes findById(Long id) throws Exception {

		MasterProduct masterProductResult = masterProductDao.findById(id);

		MasterProductData data = new MasterProductData();
		data.setId(masterProductResult.getId());
		data.setProductName(masterProductResult.getProductName());
		data.setProductCode(masterProductResult.getProductCode());
		data.setIsActive(masterProductResult.getIsActive());
		data.setVersion(masterProductResult.getVersion());

		FindByIdMasterProductRes productRes = new FindByIdMasterProductRes();
		productRes.setData(data);
		return productRes;
	}

	@Override
	public FindAllMasterProductRes findAll() throws Exception {

		List<MasterProduct> masterProductResult = masterProductDao.findAll();

		List<MasterProductData> datas = new ArrayList<>();

		for (int i = 0; i < masterProductResult.size(); i++) {
			MasterProduct product = masterProductResult.get(i);
			MasterProductData data = new MasterProductData();
			data.setId(product.getId());
			data.setProductName(product.getProductName());
			data.setProductCode(product.getProductCode());
			data.setIsActive(product.getIsActive());
			data.setVersion(product.getVersion());
			datas.add(data);
		}

		FindAllMasterProductRes productRes = new FindAllMasterProductRes();
		productRes.setDatas(datas);
		return productRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertRes insert(InsertMasterProductReq masterProduct) throws Exception {

		MasterProduct product = new MasterProduct();
		product.setProductName(masterProduct.getProductName());
		product.setProductCode(masterProduct.getProductCode());
		product.setIsActive(masterProduct.getIsActive());
		product.setCreatedBy(getUserId());
		MasterProduct masterProductResult = masterProductDao.insert(product);

		InsertResData data = new InsertResData();
		data.setId(masterProductResult.getId());

		InsertRes insertRes = new InsertRes();
		insertRes.setData(data);
		insertRes.setMessage("Berhasil menambahkan data");
		return insertRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateRes updateById(UpdateMasterProductReq masterProduct) throws Exception {

		MasterProduct product = masterProductDao.findById(masterProduct.getId());
		product.setProductName(masterProduct.getProductName());
		product.setUpdatedBy(getUserId());
		product.setIsActive(masterProduct.getIsActive());
		product.setVersion(masterProduct.getVersion());
		MasterProduct masterProductResult = masterProductDao.update(product);

		UpdateResData data = new UpdateResData();
		data.setVersion(masterProductResult.getVersion());

		UpdateRes updateRes = new UpdateRes();
		updateRes.setData(data);
		updateRes.setMessage("Berhasil mengubah data");
		return updateRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteRes deleteById(Long id) throws Exception {

		Boolean masterProductResult = masterProductDao.deleteById(id);

		DeleteRes deleteRes = new DeleteRes();
		if (masterProductResult) {
			deleteRes.setMessage("Hapus berhasil");
		} else {
			deleteRes.setMessage("Hapus gagal");
		}
		return deleteRes;
	}
}
