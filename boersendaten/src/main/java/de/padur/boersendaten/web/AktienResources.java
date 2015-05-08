/**
 * 
 */
package de.padur.boersendaten.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

/**
 * @author ronald.padur
 *
 */
public class AktienResources extends Resources<AktienResource> {

	public AktienResources() {

	}

	public AktienResources(final Iterable<AktienResource> content,
			final Iterable<Link> links) {
		super(content, links);
	}

	public AktienResources(final Iterable<AktienResource> content,
			final Link... links) {
		super(content, links);
	}

	public List<AktieRestDTO> unwrap() {
		Collection<AktienResource> resources = getContent();
		List<AktieRestDTO> aktien = new ArrayList<AktieRestDTO>(
				resources.size());

		for (AktienResource resource : resources) {
			aktien.add(resource.getContent());
		}

		return aktien;
	}
}
