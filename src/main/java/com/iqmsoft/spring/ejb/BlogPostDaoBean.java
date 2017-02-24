

package com.iqmsoft.spring.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaQuery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Stateless
@Transactional
@TransactionManagement
public class BlogPostDaoBean {

    /** The EntityManager. */
    @PersistenceContext//(unitName = "blogpost-persistence")
	//@Autowired
   // @PersistenceUnit
    private EntityManager entityManagerFactory;

   // @Autowired
  //  private EntityManagerFactory emfact;
	
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void persist(BlogPost blogPost) {
    	
    	//entityManagerFactory.getTransaction().begin();
    	entityManagerFactory.persist(blogPost);
    	
    	entityManagerFactory.flush();
    	//entityManagerFactory.getTransaction().commit();
    }

    /**
     * Returns all entities.
     *
     * @return list with all entities.
     */
    @SuppressWarnings("unchecked")
    public List<BlogPost> findAll() {
        CriteriaQuery criteriaQuery = entityManagerFactory.getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(BlogPost.class));
        return entityManagerFactory.createQuery(criteriaQuery).getResultList();
    	
    	//return this.brepos.findAll();
    }

    /**
     * Finds a specific entity by id.
     *
     * @param id the id.
     * @return the found entity or null if the entity can't be found.
     */
    public BlogPost find(int id) {
        try {
            return entityManagerFactory.find(BlogPost.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }
}
