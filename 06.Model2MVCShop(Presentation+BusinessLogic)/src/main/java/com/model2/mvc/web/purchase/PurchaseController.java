package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserDaoImpl;


//==> 회원관리 Controller
@Controller
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
		
	public PurchaseController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addPurchaseView.do")
	public String addPurchaseView(HttpServletRequest request, Model model) throws Exception {

		System.out.println("==============================");
		System.out.println("AddPurchaseViewAction - START"); 
		
		Product product = null;
		
		int prodNo = Integer.parseInt(request.getParameter("prod_no"));
		System.out.println("AddPurchaseViewAction - prodNo : "+prodNo);
		
		product = productService.getProduct(prodNo);
		
		System.out.println(product);
		
		model.addAttribute("product", product);
		
		System.out.println("AddPurchaseViewAction - END"); 
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase( HttpServletRequest request, Model model) throws Exception {
		
		System.out.println("==============================");
		System.out.println("AddPurchaseAction - START"); 
		
		
		ProductDao productDAO = new ProductDaoImpl();
		Purchase purchase = new Purchase();
		Product product = productDAO.findProduct(Integer.parseInt(request.getParameter("prodNo")));
		User user = new UserDaoImpl().getUser(request.getParameter("buyerId"));
		
		//setting할 때, tranNo는 받지않는다. DB에서 생성할 것이므로(상품번호처럼)
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setTranCode("1");//tranCode가 null이면 판매중, "1"이면 판매완료, "2"면 배송시작, "3"이면 배송완료로 설정하겠다.
		product.setProTranCode("1");
		//orderDate도 현재 날짜를 입력해야하는 것이므로, 사용자로부터 입력받지않고 DB에 입력할때 sysdate으로 넣는다.
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		
		System.out.println("AddPurchaseAction - purchaseVO : "+purchase);
		
		purchaseService.addPurchase(purchase);
		
		purchase = purchaseService.getPurchase2(purchase.getPurchaseProd().getProdNo());
		model.addAttribute("purchase", purchase);
		
		System.out.println("AddPurchaseAction - END"); 
		
		return "forward:/purchase/addPurchase.jsp";
		
	}
	
	@RequestMapping("/getPurchase.do")
	public String getPurchase( HttpServletRequest request , Model model ) throws Exception {
		
		System.out.println("==============================");
		System.out.println("GetPurchaseAction - START"); 
		
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		purchase = purchaseService.getPurchase(purchase.getTranNo());
		model.addAttribute("purchase", purchase);
		
		if (request.getParameter("update")!=null) {
			request.setAttribute("update", "update");
		}
		System.out.println("GetPurchaseAction - END"); 
				
		return "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping("/updatePurchaseView.do")
	public String updatePurchaseView(HttpServletRequest request, Model model ) throws Exception{

		System.out.println("==============================");
		System.out.println("UpdatePurchaseViewAction - START"); 
		
		//String returnPage = "forward:/purchase/updatePurchaseView.jsp";
		Purchase purchase = null;
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));

		purchase = purchaseService.getPurchase(tranNo);
		
//		if (purchase.getTranCode().equals("3")) {
//			returnPage = "forward:/purchase/updatePurchaseView.jsp"
//		}
		
		System.out.println(purchase);
		
		model.addAttribute("purchase", purchase);
		
		System.out.println("UpdatePurchaseViewAction - END");
		
		//return returnPage;
		return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	@RequestMapping("/updatePurchase.do")
	public String updatePurchase( HttpServletRequest request , Model model ) throws Exception{

		System.out.println("==============================");
		System.out.println("UpdatePurchaseAction - START"); 
		
		
		ProductDao productDAO = new ProductDaoImpl();
		Purchase purchase = new Purchase();
		
		if (request.getParameter("prodNo") != null) {
			purchase.setPurchaseProd(productDAO.findProduct(Integer.parseInt(request.getParameter("prodNo"))));
		}else if(request.getParameter("tranNo") != null){
			purchase.setPurchaseProd(productDAO.findProduct(Integer.parseInt(request.getParameter("tranNo"))));
		}
		
		purchase.setBuyer(new UserDaoImpl().getUser(request.getParameter("buyerId")));
		purchase.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));

		System.out.println("UpdatePurchaseAction - "+purchase);
		
		purchaseService.updatePurchase(purchase);
		
		System.out.println("UpdatePurchaseAction - END"); 
		
		return "forward:/getPurchase.do?update=update&tranNo="+purchase.getTranNo();
	}
	
	@RequestMapping("/updateTranCode.do")
	public String updateTranCode(HttpServletRequest request) throws Exception{
		
		System.out.println("==============================");
		System.out.println("UpdateTranCodeAction - START"); 
		
		ProductDao productDAO = new ProductDaoImpl();
		Purchase purchase = null;
		
		String currentPage = request.getParameter("page");
		purchase = purchaseService.getPurchase2(Integer.parseInt(request.getParameter("prodNo")));
		purchase.setTranCode(request.getParameter("tranCode"));
		
		System.out.println("UpdateTranCodeByProdAction - purchase : "+purchase);
		
		purchaseService.updateTranCode(purchase);
		
		System.out.println("UpdateTranCodeAction - END"); 
		
		return "forward:/listPurchase.do?page="+currentPage;
		//return "forward:/purchase/getPurchase.do?tranNo="+purchase.getTranNo();
	}
	
	@RequestMapping("/updateTranCodeByProd.do")
	public String updateTranCodeByProd(HttpServletRequest request, Model model) throws Exception{
		
		System.out.println("==============================");
		System.out.println("UpdateTranCodeByProdAction - START"); 
		
		String currentPage = request.getParameter("page");
		Purchase purchase = purchaseService.getPurchase2(Integer.parseInt(request.getParameter("prodNo")));
		purchase.setTranCode(request.getParameter("tranCode"));
		
		System.out.println("UpdateTranCodeByProdAction - purchase : "+purchase);
		
		purchaseService.updateTranCode(purchase);
		
		System.out.println("UpdateTranCodeByProdAction - END"); 
		
		return "forward:/listProduct.do?page="+currentPage+"&menu=manage";
		//return "forward:/purchase/getPurchase.do?tranNo="+purchase.getTranNo();
	}
	
	@RequestMapping("/cancelPurchase.do")
	public String cancelPurchase(HttpServletRequest request, Model model) throws Exception{
		
		System.out.println("==============================");
		System.out.println("CancelPurchaseAction - START"); 
		
		int tranNo = 0;
		
		if(request.getParameter("tranNo") != null){
			//purchase.setTranNo((Integer.parseInt(request.getParameter("tranNo"))));
			tranNo = Integer.parseInt(request.getParameter("tranNo"));
			
			//System.out.println("CancelPurchaseAction - "+purchase);
			System.out.println("CancelPurchaseAction - "+tranNo);
			
			purchaseService.cancelPurchase(tranNo);
		}		
		
		System.out.println("CancelPurchaseAction - END"); 
		
		return "forward:/listPurchase.do";
	}
	
	
	@RequestMapping("/listPurchase.do")
	public String listPurchase( @ModelAttribute("search") Search search , Model model ,HttpSession session ) throws Exception{
		
		System.out.println("==============================");
		System.out.println("ListPurchaseAction - START"); 
			
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		String buyerId = ((User)session.getAttribute("user")).getUserId();

		search.setSearchCondition("2");
		search.setSearchKeyword(buyerId);
		
		Map<String,Object> map=purchaseService. getPurchaseList(search,buyerId);
		
		Page resultPage	= 
				new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListPurchaseAction ::"+resultPage);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		System.out.println("ListPurchaseAction - END"); 
		
		return "forward:/purchase/listPurchase.jsp";
	}
}