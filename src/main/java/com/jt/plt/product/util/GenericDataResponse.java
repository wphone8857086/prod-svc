package com.jt.plt.product.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//import io.swagger.annotations.ApiModelProperty;

@Data
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericDataResponse<T> extends GenericResponse {
	
	@ApiModelProperty(name = "data", value = "数据对象", position=3)
	@JsonProperty("data")
	private T data;
	
	public static <T> ResponseEntity<?> okWithData(T data) {
		GenericDataResponse<T> resp = new GenericDataResponse<T>();
		resp.setCode(GenericResponse.CODE_OK);
		resp.setData(data);
		return ResponseEntity.ok(resp);
	}
	
	public static <T> ResponseEntity<?> okWithData(T data, HttpHeaders headers) {
		GenericDataResponse<T> resp = new GenericDataResponse<T>();
		resp.setCode(GenericResponse.CODE_OK);
		resp.setData(data);
		return new ResponseEntity<>(resp, headers, HttpStatus.OK);
	}
}
