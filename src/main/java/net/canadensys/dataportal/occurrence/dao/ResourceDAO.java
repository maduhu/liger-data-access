package net.canadensys.dataportal.occurrence.dao;

import java.util.List;

import net.canadensys.dataportal.occurrence.model.DwcaResourceModel;

public interface ResourceDAO {

	/**
	 * Load all ResourceModel
	 * 
	 * @return ResourceModel list or null
	 */
	public List<DwcaResourceModel> loadResources();

	/**
	 * Load a ResourceModel from its auto_id
	 * 
	 * @param auto_id
	 * @return ResourceModel or null if nothing is found
	 */
	public DwcaResourceModel load(Integer auto_id);
	
	/**
	 * Load a ResourceModel from a resource_uuid
	 * 
	 * @param resource_uuid
	 * @return ResourceModel or null if nothing is found
	 */
	public DwcaResourceModel loadBySourceFileId(String resource_uuid);

	/**
	 * Save a ResourceModel
	 * 
	 * @param DwcaResourceModel
	 * @return success or not
	 */
	public boolean save(DwcaResourceModel resourceModel);

}
