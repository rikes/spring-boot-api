package br.com.rickes.enumerat;

public enum GenderEnum {
	
	MASCULINO(0),FEMININO(1);	
	
	private int code;

    private GenderEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    public static GenderEnum fromCode(int code) {
        
    	switch(code) {
	        case 0:
	            return MASCULINO;
	        case 1:
	            return FEMININO;
	        default:
	        	return null;
        }
    }
}
