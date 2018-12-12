package club.teenshare.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import club.teenshare.beans.Plsong;

/**
 * A data access object (DAO) providing persistence and search support for
 * Plsong entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see club.teenshare.beans.Plsong
 * @author MyEclipse Persistence Tools
 */
public class PlsongDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(PlsongDAO.class);
	// property constants

	public void save(Plsong transientInstance) {
		log.debug("saving Plsong instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Plsong persistentInstance) {
		log.debug("deleting Plsong instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Plsong findById(java.lang.String id) {
		log.debug("getting Plsong instance with id: " + id);
		try {
			Plsong instance = (Plsong) getSession().get("club.teenshare.beans.Plsong", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Plsong instance) {
		log.debug("finding Plsong instance by example");
		try {
			List results = getSession().createCriteria("club.teenshare.beans.Plsong").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Plsong instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Plsong as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Plsong instances");
		try {
			String queryString = "from Plsong";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Plsong merge(Plsong detachedInstance) {
		log.debug("merging Plsong instance");
		try {
			Plsong result = (Plsong) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Plsong instance) {
		log.debug("attaching dirty Plsong instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Plsong instance) {
		log.debug("attaching clean Plsong instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}