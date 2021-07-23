package com.github.products.services.impl;

import com.github.products.config.ServiceApplicationContextConfig;
import com.github.products.entity.Comment;
import com.github.products.entity.EntityStatus;
import com.github.products.exceptions.EntityBadRequest;
import com.github.products.exceptions.ParametersBadRequest;
import com.github.products.repository.CommentRepo;
import com.github.products.services.ICommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.github.products.services.impl.CommentServiceMocks.entity_inDatabase;
import static com.github.products.services.impl.CommentServiceMocks.entity_toSave;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Import(ServiceApplicationContextConfig.class)
public class CommentServiceTest {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ICommentService commentService;

    @Test
    public void givenComment_whenCreate_thenSaveAndReturnComment() {
        Comment comment = entity_toSave();
        Comment exp = entity_inDatabase();
        Mockito.when(this.commentRepo.save(comment))
                .thenReturn(exp);
        Comment act = this.commentService.create(comment);
        assertEquals("Comments should be equals", exp, act);
    }

    @Test(expected = EntityBadRequest.class)
    public void givenNullComment_whenCreate_thenThrowEntityBadRequest() {
        this.commentService.create(null);
    }

    @Test
    public void givenCommentId_whenReadById_thenReturnComment() {
        var commentId = 1L;
        Comment exp = entity_inDatabase();
        Mockito.when(this.commentRepo.findById(commentId))
                .thenReturn(Optional.of(exp));
        Comment act = this.commentService.readById(commentId);
        assertEquals("Comments should be equals", exp, act);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenNullCommentId_whenReadById_thenThrowParametersBadRequest() {
        this.commentService.readById(null);
    }

    @Test
    public void givenCommentId_whenRemove_thenDoSetStatusOff() {
        var commentId = 1L;
        Mockito.doNothing().when(this.commentRepo)
                .updateStatus(commentId, EntityStatus.off);
        this.commentService.remove(commentId);
        Mockito.verify(this.commentRepo)
                .updateStatus(commentId, EntityStatus.off);
    }

    @Test(expected = ParametersBadRequest.class)
    public void givenNullCommentId_whenRemove_thenThrowParametersBadRequest() {
        this.commentService.remove(null);
    }

}
