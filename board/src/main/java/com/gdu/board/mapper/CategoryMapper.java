package com.gdu.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.board.vo.Category;

@Mapper
public interface CategoryMapper {
	void deleteCategory(String categoryName);
	void insertCategory(Category cateogry);
	List<Category> selectCategoryList();
}
