package club.teenshare.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import club.teenshare.beans.Song;

/**
 * A data access object (DAO) providing persistence and search support for Song
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see club.teenshare.beans.Song
 * @author MyEclipse Persistence Tools
 */
public class SongDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SongDAO.class);
	// property constants
	public static final String SONGPIC = "songpic";
	public static final String SONGNAME = "songname";
	public static final String SONGDESC = "songdesc";
	public static final String SONGSRC = "songsrc";
	public static final String SONGSINGER = "songsinger";
	public static final String SONGPLAYNUM = "songplaynum";

	public void save(Song transientInstance) {
		log.debug("saving Song instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Song persistentInstance) {
		log.debug("deleting Song instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Song findById(java.lang.String id) {
		log.debug("getting Song instance with id: " + id);
		try {
			Song instance = (Song) getSession().get("club.teenshare.beans.Song", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Song instance) {
		log.debug("finding Song instance by example");
		try {
			List results = getSession().createCriteria("club.teenshare.beans.Song").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Song instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Song as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySongpic(Object songpic) {
		return findByProperty(SONGPIC, songpic);
	}

	public List findBySongname(Object songname) {
		return findByProperty(SONGNAME, songname);
	}

	public List findBySongdesc(Object songdesc) {
		return findByProperty(SONGDESC, songdesc);
	}

	public List findBySongsrc(Object songsrc) {
		return findByProperty(SONGSRC, songsrc);
	}

	public List findBySongsinger(Object songsinger) {
		return findByProperty(SONGSINGER, songsinger);
	}

	public List findBySongplaynum(Object songplaynum) {
		return findByProperty(SONGPLAYNUM, songplaynum);
	}

	public List findAll() {
		log.debug("finding all Song instances");
		try {
			String queryString = "from Song";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Song merge(Song detachedInstance) {
		log.debug("merging Song instance");
		try {
			Song result = (Song) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Song instance) {
		log.debug("attaching dirty Song instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Song instance) {
		log.debug("attaching clean Song instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}