package fa.gb.rest.request;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseInputRequest<T> {
	
	private final T input;
	
	public BaseInputRequest() {
		this.input = null;
	}
	
	public BaseInputRequest(T input) {
		this.input = input;
	}
	
	public T getInput() {
		return this.input;
	}

}
