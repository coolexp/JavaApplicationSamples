package com.nikoer.aicarb.vo;

import java.util.ArrayList;
import java.util.List;

public class UserInfoVO {
	private String name;
	private List<ItemInfoVO> itemList = new ArrayList<ItemInfoVO>();
	public List<ItemInfoVO> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemInfoVO> itemList) {
		this.itemList = itemList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public static UserInfoVO create(String name,String itemList) {
		UserInfoVO vo = new UserInfoVO();
		vo.setName(name);
    	List<ItemInfoVO> _list = new ArrayList<ItemInfoVO>();
    	String[] iList = itemList.split(";");
    	for(int i = 0;i<iList.length;i++) {
    		String itemData = iList[i];
    		String[] d = itemData.split(",");
    		ItemInfoVO item = new ItemInfoVO();
        	item.setItemName(d[0]);
        	item.setItemNum(Integer.parseInt(d[1]));
        	_list.add(item);
    	}
    	vo.setItemList(_list);
		return vo;
	}
}
