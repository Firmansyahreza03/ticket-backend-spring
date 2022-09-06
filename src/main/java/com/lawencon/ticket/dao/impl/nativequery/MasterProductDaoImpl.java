package com.lawencon.ticket.dao.impl.nativequery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticket.dao.MasterProductDao;
import com.lawencon.ticket.entitymanager.BaseEntityManager;
import com.lawencon.ticket.model.MasterProduct;

@Profile("Native")
@Repository(value = "MasterProductDaoImpl")
public class MasterProductDaoImpl extends BaseEntityManager implements MasterProductDao {

	@Override
	public MasterProduct findById(Long id) throws Exception {
		MasterProduct masterProduct = em.find(MasterProduct.class, id);
		return masterProduct;
	}

	@Override
	public List<MasterProduct> findAll() throws Exception {
		String sql = " SELECT * FROM t_m_product";

		List<MasterProduct> masterProducts = new ArrayList<>();
		List<?> result = em.createNativeQuery(sql).getResultList();
		result.forEach(obj -> {
			Object[] objArr = (Object[]) obj;
			MasterProduct masterProduct = new MasterProduct();
			masterProduct.setId(Long.valueOf(objArr[0].toString()));
			masterProduct.setProductName(objArr[1].toString());
			masterProduct.setProductCode(objArr[2].toString());
			masterProduct.setIsActive(Boolean.valueOf(objArr[7].toString()));
			masterProduct.setVersion(Integer.valueOf(objArr[8].toString()));

			masterProducts.add(masterProduct);
		});

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
		String sql = " DELETE FROM t_m_product WHERE id = :id";
		int res = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return res > 0;
	}
}
