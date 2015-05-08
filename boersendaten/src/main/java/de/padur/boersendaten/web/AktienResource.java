/**
 * 
 */
package de.padur.boersendaten.web;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * @author ronald.padur
 *
 */
public class AktienResource extends Resource<AktieRestDTO> {

	public AktienResource() {
		super(new AktieRestDTO());
	}

	public AktienResource(final AktieRestDTO content, final Iterable<Link> links) {
		super(content, links);
	}

	public AktienResource(final AktieRestDTO content, final Link... links) {
		super(content, links);
	}
}
