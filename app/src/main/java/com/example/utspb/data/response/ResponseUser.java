package com.example.utspb.data.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseUser{

	@SerializedName("items")
	private List<ItemsItem> items;

	public List<ItemsItem> getItems(){
		return items;
	}
}