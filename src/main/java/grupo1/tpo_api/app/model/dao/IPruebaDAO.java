package grupo1.tpo_api.app.model.dao;

import java.util.List;

import grupo1.tpo_api.app.model.entity.prueba;

public interface IPruebaDAO {
	
	public List<prueba> findAll();
	public prueba findByID(int id);
	public void save(prueba test);
	public void delete(int id);
	
}
