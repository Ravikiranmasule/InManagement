package com.luxtavern.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.luxtavern.dao.LoginDao;
import com.luxtavern.entity.User;


public class LoginDaoIMPL  {
	@Autowired
	SessionFactory sessionFactory;
//
//	@Override
//	public User login(String userEmail,String userPassWord) {
//		Session session=null;
//				try { session=sessionFactory.openSession();
//				
////		User user1=(User) session.createQuery("from User where userEmail = :userEmail", User.class)
////                .setParameter("userEmail", userEmail)
////                .uniqueResult();
//////				
////				Criteria criteria=session.createCriteria(User.class);
////				criteria.add(Restrictions.eq("userEmail", userEmail));
////				User user1=(User) criteria.uniqueResult();
////				User user1=(User) session.createQuery(" from User where userEmail=:userEmail",User.class)
////				.setParameter("userEmail", userEmail)
////				.uniqueResult();
////				
//				CriteriaBuilder builder=session.getCriteriaBuilder();
//				CriteriaQuery<User> query = builder.createQuery(User.class);
//				Root<User> root = query.from(User.class);
//				query.select(root).where(builder.equal(root.get("userEmail"), userEmail));
//				User user1=session.createQuery(query).uniqueResult();
//		System.out.println("in dao class");
//		System.out.println(user1);
//		System.out.println("in dao class after user1");
//return user1;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//
//	@Override
//	public User getUserById(Long userId) {
//		Session session=sessionFactory.openSession();
//		User user=session.get(User.class, userId);
//		
//		return user;
//	}
//
//	@Override
//	public String register(User user) {
//		Session session;
//		String msg=null;
//		try {
//			session=sessionFactory.openSession();
//			Transaction tr=session.beginTransaction();
//			System.out.println("in user register dao");
//			session.save(user);
//			tr.commit();
//			msg="success";
//			
//		} catch (Exception e) {
//			msg ="fail";
//			e.printStackTrace();
//		}
//		return msg;
//	}
//
//	@Override
//	public Boolean userExists(String userEmail) {
//		System.out.println(userEmail);
//		Session session=null;
//		Boolean result=false;
//		System.out.println("in userExists method");
//		try {
//			session=sessionFactory.openSession();
//		Criteria criteria=session.createCriteria(User.class);
//		
//		criteria.add(Restrictions.eq("userEmail",userEmail));
//		
//		
//		User user1=(User) criteria.uniqueResult();
//		
//		if(user1!=null) {
//			
//			result= true;
//		}
//		
//			
//			
//		} catch (Exception e) {
//			result=false;
//		}
//		return result;
//	}
//
//	@Override
//	public List<User> getAllUser() {
//		Session session= null;
//		List<User> list=null;
//		try {
//			session=sessionFactory.openSession();
//			Criteria criteria=session.createCriteria(User.class);
//			
//			list=criteria.list();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return list;
//	}


}
