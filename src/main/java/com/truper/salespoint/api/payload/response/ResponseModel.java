package com.truper.salespoint.api.payload.response;

public class ResponseModel<T> {
	public String result;
	public String mensaje;
	public T data;
	
	public ResponseModel() {}
	
	public ResponseModel(String result, String mensaje, T data) {
		super();
		this.result = result;
		this.mensaje = mensaje;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseModel [result=" + result + ", mensaje=" + mensaje + ", data=" + data + "]";
	}
	
}
