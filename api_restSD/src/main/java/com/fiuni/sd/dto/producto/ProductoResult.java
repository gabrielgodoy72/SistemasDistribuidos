package com.fiuni.sd.dto.producto;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "productoResult")
public class ProductoResult extends BaseResult<ProductoDTO> {

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public List<ProductoDTO> getProductos() {
		return getList();
	}

	public void setProductos(List<ProductoDTO> dtos) {
		super.setList(dtos);
	}

}
