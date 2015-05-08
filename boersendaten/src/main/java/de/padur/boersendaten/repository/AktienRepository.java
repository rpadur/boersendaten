/**
 * 
 */
package de.padur.boersendaten.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

import de.padur.boersendaten.bo.Aktie;

/**
 * @author ronald.padur
 *
 */
@RepositoryRestResource(collectionResourceRel = "aktien", path = "aktien")
public interface AktienRepository extends MongoRepository<Aktie, String> {

	public Aktie findByWkn(@Param("wkn") final String wkn);

	public List<Aktie> findByName(@Param("name") final String name);

	@RequestMapping(name = "/aktien")
	public List<Aktie> findAll();

}
