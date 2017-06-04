package dao;

import java.util.ArrayList;

import beans.Service;

public interface ServiceDao {
	public Boolean addService(Service service);

	public Boolean modifyService(Service service);

	public Boolean removeService(Service service);

	public Service findServiceById(Long Id);

	public ArrayList<Service> findAllService(Long Id, Long limit, Long offset);

	public ArrayList<Service> findAllServiceByIdProvider(Long Id, Long limit, Long offset);

	public Long countElements(Long Id);

	public Long countElementsByIdProvider(Long Id);

	public Boolean changeStatut(Long Id, Integer statut);

}
