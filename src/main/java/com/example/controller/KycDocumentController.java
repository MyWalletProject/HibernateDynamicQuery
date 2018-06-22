package com.example.controller;

import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.api.ApiAction;
import com.example.config.KYCConstants;
import com.example.exception.InvalidSortingException;
import com.example.req.Req_KycDocument;
import com.example.req.Req_PageRequest;
import com.example.service.KycDocumentService;
import com.example.util.MessageUtility;
import com.example.util.ResponseUtil;
import com.example.util.StarinsUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "kycdocument-controller", description = "Operation pertaining to kycdocument")
@RestController
@RequestMapping("/api/v1")
public class KycDocumentController {

	@Autowired
	private KycDocumentService kycDocumentService;

	 private static final Logger logger = LoggerFactory.getLogger(KycDocumentController.class);
	
	     public KycDocumentController() {
		 
		 logger.info("constructor KycDocumentController ");
		}
	 
	@ApiAction
	@ApiOperation(value = "Get all kycdocuments ", response = ResponseEntity.class)
	@GetMapping(value = "/kycdocument", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllKycDocuments(
			@RequestHeader(name = KYCConstants.Starin_Token_Header, defaultValue = "") String authToken,
			@RequestParam(required=false) String sort,@Valid  @ModelAttribute Req_KycDocument kycModal ,BindingResult bindingResult , @Valid @ModelAttribute Req_PageRequest pageRequest,
			BindingResult result) {

		logger.info("inside getAllKycDocuments api");

		if (bindingResult.hasErrors()){
			logger.info("****************** ");
			return ResponseUtil.fieldErrorResponse("field errrorr",StarinsUtility.getFieldErrorResponse(result));
		}		

		if (result.hasErrors()){
			logger.info("###############");
			return ResponseUtil.fieldErrorResponse("field errrorr",StarinsUtility.getFieldErrorResponse(result));
		}		

		String message =   pageRequest.customValidate( );
		if (message != null){
			logger.info("message does not null condition true");
			return ResponseUtil.errorResponse(message, HttpStatus.BAD_REQUEST);
		}
		Map<String, Object> response = null;
		try{
			response = kycDocumentService.getAllKycDocuments(kycModal,pageRequest, sort);

			Boolean isError = (Boolean) response.get("error");
			if (isError != null && isError) {
				logger.info("isError does not null condition true");
				return ResponseUtil.errorResponse((String) response.get("message"), (HttpStatus) response.get("status"));
			}

			return ResponseUtil.response(response.get("data"), (String) response.get("message"));

		}catch (InvalidSortingException se) {
			logger.info(" **InvalidSortingException** ");
			return ResponseUtil.errorResponse(se.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			logger.info("**Exception**");
			return ResponseUtil.errorResponse("INternal server error",HttpStatus.INTERNAL_SERVER_ERROR);
					
		}

	}



}

