package com.example.eat_res_halc.Callback;

import com.example.eat_res_halc.Model.CommentModel;

import java.util.List;

public interface ICommentCallbackListener {
    void onCommentLoadSuccess(List<CommentModel> commentModels);

    void onCommentLoadFailed(String message);
}
