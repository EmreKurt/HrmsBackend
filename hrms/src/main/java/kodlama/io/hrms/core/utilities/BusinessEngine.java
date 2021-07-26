package kodlama.io.hrms.core.utilities;

import kodlama.io.hrms.core.result.*;

public class BusinessEngine {
	
	public static Result run(Result... logics) {
		
		for (var logic : logics) {
			if (!logic.isSuccess()) {
				return logic;
			}
		}
		return null;
	}
}
