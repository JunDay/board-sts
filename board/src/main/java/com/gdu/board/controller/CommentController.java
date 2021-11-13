package com.gdu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.board.service.CommentService;
import com.gdu.board.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/deleteComment")
	public String deleteComment(Comment comment, int boardNo) {
		log.debug("[Debug] \"START\" CommentController.deleteComment().Get()");
		commentService.deleteComment(comment, boardNo);
		
		return "redirect:/boardOne?boardNo="+boardNo;
	}
	
	@GetMapping("/addComment")
	public String addComment() {
		log.debug("[Debug] \"START\" CommentController.addComment().Get()");
		return "addComment";
	}
	@PostMapping("/addComment")
	public String addComment(Comment comment, int boardNo) {
		log.debug("[Debug] \"START\" CommentController.addComment().Post()");
		commentService.addComment(comment, boardNo);
		log.debug(comment.toString());
		
		return "redirect:/boardOne?boardNo="+comment.getBoard().getBoardNo();
	}
}
