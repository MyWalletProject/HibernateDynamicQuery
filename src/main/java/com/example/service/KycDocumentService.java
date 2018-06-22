package com.example.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Service;
import com.example.domain.KycDocument;
import com.example.exception.InvalidSortingException;
import com.example.req.Req_KycDocument;
import com.example.req.Req_PageRequest;
import com.example.util.MessageUtility;
import com.example.util.ObjectMap;
import com.example.util.StarinUtilities;
import com.example.util.starinutility;

@Service
public class KycDocumentService {

	
	
private static final Logger logger = LoggerFactory.getLogger(KycDocumentService.class);

	
	@Autowired
	private HibernateJpaSessionFactoryBean sessionFactory;


	public KycDocumentService() {
		
	}


	public Map<String, Object> getAllKycDocuments(Req_KycDocument kycModal, Req_PageRequest pageRequest,String _sort) {

			int pageNo = Integer.parseInt(pageRequest.getPageNo());
			int pageSize = Integer.parseInt(pageRequest.getPageSize());
			long count = Integer.parseInt(pageRequest.getCount());

			List<KycDocument> kycDocuments = null;
			if (pageNo == 0 && pageSize == 0) {
				kycDocuments = getKycDocumentsByDynamicSearch(kycModal, false, pageNo - 1, pageSize,_sort);
			} else {
				kycDocuments = getKycDocumentsByDynamicSearch(kycModal, true, pageNo - 1, pageSize,_sort);
			}

			if (count == -1)
				count = getKycDocumentsByDynamicSearch(kycModal, false, pageNo - 1, pageSize,_sort).size();

			Map<String, Object> response = null;
			response = Req_PageRequest.prepareResponse(pageNo, pageSize, count, "kycDocuments",ObjectMap.objectMap(kycDocuments));
					

			return starinutility.successResponse(response, "fetcH sucessfully");

	}

	private List<KycDocument> getKycDocumentsByDynamicSearch(Req_KycDocument kycModal, boolean isPagination, int pageNo,
			int pageSize,String _sort) {

		EntityManagerFactory emf = sessionFactory.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		Session session = em.unwrap(Session.class);

		Criteria criteria = session.createCriteria(KycDocument.class);

		if (kycModal.getDocumentName() != null){
			logger.info("kycDOcument Name : "+kycModal.getDocumentName());
			criteria.add(Restrictions.like("documentName", kycModal.getDocumentName() + "%"));
		}
		if(kycModal.getDocumentId() != null){
			criteria.add(Restrictions.eq("documentId", StarinUtilities.parseIntoInteger(kycModal.getDocumentId())));
		}
		if(kycModal.getDescription() != null){
			criteria.add(Restrictions.like("description", kycModal.getDescription() + "%"));
		}

		logger.info("sorting columns is => "+_sort);

		if(_sort != null && !_sort.equals("")){

			String[] sortingColumns = _sort.split(",");
			for(String sortingColumn :sortingColumns ){
				logger.info("Column name with sorting : "+sortingColumn);
				String[] separateColumnName=sortingColumn.split("_");
				if(separateColumnName.length != 2 ){
					throw new InvalidSortingException("_sort paramter format should be \'columnName_sortingOrder\' ");
				}
				String columnName = separateColumnName[0];
				String sortingOrder = separateColumnName[1];
				if(!sortingOrder.matches("(ASC|DESC)")){
					throw new InvalidSortingException("sorting Order should be [ASC,DESC]");
				}
				if(sortingOrder.equals("ASC")){
					criteria.addOrder(Order.asc(columnName));
				}else{
					criteria.addOrder(Order.desc(columnName));
				}
			}
	
		}	

		if (isPagination) {
			criteria.setFirstResult(pageNo);
			criteria.setMaxResults(pageSize);
		}

		List<KycDocument> kycDocumentList = criteria.list();
		return kycDocumentList;
	}

}
