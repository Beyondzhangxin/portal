package aiidc.com.cn.portal.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2313696048038752791L;
	protected String id;

	@Id
	@Column(name = "id", length = 32)
	public String getId() {
		if (id == null)
			id = UUID.randomUUID().toString().replace("-", "");
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		return toJson();
	}

	public final String toJson() {
		try {
			return JsonTools.toJsonStr(this);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
