package com.dhanjyoti.springmvc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dhanjyoti.springmvc.model.KycDocument;



@Repository("kycdocumentDao")
public class KycDocumentDaoImpl extends AbstractDao<Integer, KycDocument> implements KycDocumentDao {

	static final Logger logger = LoggerFactory.getLogger(KycDocumentDaoImpl.class);

	@Override
	public KycDocument findById(Integer id) {
		// TODO Auto-generated method stub
		KycDocument document = getByKey(id);
		return document;
	}
		
	
}
