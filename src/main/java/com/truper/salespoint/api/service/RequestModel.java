package com.truper.salespoint.api.service;

public class RequestModel<T> {
	public String sistemaOrigen;
	public T data;
	
	public RequestModel() {}
	
	public RequestModel(String sistemaOrigen, T data) {
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
