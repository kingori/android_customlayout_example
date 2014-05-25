package kr.pe.kingori.customlayoutexam;

import android.content.Context;
import android.util.AttributeSet;
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildWithMargins(iv, MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.AT_MOST), 0,
                heightMeasureSpec, 0);

        int usedWidth = iv.getMeasuredWidth();

        measureChildWithMargins(name, widthMeasureSpec, usedWidth, heightMeasureSpec, 0);
        measureChildWithMargins(sub, widthMeasureSpec, usedWidth, heightMeasureSpec, 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int ivTop = (getMeasuredHeight() - iv.getMeasuredHeight() - getPaddingTop() - getPaddingBottom()) / 2 + getPaddingTop();
        int ivRight = getPaddingLeft() + iv.getMeasuredWidth();
        iv.layout(getPaddingLeft(), ivTop, ivRight, ivTop + iv.getMeasuredHeight());

        int nameTop = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - name.getMeasuredHeight() - sub.getMeasuredHeight() - ((MarginLayoutParams) name.getLayoutParams()).bottomMargin) / 2 + getPaddingTop();
        int subTop = nameTop + name.getMeasuredHeight() + ((MarginLayoutParams) name.getLayoutParams()).bottomMargin;

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
}
