package br.com.go.notification;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

	public static <D, T> Page<D> mapPageBiDirectional(Page<T> pageValue, Class<D> classDestination) {
		ModelMapper mapper = new ModelMapper();
		return pageValue.map(object -> mapper.map(object, classDestination));
	}

	public static <D, T> List<D> mapListBiDirectional(List<T> listValue, Class<D> classDestination) {
		ModelMapper mapper = new ModelMapper();
		return listValue.stream().map(object -> mapper.map(object, classDestination)).collect(Collectors.toList());
	}

}
