package ita.support.dba.dao.admin.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ita.support.dba.dao.AbstractDaoImpl;
import ita.support.dba.dao.admin.IInquiryDao;
import ita.support.dba.entities.admin.Inquiry;

@Repository
@Transactional
public class InquiryDaoImpl extends AbstractDaoImpl<Inquiry, Long> implements IInquiryDao {

	public InquiryDaoImpl() {
		super(Inquiry.class);
	}
}
