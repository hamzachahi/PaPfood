package dao;

import beans.Service;

public interface ServiceDao {
	public Boolean addService(Service service);

	public Boolean modifyService(Service service);

	public Boolean removeService(Service service);
}
