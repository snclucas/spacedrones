package org.spacedrones.components;

public class TypeInfo { 
	
    public final String typeIdString;

    public TypeInfo(String typeId) {
    	this.typeIdString = typeId;
    }
    
    
    

	@Override
	public String toString() {
		return typeIdString;
	}




	@Override
	public int hashCode() {
		//Safe to use string hashcodes
		return typeIdString.hashCode();
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		TypeInfo other = (TypeInfo) obj;
		if (typeIdString == null) {
			if (other.typeIdString != null)
				return false;
		} else if (!typeIdString.equals(other.typeIdString))
			return false;
		return true;
	}
    
    
}