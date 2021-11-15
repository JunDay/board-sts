package com.gdu.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.board.mapper.CategoryMapper;
import com.gdu.board.vo.Category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
	
	public void deleteCategory(String categoryName) {
		log.debug("[Debug] \"START\" CategoryService.deleteCategory()");
		categoryMapper.deleteCategory(categoryName);
	}
	public void insertCategory(Category cateogry) {
		log.debug("[Debug] \"START\" CategoryService.insertCategory()");
		categoryMapper.insertCategory(cateogry);
	}
	
	public List<Category> selectCategoryList() {
		log.debug("[Debug] \"START\" CategoryService.selectCategoryList()");
		
		return categoryMapper.selectCategoryList();
	}
}
