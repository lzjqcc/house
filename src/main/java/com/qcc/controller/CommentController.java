package com.qcc.controller;

import com.qcc.dao.dto.CommentDto;
import com.qcc.service.CommentService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping(value = "/pushComment")
    public ResponseVO pushComment(@RequestBody CommentDto commentDto) {
        return commentService.pushComment(CommUtils.getCurrentAccount(), commentDto);
    }
    @GetMapping(value = "/pullComment")
    public ResponseVO pullComment(Integer houseId) {
        return commentService.pullComment(houseId);
    }
}
