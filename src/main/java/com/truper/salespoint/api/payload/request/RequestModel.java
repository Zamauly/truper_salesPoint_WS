package com.truper.salespoint.api.payload.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class RequestModel<T> {
	
	@NotNull(message = "sistemaOrigen is required")
	public String sistemaOrigen;
	
	@NotNull(message = "data type to process is required")
	public T data;
	
	public RequestModel() {}
	
	public RequestModel(String sistemaOrigen, @Valid  T data) {
		super();
		this.sistemaOrigen = sistemaOrigen;
		this.data = data;
	}

	public String getSistemaOrigen() {
		return sistemaOrigen;
	}

	public void setSistemaOrigen(String sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseModel [sistemaOrigen=" + sistemaOrigen + ", data=" + data + "]";
	}
	
}
