/**
 * 
 */
package de.padur.boersendaten.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.padur.boersendaten.bo.Aktie;

/**
 * @author ronald.padur
 *
 */
public interface AktienRepository extends MongoRepository<Aktie, String> {

	public Aktie findByWkn(final String wkn);
	
	public List<Aktie> findByName(final String name);
	
}
