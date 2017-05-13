package dao;

import java.util.ArrayList;

import beans.Service;

public interface ServiceDao {
	public Boolean addService(Service service);

	public Boolean modifyService(Service service);

	public Boolean removeService(Service service);

	public Service findServiceById(Long Id);

	public ArrayList<Service> findServiceByKeyWord(String keyWord);

	public ArrayList<Service> findAllService(Long limit, Long offset);

	public ArrayList<Service> findAllServiceById(Long Id);

	public Long countElements();
}
