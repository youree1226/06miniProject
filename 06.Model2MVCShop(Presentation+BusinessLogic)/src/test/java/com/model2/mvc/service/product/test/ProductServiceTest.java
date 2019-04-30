package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		//Product.setProdNo(10101);
		product.setProdName("testProdName");
		product.setManuDate("11111111");
		product.setProdDetail("testProdDetail");
		product.setPrice(120408);
		
		productService.addProduct(product);
		
		Assert.assertNotNull("testProdName", product.getProdName());

	}
	
	//@Test
	public void testfindProduct() throws Exception {
		
		Product product = productService.getProduct(10042);

		
		//==> API 확인
		Assert.assertEquals(10042, product.getProdNo());
		Assert.assertEquals("testProdName",product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals(120408, product.getPrice());

		Assert.assertNotNull( productService.getProduct(10042));
	}
	
	//@Test
	 public void testUpdateUser() throws Exception{
		 
		Product Product = productService.getProduct(10042);

		Assert.assertNotNull(Product);
		
		Product.setProdName("change");

		productService.updateProduct(Product);
		
		Product = productService.getProduct(10042);
		Assert.assertNotNull(Product);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		Assert.assertEquals("change", Product.getProdName());
		//Assert.assertEquals(null, Product.getProdDetail());
		//detail안넣고 쿼리 날려도 null로 세팅되는게 아니라 기존 것 그대로 있음
		Assert.assertEquals(120408, Product.getPrice());
	 }
	 
	 //==>  주석을 풀고 실행하면....
	@Test
	public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	//@Test
	public void testGetProductListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("testProdName");
	 	Map<String,Object> map = productService.getProductList(search);
	 	System.out.println(map);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
//	 	search.setSearchCondition("1");
//	 	search.setSearchKeyword("%test%");
//	 	map = productService.getProductList(search);
//	 	
//	 	list = (List<Object>)map.get("list");
//	 	///Assert.assertEquals(0, list.size());
//	 	System.out.println(map);
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	
}