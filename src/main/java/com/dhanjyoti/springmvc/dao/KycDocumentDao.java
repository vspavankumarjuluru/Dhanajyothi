package com.dhanjyoti.springmvc.dao;

import com.dhanjyoti.springmvc.model.KycDocument;

public interface KycDocumentDao {	
	
	KycDocument findById(Integer id);
}

