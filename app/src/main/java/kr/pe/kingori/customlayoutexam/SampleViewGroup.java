package kr.pe.kingori.customlayoutexam;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * simple viewgroup layouts
 * - image on left | center_vertical,
 * - (tv + sub ) on left of image | center_vertical
 * - sub below tv
 */
public class SampleViewGroup extends ViewGroup implements DataPresenter<SampleItem> {
    private ImageView iv;
    private TextView name;
    private TextView sub;

    public SampleViewGroup(Context context) {
        super(context);
    }

    public SampleViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SampleViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iv = (ImageView) findViewById(R.id.iv);
        name = (TextView) findViewById(R.id.name);
        sub = (TextView) findViewById(R.id.sub);
    }

    private int viewContentHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildWithMargins(iv, widthMeasureSpec, 0,
                heightMeasureSpec, 0);

        int usedWidth = getViewWidthWithMargin(iv);

        //제대로 하려면 name, sub height를 다 계산해보고 해야겠지만 귀찮아서...
        measureChildWithMargins(name, widthMeasureSpec, usedWidth, heightMeasureSpec, 0);
        measureChildWithMargins(sub, widthMeasureSpec, usedWidth, heightMeasureSpec, 0);

        viewContentHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int ivTop = (viewContentHeight - iv.getMeasuredHeight()) / 2 + getPaddingTop();
        int ivRight = getPaddingLeft() + iv.getMeasuredWidth();
        iv.layout(getPaddingLeft(), ivTop, ivRight, ivTop + iv.getMeasuredHeight());

        int nameTop = (viewContentHeight - getViewHeightWithMargin(name) - getViewHeightWithMargin(sub)) / 2 + getPaddingTop();
        int subTop = nameTop + getViewHeightWithMargin(name);

        int nameLeft = ivRight + ((MarginLayoutParams) iv.getLayoutParams()).rightMargin;

        name.layout(nameLeft, nameTop, nameLeft + name.getMeasuredWidth(), nameTop + name.getMeasuredHeight());
        sub.layout(nameLeft, subTop, nameLeft + sub.getMeasuredWidth(), subTop + sub.getMeasuredHeight());
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    public void apply(SampleItem data) {
        iv.setBackgroundColor(data.getImageBgColor());
        name.setText(data.getName());
        sub.setText(data.getSubString());
    }

    private int getViewHeightWithMargin(View v) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) v.getLayoutParams();
        return v.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    private int getViewWidthWithMargin(View v){
        MarginLayoutParams layoutParams = (MarginLayoutParams) v.getLayoutParams();
        return v.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
    }
}
