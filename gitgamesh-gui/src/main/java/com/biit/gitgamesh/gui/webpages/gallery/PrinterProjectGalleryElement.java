package com.biit.gitgamesh.gui.webpages.gallery;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.UUID;

import com.biit.gitgamesh.gui.components.GalleryElement;
import com.biit.gitgamesh.persistence.entity.PrinterProject;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;

public class PrinterProjectGalleryElement extends GalleryElement<PrinterProject> {
	private static final long serialVersionUID = -1180272951539494732L;

	public static final int MAX_DESCRIPTION_LENGTH = 512;

	public PrinterProjectGalleryElement(PrinterProject element) {
		super(element);
	}

	@Override
	protected String getUserName() {
		return getElement().getCreatedBy();
	}

	@Override
	protected Timestamp getUpdateTime() {
		return getElement().getUpdateTime();
	}

	@Override
	protected String getName() {
		return getElement().getName();
	}

	@Override
	protected String getElementDescription() {
		if (getElement().getDescription().length() < MAX_DESCRIPTION_LENGTH) {
			return getElement().getDescription();
		}
		return getElement().getDescription().substring(0, MAX_DESCRIPTION_LENGTH - 3) + "...";
	}

	@Override
	protected Resource getPreviewResource() {
		StreamResource resource = new StreamResource(new StreamSource() {
			private static final long serialVersionUID = 6580434244890188186L;

			@Override
			public InputStream getStream() {
				return new ByteArrayInputStream(getElement().getPreview());
			}
		}, UUID.randomUUID().toString());
		resource.setCacheTime(0);
		return resource;
	}
}