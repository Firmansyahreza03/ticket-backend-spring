package com.lawencon.ticket.dto.threaddtl;

import java.util.List;

public class FindAllThreadDtlRes {
	private List<ThreadDtlData> datas;
	private Long id;

	public List<ThreadDtlData> getDatas() {
		return datas;
	}

	public void setDatas(List<ThreadDtlData> datas) {
		this.datas = datas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
