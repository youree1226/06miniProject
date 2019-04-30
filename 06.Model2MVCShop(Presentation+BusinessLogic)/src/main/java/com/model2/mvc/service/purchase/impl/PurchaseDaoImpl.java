package com.model2.mvc.service.purchase.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.domain.*;

@Repository
public class PurchaseDaoImpl implements PurchaseDao {
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	///C
	public PurchaseDaoImpl(){
		System.out.println(this.getClass());
	}
	

	public void insertPurchase(Purchase purchase) throws Exception {
		
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	public Purchase getPurchase(int tranNo) throws Exception {

		return  sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}
	
	public Purchase getPurchase2(int prodNo) throws Exception {

		return  sqlSession.selectOne("PurchaseMapper.getPurchase2", prodNo);
	}
	
	public  List<Product> getSaleList(Search search) throws Exception {
		
		return sqlSession.selectList("PurchaseMapper.getSaleList", search);
	}



	public  List<Product> getPurchaseList(Search search,String buyerId) throws Exception {
		Map map = new HashMap();
		map.put("search", search);
		map.put("buyerId", buyerId);
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}

	
	public void updatePurchase(Purchase purchase) throws Exception {
		
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);		
	}

	public void updateTranCode(Purchase purchase) throws Exception {
		
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}

	public void deletePurchase(int tranNo) throws Exception {

		sqlSession.delete("PurchaseMapper.deletePurchase", tranNo);		
	}	
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", search);	
	}


	
}
