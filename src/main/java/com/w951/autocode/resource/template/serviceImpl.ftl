<#ftl strip_whitespace=true/>
package ${packageName}.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${packageName}.entity.${classSimpleName};
import ${packageName}.service.${classSimpleName}Service;
import com.w951.orm.hibernate.HibernateDao;
import com.w951.util.bean.BeanUtil;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：${author}<br>
 * 日期：${date?string("yyyy-MM-dd")}<br>
 * 时间：${time?string("HH:mm:ss")}<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
@Component
public class ${classSimpleName}ServiceImpl implements ${classSimpleName}Service {
	@Resource
	private HibernateDao hibernateDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public String delete(${classSimpleName} entity) {
		entity = get(entity.get${classSimpleName}Id());
		hibernateDao.delete(entity);
		return null;
	}

	public ${classSimpleName} get(String id) {
		return hibernateDao.get(new ${classSimpleName}(), id);
	}

	public long getCount() {
		return hibernateDao.getCount(new ${classSimpleName}());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String insert(${classSimpleName} entity) {
		hibernateDao.insert(entity);
		return null;
	}

	public List<${classSimpleName}> queryList(String... order) {
		if (order != null) {
			return hibernateDao.queryList(new ${classSimpleName}(), order);
		} else {
			return hibernateDao.queryList(new ${classSimpleName}());
		}
	}

	public List<${classSimpleName}> queryPageList(int pageIndex, int pageSize,
			String... order) {
		if (order != null) {
			return hibernateDao.queryPageList(new ${classSimpleName}(), pageIndex,
					pageSize, order);
		} else {
			return hibernateDao.queryPageList(new ${classSimpleName}(), pageIndex,
					pageSize);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String update(${classSimpleName} entity) {
		${classSimpleName} obj = get(entity.get${classSimpleName}Id());
		BeanUtil.beanToBean(entity, obj);
		hibernateDao.update(entity);
		return null;
	}

}