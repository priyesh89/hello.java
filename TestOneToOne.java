package com.js.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.js.dto.AadharCardDTO;
import com.js.dto.AddressDTO;
import com.js.util.GetHibernateConnection;

public class TestOneToOne {

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		try {
			session = GetHibernateConnection.getInstance().getSession();
			tx = session.beginTransaction();
			AadharCardDTO aadharCardDTO = new AadharCardDTO("vivek", "baviskar", "888888888");
			AddressDTO addressDTO = new AddressDTO("india", "g.j..", "ahemdabad", "65432", "gandhi nagar");
			session.persist(aadharCardDTO);
			addressDTO.setAddressId(aadharCardDTO.getAadharId());
			aadharCardDTO.setAddress(addressDTO);
			session.save(aadharCardDTO);
			tx.commit();
			System.out.println("succesfully saved");
			List<AadharCardDTO> list = (List<AadharCardDTO>) session.createQuery("from AadharCardDTO").list();
			for (AadharCardDTO aadharCardDTO2 : list) {
				System.out.println(aadharCardDTO2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
