package com.aashdit.lms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.lms.dto.BookDetail;
import com.aashdit.lms.dto.CatalogDto;
import com.aashdit.lms.dto.IssuingDetails;
import com.aashdit.lms.model.Book;
import com.aashdit.lms.model.BookCatalog;
import com.aashdit.lms.model.BookIssue;
import com.aashdit.lms.model.BookIssueHistory;
import com.aashdit.lms.model.LibraryCard;
import com.aashdit.lms.model.PaymentTransaction;
import com.aashdit.lms.repository.BookCatalogRepository;
import com.aashdit.lms.repository.BookIssueHistoryRepository;
import com.aashdit.lms.repository.BookIssueRepository;
import com.aashdit.lms.repository.BookRepository;
import com.aashdit.lms.repository.LibraryCardRepository;
import com.aashdit.lms.repository.PaymentTransactionRepository;
import com.aashdit.lms.utils.ApplicationConstants;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookIssueServiceImpl implements BookIssueService,MessageSourceAware{
	
	@Autowired
	private LibraryCardRepository libraryCardRepository;
	
	@Autowired
	private BookCatalogRepository bookCatalogRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookIssueRepository bookIssueRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BookIssueHistoryRepository bookIssueHistoryRepository;
	
	@Autowired
	private PaymentTransactionRepository paymentTransactionRepository;

	@Autowired
	private MessageSource messageSource;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	ResourceBundle rb = ResourceBundle.getBundle("application");
	
	@Override
	public List<Object[]> findApprovalNo(String aaprovalNo) {
		  List<Object[]> objectList= libraryCardRepository.findApprovalNoAutoSearch(aaprovalNo); 	  
		  return objectList;
	}

	@Override
	public ServiceOutcome<LibraryCard> findMemberByApprovalNo(String sentapprovalNo) {
		ServiceOutcome<LibraryCard> srvc = new ServiceOutcome<>();
	    String message = "";
	    Boolean flag = true;
	    LibraryCard cardData = new LibraryCard();

	    try {
	        User user = SecurityHelper.getCurrentUser();

	        // Check if mamberCode is present
	        if (Optional.ofNullable(sentapprovalNo).isPresent()) {
	            cardData = libraryCardRepository.findCardByApprovalNo(sentapprovalNo);
	        } else {
	            message = "Member Not Found";
	            flag = false;
	        }
	    } catch (Exception e) {
	        message = "Something went wrong, please try again";
	        flag = false;
	        log.error("Exception occurred in findMemberByApprovalNo method in BookIssueServiceImpl-->", e);
	    }

	    srvc.setData(cardData);
	    srvc.setMessage(message);
	    srvc.setOutcome(flag);

	    return srvc;
	}

	@Override
	public List<Object[]> findBookSByNamel(String books) {
		 List<Object[]> objectList =null;
		try {
			objectList= bookCatalogRepository.findBooksByBookName(books); 
		}
		catch (Exception e) {
			 log.error("Exception occurred in findBookSByNamel method in BookIssueServiceImpl-->", e);
		}
		return objectList;
	}

	@Override
	public ServiceOutcome<List<Book>> findByCode(String code) {
		ServiceOutcome<List<Book>> svcOutcome = new ServiceOutcome<>();
		try {
			Optional<BookCatalog> data = bookCatalogRepository.findByBookCatalogCode(code);
			List<Book> lstSc = bookRepository.findByBookCatalogId(data.get().getBookCatalogId());
			svcOutcome.setData(lstSc);
		}
		catch (Exception e) {
			 log.error("Exception occurred in findByCode method in BookIssueServiceImpl-->", e);
			 svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	}
	@Transactional
	@Override
	public ServiceOutcome<String> saveBookISuue(IssuingDetails issuingDetails) {
		ServiceOutcome<String> srvc = new ServiceOutcome<>();
		String message="";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Book book =null;
		try {
			User user  = SecurityHelper.getCurrentUser();
			if(issuingDetails.getBookDetails()!=null && issuingDetails.getBookDetails().size()>0) {
				
				List<BookDetail> bookDetails = issuingDetails.getBookDetails();
		        for (BookDetail bookDetail : bookDetails) {
		        	BookIssue bookdeatils= new BookIssue();
		        LibraryCard icarddetails  = libraryCardRepository.findCardByApprovalNo(issuingDetails.getApprovalNo());
		         book = bookRepository.findByBookUkNoAndIsActive(bookDetail.getBookId(),true);
		        bookdeatils.setBook(bookRepository.findByBookId(book.getBookId()));
		        bookdeatils.setLibraryCard(libraryCardRepository.findByLib_cardId(icarddetails.getLib_cardId()));
		        Date issueDate = dateFormat.parse(issuingDetails.getIssueDate());
		        Date retrinDate = dateFormat.parse(issuingDetails.getReturnDate());
		        bookdeatils.setIssuedDate(issueDate);
		        bookdeatils.setReturnDate(retrinDate);
//		        String qrPath = QrCodeGenrator.generateQRCodeGenerateForBooking( icarddetails.getMember().getMemberCode(),  rb.getString("UPLOAD.FILE.PATH"), ApplicationConstants.QRBOOKING,"QR" + icarddetails.getMember().getMemberCode(),book.getBookUkNo());
//		        bookdeatils.setBarchodePath(qrPath);
		        bookdeatils.setIsActive(true);
		        bookdeatils.setStatus("APPROVED");
		        bookdeatils.setCreatedBy(user);
		        bookdeatils.setCreatedOn(new Date());
		        bookdeatils.setLastUpdatedOn(new Date());
		        bookdeatils.setLastUpdatedBy(user);
		        bookdeatils.setLastUpdatedOn(new Date());
		        bookdeatils.setActualReturnDate(retrinDate);
		        bookIssueRepository.save(bookdeatils);
		        if(bookdeatils.getStatus().equals("APPROVED")) {
		        	 book.setStatus("APPROVED");
				        bookRepository.save(book);
		        }
		        BookIssueHistory bookhis= new BookIssueHistory();
				BeanUtils.copyProperties(bookdeatils, bookhis);
				bookhis.setBookIssue(bookdeatils);
				bookhis.setLastUpdatedBy(user);
				bookhis.setCreatedOn(new Date());
				bookhis.setLastUpdatedBy(user);
				bookhis.setCreatedBy(user);
				bookIssueHistoryRepository.save(bookhis);
		        
		        }
		        message = "Book Issued Successfully";
			srvc.setMessage(message);
		    srvc.setOutcome(true);
		
			} else {
				srvc.setMessage(message);
			    srvc.setOutcome(false);
			    
			}
		    
		} catch (Exception e) {
			
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			srvc.setMessage("Something went wrong please try again");
			srvc.setOutcome(false);
			log.error("Exception occured in saveBookISuue in MemberServiceImpl-->", e.getMessage());
		}
		return srvc;
	}

	@Override
	public ServiceOutcome<List<CatalogDto>> getAllBookCatalougeData() {
		ServiceOutcome<List<CatalogDto>> serviceOutcome=new ServiceOutcome<>();
		try {
			StringBuilder query = new StringBuilder();
			query.append("select\n");
			query.append("tlmbc.book_catalog_id ,\n");
			query.append("tlmbc.book_catalog_code ,\n");
			query.append("tlmbc.isbn,\n");
			query.append("tlmbc.book_titles,\n");
			query.append("tlma.author_name ,\n");
			query.append("tlmbc.book_subject ,\n");
			query.append("tlmsc.sub_category_name ,\n");
			query.append("tlmbc.description ,\n");
			query.append("tmlv.lookup_value_en ,\n");
			query.append("tlmbc.no_of_page ,\n");
			query.append("tlmbc.img_path ,\n");
			query.append("tlmbc.book_price,\n");
			query.append("tlmbc.purchase_date ,\n");
			query.append("tlmbc.publish_date ,\n");
			query.append("tlml.lib_name ,\n");
			query.append("tlmp.publisher_name ,\n");
			query.append("tlmbc.is_active\n");
			query.append("from\n");
			query.append("public.t_lms_mst_book_catalog tlmbc\n");
			query.append("join public.t_lms_mst_author tlma on\n");
			query.append("tlma.author_id = tlmbc.author_id\n");
			query.append("join public.t_lms_mst_sub_category tlmsc on\n");
			query.append("tlmsc.sub_category_id = tlmbc.sub_category_id\n");
			query.append("join public.t_lms_mst_library tlml on\n");
			query.append("tlml.lib_id = tlmbc.lib_id\n");
			query.append("join public.t_mst_lookup_value tmlv on\n");
			query.append("tmlv.lookup_value_id = tlmbc.\"language\"\n");
			query.append("join public.t_lms_mst_publisher tlmp on\n");
			query.append("tlmp.publisher_id = tlmbc.publisher_id\n");
			List<CatalogDto> resultList = jdbcTemplate.query(query.toString(),
					new BeanPropertyRowMapper<>(CatalogDto.class));
			serviceOutcome.setData(resultList);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(this.messageSource.getMessage("msg.error", null,LocaleContextHolder.getLocale()));
		}
		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<JSONObject> createPayment(Long rentMonthId, String amount) {
		ServiceOutcome<JSONObject> serviceOutcome=new ServiceOutcome<JSONObject>();
		serviceOutcome.setOutcome(true);
		try {
			JSONObject jsonObject=new JSONObject();
			if(serviceOutcome.getOutcome()) {
				RazorpayClient razorpay = new RazorpayClient(ApplicationConstants.TEST_KEY_ID, ApplicationConstants.SECRET_KEY_ID);
				//double amount =500.0;

				JSONObject orderRequest = new JSONObject();
				orderRequest.put("amount", Double.parseDouble(amount)); // amount in the smallest currency unit
				orderRequest.put("currency", "INR");
				orderRequest.put("receipt", "Ajay Kumar");

				Order order = razorpay.Orders.create(orderRequest);
				
				jsonObject.put("order_id", order.get("id").toString());
				jsonObject.put("amount", order.get("amount").toString());
				jsonObject.put("name", "Ajay Kumar");
				jsonObject.put("test_key", ApplicationConstants.TEST_KEY_ID);
			}
			serviceOutcome.setData(jsonObject);
		} catch (Exception e) {
			log.error("Error in Generate Order Id ==> "+e);
		}
		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<JSONObject> saveSuccAndErrorRes(String signature, String order_id, String payment_id,
			String status, String reason, Long rentMonthId, String preference) {
		ServiceOutcome<JSONObject> serviceOutcome =new ServiceOutcome<>();
		User user =SecurityHelper.getCurrentUser();
		try {
			RazorpayClient razorpayClient = new RazorpayClient(ApplicationConstants.TEST_KEY_ID, ApplicationConstants.SECRET_KEY_ID);
			JSONObject jsonObject=new JSONObject();
			if(status.equals(ApplicationConstants.SUCCESS)) {
				JSONObject options = new JSONObject();
				options.put("razorpay_order_id", order_id);
				options.put("razorpay_payment_id", payment_id);
				options.put("razorpay_signature", signature);
		     	Boolean isPaymentSuccess = Utils.verifyPaymentSignature(options, ApplicationConstants.SECRET_KEY_ID);
		     	
	     		PaymentTransaction paymentTransaction=new PaymentTransaction();
	     		paymentTransaction.setCreatedBy(user);
	     		paymentTransaction.setCreatedOn(new Date());
	     		paymentTransaction.setOrderId(order_id);
	     		paymentTransaction.setPaymentId(payment_id);
	     		paymentTransaction.setSignature(signature);
	     		paymentTransaction.setBookIssue(bookIssueRepository.findById(rentMonthId).get());
	     		Payment payment = razorpayClient.Payments.fetch(payment_id);
	     	    Double amount = Double.valueOf(payment.get("amount").toString());
	     	    paymentTransaction.setAmount(amount / 100.00);
	     	    String method = payment.get("method");
	     	    paymentTransaction.setPaymentMode(method.replaceAll(" ", "").toUpperCase());
		     	if(isPaymentSuccess) {
		     	   paymentTransaction.setStatus(ApplicationConstants.SUCCESS);
		     	   paymentTransaction = paymentTransactionRepository.save(paymentTransaction);
		     	}else {
		     	   paymentTransaction.setStatus(ApplicationConstants.FAILED);
			       paymentTransaction = paymentTransactionRepository.save(paymentTransaction);
		     	}
		     	jsonObject.put("transactionId", paymentTransaction.getTransactionId());
			}else {
	     		PaymentTransaction paymentTransaction=new PaymentTransaction();
	     		paymentTransaction.setCreatedBy(user);
	     		paymentTransaction.setCreatedOn(new Date());
	     		paymentTransaction.setOrderId(order_id);
	     		paymentTransaction.setPaymentId(payment_id);
	     		paymentTransaction.setSignature(signature);
	     		paymentTransaction.setBookIssue(bookIssueRepository.findById(rentMonthId).get());
	     		paymentTransaction.setReason(reason);
	     		Payment payment = razorpayClient.Payments.fetch("payment_id");
	     	    Double amount = Double.valueOf(payment.get("amount"));
	     	    paymentTransaction.setAmount(amount / 100.00);
	     	    paymentTransaction.setStatus(ApplicationConstants.FAILED);
	     	    paymentTransaction = paymentTransactionRepository.save(paymentTransaction);
	     	   jsonObject.put("transactionId", paymentTransaction.getTransactionId());
			}
			serviceOutcome.setData(jsonObject);
			
		} catch (Exception e) {
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			log.error("payment eorror orderId ="+ order_id +"payment_id ="+ payment_id + "signature =" + signature +""+ e);
		}
		
		return serviceOutcome;
	}

	
	
	
	
}
