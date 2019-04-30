package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		purchase.setBuyer(userService.getUser("user01"));
		purchase.setPurchaseProd(productService.getProduct(10040));
		purchase.setPaymentOption("1");

		purchaseService.addPurchase(purchase);
		System.out.println(purchase);
		
		System.out.println("==================================");


		//==> console Ȯ��
		//System.out.println(user);
		//==> API Ȯ��

	}
	
	//@Test
	public void testFindPurchase() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10000);
		System.out.println(purchase);
		
		//==> API Ȯ��
		Assert.assertEquals(10000, purchase.getPurchaseProd().getProdNo());

		Assert.assertNotNull( purchaseService.getPurchase(10000));
	}
	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10000);
		System.out.println(purchase);
		Assert.assertEquals(10000, purchase.getPurchaseProd().getProdNo());
		
//		purchase.setTranCode("1");
//
//		purchaseService.updateTranCode(purchase);
		
		purchase.setReceiverName("����");
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10000);

		System.out.println(purchase);
		

	 }
	 
	//==>  �ּ��� Ǯ�� �����ϸ�....
	//@Test
	public void testGetPurchaseList() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword("user01");
	 	
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user01");
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(3, list.size());
	 	System.out.println(list);
	 	System.out.println("list.size() : "+list.size());

	 	
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
		//==> console Ȯ��
	 	//System.out.println(list);

	 }
	 
	@Test
	public void testGetSaleListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	//search.setSearchCondition("1");
	 	Map<String,Object> map = purchaseService.getSaleList(search);
	 	System.out.println(map);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	System.out.println(list);
	 	System.out.println("list.size() : "+list.size());
	 	//Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println("totalCount"+totalCount);
	 	
	 	System.out.println("=======================================");

	 }
	 
	
}