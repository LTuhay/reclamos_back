package com.group1.dev.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.group1.dev.app.model.dao.EdificioRepository;
import com.group1.dev.app.model.dao.ImagenRepository;
import com.group1.dev.app.model.dao.ReclamoRepository;
import com.group1.dev.app.model.dao.UnidadRepository;
import com.group1.dev.app.model.entity.Edificio;
import com.group1.dev.app.model.entity.EstadoReclamo;
import com.group1.dev.app.model.entity.EstadoUnidad;
import com.group1.dev.app.model.entity.Imagen;
import com.group1.dev.app.model.entity.Reclamo;
import com.group1.dev.app.model.entity.Unidad;

@DataJpaTest
class RepositoryApplicationTests {
	
	@Autowired
	private UnidadRepository unidadRepo;
	
	@Autowired
	private EdificioRepository edificioRepo;
	
	@Autowired
	private ReclamoRepository reclamoRepo;
	
	@Autowired
	private ImagenRepository imagenRepo;
	//private UsuarioRepository usuarioRepo;


	@Test
	public void should_find_no_personas_if_repository_is_empty() {
				
		//assertThat(personaRepo.findAll()).isEmpty();
	}

	@Test
	public void should_store_a_Unidad() {
				
		Unidad unidad = new Unidad();
		unidad.setNro(5);unidad.setPiso(1);unidad.setEstado(EstadoUnidad.HabitadaPorDuenio);
		Unidad unidadPersistida = unidadRepo.save(unidad);
				
		assertThat(unidadPersistida).hasFieldOrPropertyWithValue("piso", 1);
		assertThat(unidadPersistida).hasFieldOrPropertyWithValue("estado", EstadoUnidad.HabitadaPorDuenio);
		assertThat(unidadPersistida).hasFieldOrPropertyWithValue("nro", 5);
	}

	@Test
	public void should_find_all_Edificios() {
		
		Edificio edificio1 = new Edificio();
		edificio1.setDireccion("rivadavia 3333");
		edificioRepo.save(edificio1);
		
		Edificio edificio2 = new Edificio();
		edificio2.setDireccion("corrientes 2222");
		edificioRepo.save(edificio2);
		
		Edificio edificio3 = new Edificio();
		edificio3.setDireccion("Belgrano 1111");
		edificioRepo.save(edificio3);
		
		assertThat(edificioRepo.findAll()).hasSize(3).contains(edificio1,edificio2,edificio3);
	}


	@Test
	public void should_find_Reclamo_by_id() {
	
	Reclamo reclamo1 = new Reclamo();
	reclamo1.setTitulo("Reclamo testing");
	reclamo1.setDescripcion("This is a test");
	reclamo1.setEstadoReclamo(EstadoReclamo.Nuevo);
    reclamoRepo.save(reclamo1);

    Reclamo reclamo2 = new Reclamo();
	reclamo2.setTitulo("Reclamo testing 2");
	reclamo2.setDescripcion("This is a second test");
	reclamo2.setEstadoReclamo(EstadoReclamo.Cerrado);
    reclamoRepo.save(reclamo2);

    Reclamo foundReclamo = reclamoRepo.findById(reclamo2.getId()).get();

    assertThat(foundReclamo).isEqualTo(reclamo2);
    
	}
	@Test
	public void should_delete_Imagen_by_id() {
		Imagen imagen = new Imagen();
		imagen.setDescripcion("Zoom");
		imagen.setNombreImagen("ReclamoXZoom");
		imagen.setId(1);
		imagenRepo.save(imagen);
		assertThat(imagenRepo.findById(1)).isPresent();
		imagenRepo.deleteById(1);
		assertThat(imagenRepo.findById(1)).isEmpty();
		
	}

}
