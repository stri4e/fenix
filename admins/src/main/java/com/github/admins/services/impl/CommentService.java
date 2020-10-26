package com.github.admins.services.impl;

import com.github.admins.dto.CommentDto;
import com.github.admins.services.ICommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    @Override
    public Optional<CommentDto> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
