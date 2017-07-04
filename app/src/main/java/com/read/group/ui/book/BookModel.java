package com.read.group.ui.book;

import com.read.group.R;
import com.read.group.base.annotation.ModelView;
import com.read.group.base.model.ViewModel;

import javax.inject.Inject;

/**
 * Created by apple on 2017/7/2.
 */
@ModelView(R.layout.activity_book)
public class BookModel extends ViewModel<BookActivity> {
    @Inject
    public BookModel() {}
}
