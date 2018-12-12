package club.teenshare.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import club.teenshare.beans.Playlist;

/**
 * A data access object (DAO) providing persistence and search support for
 * Playlist entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see club.teenshare.beans.Playlist
 * @author MyEclipse Persistence Tools
 */
public class PlaylistDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(PlaylistDAO.class);
	// property constants
	public static final String PLNAME = "plname";
	public static final String PLDESC = "pldesc";
	public static final String PLIMG = "plimg";
	public static final String PLNUMBER = "plnumber";

	public void save(Playlist transientInstance) {
		log.debug("saving Playlist instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Playlist persistentInstance) {
		log.debug("deleting Playlist instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Playlist findById(java.lang.String id) {
		log.debug("getting Playlist instance with id: " + id);
		try {
			Playlist instance = (Playlist) getSession().get("club.teenshare.beans.Playlist", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Playlist instance) {
		log.debug("finding Playlist instance by example");
		try {
			List results = getSession().createCriteria("club.teenshare.beans.Playlist").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Playlist instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Playlist as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPlname(Object plname) {
		return findByProperty(PLNAME, plname);
	}

	public List findByPldesc(Object pldesc) {
		return findByProperty(PLDESC, pldesc);
	}

	public List findByPlimg(Object plimg) {
		return findByProperty(PLIMG, plimg);
	}

	public List findByPlnumber(Object plnumber) {
		return findByProperty(PLNUMBER, plnumber);
	}

	public List findAll() {
		log.debug("finding all Playlist instances");
		try {
			String queryString = "from Playlist";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Playlist merge(Playlist detachedInstance) {
		log.debug("merging Playlist instance");
		try {
			Playlist result = (Playlist) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Playlist instance) {
		log.debug("attaching dirty Playlist instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Playlist instance) {
		log.debug("attaching clean Playlist instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}