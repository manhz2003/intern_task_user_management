package com.fds.nsw.kernel.dao.orm.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class QueryBuilder {
	protected Class<?> entityClass;
//	@Builder.Default
//	protected int firstResult = 0;
	protected int firstResult;
//	@Builder.Default
//	protected int maxResults = 20;
	protected int maxResults;
	@Builder.Default
	protected Map<String, Object> namedParameterMap = new HashMap<String, Object>();
	protected Object[] parameters;
	protected String queryString;
	@Builder.Default
	protected boolean sqlQuery = false;

	public QueryBuilder appendNamedParameterMap(String key, Object value) {
		this.getNamedParameterMap().put(key, value);
		if (this.getParameters() == null || this.getParameters().length == 0) {
			this.setParameters(new Object[] { value });
		} else {
			ArrayList<Object> arrayList = new ArrayList<>(this.getParameters().length + 1);

			for (int i = 0; i < this.getParameters().length; i++) {
				arrayList.add(this.getParameters()[i]);
			}
			arrayList.add(value);

			this.setParameters(arrayList.toArray());
		}
		return this;
	}
}
