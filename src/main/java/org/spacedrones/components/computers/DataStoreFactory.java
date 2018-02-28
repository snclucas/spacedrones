package org.spacedrones.components.computers;

import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftDataProvider;

import java.security.InvalidParameterException;

public class DataStoreFactory {

	public final static String BASIC_DATASTORE="Basic datastore";

	public static DataStore getDataStore(String dataStoreType) throws InvalidParameterException{
		SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();

		switch (dataStoreType) {

		case BASIC_DATASTORE:
      return new BasicDataStorageUnit("Data store",
          spacecraftDataProvider.getComponentParameters(BasicDataStorageUnit.class.getSimpleName()).getBusComponentSpecification());
		default:
			throw new InvalidParameterException("No such data store.");

		}


	}

}
