package com.example.machachong.youdu.view.associatemail;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

public class MailBoxAssociateView extends MultiAutoCompleteTextView {
    public MailBoxAssociateView(Context context)
    {
        super(context);
    }

    public MailBoxAssociateView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MailBoxAssociateView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean enoughToFilter()
    {
        return getText().toString().contains("@") && getText().toString().indexOf("@") > 0;
    }
}
