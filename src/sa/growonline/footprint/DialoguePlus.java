package sa.growonline.footprint;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.utils.ZuniUtils;

/**
 * Created by Jawed on 10/29/2016.
 */
public class DialoguePlus extends BaseActivityx implements View.OnClickListener {

    //Text LinearLayouts
    private LinearLayout mLinearLayoutTxtFaqs;
    private LinearLayout mLinearLayoutTxtHowToUse;
    private LinearLayout mLinearLayoutTxtNewsletter;
    private LinearLayout mLinearLayoutTxtWhatsNext;
    private LinearLayout mLinearLayoutTxtContact;

    //Headings text
    private TextView mHeadingPlus;
    private TextView mHeadingFaqs;
    private TextView mHeadingHowToUse;
    private TextView mHeadingNewsletter;
    private TextView mHeadingWhatsNext;
    private TextView mHeadingContact;

    //TextViews
    private TextView mtxtFaqs;
    private TextView mtxtHowToUse;
    private TextView mtxtNewsletter;
    private TextView mtxtWhatsNext;
    private TextView mtxtContact;

    //close
    private TextView mCloseFaqs;
    private TextView mCloseHowToUse;
    private TextView mCloseNewsletter;
    private TextView mCloseWhatsNext;
    private TextView mCloseContact;


    private View mCurrentView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogue_plus);
        overridePendingTransition(R.anim.activity_bottom_to_top, 0);
        removeToolbar();

        //Text LinearLayouts
        mLinearLayoutTxtFaqs = (LinearLayout) findViewById(R.id.dialogue_linearlayout_txt_faqs);
        mLinearLayoutTxtHowToUse = (LinearLayout) findViewById(R.id.dialogue_linearlayout_txt_how_to_use);
        mLinearLayoutTxtNewsletter = (LinearLayout) findViewById(R.id.dialogue_linearlayout_txt_newsletter);
        mLinearLayoutTxtWhatsNext = (LinearLayout) findViewById(R.id.dialogue_linearlayout_txt_whats_next);
        mLinearLayoutTxtContact = (LinearLayout) findViewById(R.id.dialogue_linearlayout_txt_contact);

        //Headings text
        mHeadingPlus = (TextView) findViewById(R.id.dialogue_heading_plus);
        mHeadingFaqs = (TextView) findViewById(R.id.dialogue_heading_faqs);
        mHeadingHowToUse = (TextView) findViewById(R.id.dialogue_heading_how_to_use);
        mHeadingNewsletter = (TextView) findViewById(R.id.dialogue_heading_newsletter);
        mHeadingWhatsNext = (TextView) findViewById(R.id.dialogue_heading_whats_next);
        mHeadingContact = (TextView) findViewById(R.id.dialogue_heading_contact);

        //HeaderFont
        ZuniUtils.applyHeaderAppFont(mHeadingPlus);
        ZuniUtils.applyHeaderAppFont(mHeadingFaqs);
        ZuniUtils.applyHeaderAppFont(mHeadingHowToUse);
        ZuniUtils.applyHeaderAppFont(mHeadingNewsletter);
        ZuniUtils.applyHeaderAppFont(mHeadingWhatsNext);
        ZuniUtils.applyHeaderAppFont(mHeadingContact);

        //textviews
        mtxtFaqs = (TextView) findViewById(R.id.dialogue_txt_faqs);
        mtxtHowToUse = (TextView) findViewById(R.id.dialogue_txt_how_to_use);
        mtxtNewsletter= (TextView) findViewById(R.id.dialogue_txt_newsletter);
        mtxtWhatsNext = (TextView) findViewById(R.id.dialogue_txt_whats_next);
        mtxtContact = (TextView) findViewById(R.id.dialogue_txt_contact);

        //TextFont
        ZuniUtils.applyAppFont(mtxtFaqs);
        ZuniUtils.applyAppFont(mtxtHowToUse);
        ZuniUtils.applyAppFont(mtxtNewsletter);
        ZuniUtils.applyAppFont(mtxtWhatsNext);
        ZuniUtils.applyAppFont(mtxtContact);

        mHeadingFaqs.setOnClickListener(this);
        mHeadingHowToUse.setOnClickListener(this);
        mHeadingNewsletter.setOnClickListener(this);
        mHeadingWhatsNext.setOnClickListener(this);
        mHeadingContact.setOnClickListener(this);


        //close
        mCloseFaqs = (TextView) findViewById(R.id.dialogue_txt_faqs_close);
        mCloseHowToUse = (TextView) findViewById(R.id.dialogue_txt_how_to_use_close);
        mCloseNewsletter = (TextView) findViewById(R.id.dialogue_txt_newsletter_close);
        mCloseWhatsNext = (TextView) findViewById(R.id.dialogue_txt_whats_next_close);
        mCloseContact = (TextView) findViewById(R.id.dialogue_txt_contact_close);

        mCloseFaqs.setOnClickListener(this);
        mCloseHowToUse.setOnClickListener(this);
        mCloseNewsletter.setOnClickListener(this);
        mCloseWhatsNext.setOnClickListener(this);
        mCloseContact.setOnClickListener(this);


        //closebuttonfont
        ZuniUtils.applyAppFont(mCloseFaqs);
        ZuniUtils.applyAppFont(mCloseHowToUse);
        ZuniUtils.applyAppFont(mCloseNewsletter);
        ZuniUtils.applyAppFont(mCloseWhatsNext);
        ZuniUtils.applyAppFont(mCloseContact);

    }



    @Override
    public void onClick(View v) {

        if (v == mHeadingFaqs)
        {
            managevisibility(mLinearLayoutTxtFaqs);
        }
        else if (mHeadingHowToUse == v)
        {
            managevisibility(mLinearLayoutTxtHowToUse);
        }
        else if (mHeadingNewsletter == v)
        {
            managevisibility(mLinearLayoutTxtNewsletter);

        }
        else if (mHeadingWhatsNext == v)
        {
            managevisibility(mLinearLayoutTxtWhatsNext);
        }
        else if (mHeadingContact == v)
        {
            managevisibility(mLinearLayoutTxtContact);
        }

        else if (mCloseFaqs == v)
        {
            managevisibility(mLinearLayoutTxtFaqs);
        }

        else if (mCloseHowToUse == v)
        {
            managevisibility(mLinearLayoutTxtHowToUse);
        }

        else if (mCloseNewsletter == v)
        {
            managevisibility(mLinearLayoutTxtNewsletter);
        }

        else if (mCloseWhatsNext == v)
        {
            managevisibility(mLinearLayoutTxtWhatsNext);
        }

        else if (mCloseContact == v)
        {
            managevisibility(mLinearLayoutTxtContact);
        }
    }

/*@Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_top_to_bottom, 0);
    }*/



    private void managevisibility(View view) {

        if (view.getVisibility() == View.VISIBLE)
        {
            view.setVisibility(View.GONE);
            mCurrentView = null;
        }
        else
        {
            if (mCurrentView != null)
                mCurrentView.setVisibility(View.GONE);

            mCurrentView = view;
            mCurrentView.setVisibility(View.VISIBLE);
        }


    }

}