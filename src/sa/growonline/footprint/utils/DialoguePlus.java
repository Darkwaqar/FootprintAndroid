package sa.growonline.footprint.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import sa.growonline.footprint.R;

public class DialoguePlus implements View.OnClickListener{

    private LinearLayout mLinearLayoutTxtFaqs;
    private LinearLayout mLinearLayoutTxtHowToUse;
    private LinearLayout mLinearLayoutTxtNewsletter;
    private LinearLayout mLinearLayoutTxtWhatsNext;
    private LinearLayout mLinearLayoutTxtContact;

    private TextView mHeadingFaqs;
    private TextView mHeadingHowToUse;
    private TextView mHeadingNewsletter;
    private TextView mHeadingWhatsNext;
    private TextView mHeadingContact;

    private TextView mCloseFaqs;
    private TextView mCloseHowToUse;
    private TextView mCloseNewsletter;
    private TextView mCloseWhatsNext;
    private TextView mCloseContact;


    private View mCurrentView;
    private Activity mActivity;

    public DialoguePlus(Activity activityHomeMaterialViewPager) {
    mActivity = activityHomeMaterialViewPager;
    }

    public void showDialog() {
        Dialog mDialog = new Dialog(mActivity, R.style.PauseDialog);
        mDialog.setContentView(R.layout.dialogue_plus);

        InitUI(mDialog);
        mDialog.setCancelable(true);
        mDialog.show();
    }

    private void InitUI(Dialog mDialog) {
        mLinearLayoutTxtFaqs = (LinearLayout) mDialog.findViewById(R.id.dialogue_linearlayout_txt_faqs);
        mLinearLayoutTxtHowToUse = (LinearLayout) mDialog.findViewById(R.id.dialogue_linearlayout_txt_how_to_use);
        mLinearLayoutTxtNewsletter = (LinearLayout) mDialog.findViewById(R.id.dialogue_linearlayout_txt_newsletter);
        mLinearLayoutTxtWhatsNext = (LinearLayout) mDialog.findViewById(R.id.dialogue_linearlayout_txt_whats_next);
        mLinearLayoutTxtContact = (LinearLayout) mDialog.findViewById(R.id.dialogue_linearlayout_txt_contact);

        TextView mHeadingPlus = (TextView) mDialog.findViewById(R.id.dialogue_heading_plus);
        mHeadingFaqs = (TextView) mDialog.findViewById(R.id.dialogue_heading_faqs);
        mHeadingHowToUse = (TextView) mDialog.findViewById(R.id.dialogue_heading_how_to_use);
        mHeadingNewsletter = (TextView) mDialog.findViewById(R.id.dialogue_heading_newsletter);
        mHeadingWhatsNext = (TextView) mDialog.findViewById(R.id.dialogue_heading_whats_next);
        mHeadingContact = (TextView) mDialog.findViewById(R.id.dialogue_heading_contact);


        ZuniUtils.applyHeaderAppFont(mHeadingPlus);
        ZuniUtils.applyHeaderAppFont(mHeadingFaqs);
        ZuniUtils.applyHeaderAppFont(mHeadingHowToUse);
        ZuniUtils.applyHeaderAppFont(mHeadingNewsletter);
        ZuniUtils.applyHeaderAppFont(mHeadingWhatsNext);
        ZuniUtils.applyHeaderAppFont(mHeadingContact);

        TextView mTxtFaqs = (TextView) mDialog.findViewById(R.id.dialogue_txt_faqs);
        TextView mTxtHowToUse = (TextView) mDialog.findViewById(R.id.dialogue_txt_how_to_use);
        TextView mTxtNewsletter = (TextView) mDialog.findViewById(R.id.dialogue_txt_newsletter);
        TextView mTxtWhatsNext = (TextView) mDialog.findViewById(R.id.dialogue_txt_whats_next);
        TextView mTxtContact = (TextView) mDialog.findViewById(R.id.dialogue_txt_contact);


        ZuniUtils.applyAppFont(mTxtFaqs);
        ZuniUtils.applyAppFont(mTxtHowToUse);
        ZuniUtils.applyAppFont(mTxtNewsletter);
        ZuniUtils.applyAppFont(mTxtWhatsNext);
        ZuniUtils.applyAppFont(mTxtContact);

        mHeadingFaqs.setOnClickListener(this);
        mHeadingHowToUse.setOnClickListener(this);
        mHeadingNewsletter.setOnClickListener(this);
        mHeadingWhatsNext.setOnClickListener(this);
        mHeadingContact.setOnClickListener(this);


        mCloseFaqs = (TextView) mDialog.findViewById(R.id.dialogue_txt_faqs_close);
        mCloseHowToUse = (TextView) mDialog.findViewById(R.id.dialogue_txt_how_to_use_close);
        mCloseNewsletter = (TextView) mDialog.findViewById(R.id.dialogue_txt_newsletter_close);
        mCloseWhatsNext = (TextView) mDialog.findViewById(R.id.dialogue_txt_whats_next_close);
        mCloseContact = (TextView) mDialog.findViewById(R.id.dialogue_txt_contact_close);

        mCloseFaqs.setOnClickListener(this);
        mCloseHowToUse.setOnClickListener(this);
        mCloseNewsletter.setOnClickListener(this);
        mCloseWhatsNext.setOnClickListener(this);
        mCloseContact.setOnClickListener(this);


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
            manageVisibility(mLinearLayoutTxtFaqs);
        }
        else if (mHeadingHowToUse == v)
        {
            manageVisibility(mLinearLayoutTxtHowToUse);
        }
        else if (mHeadingNewsletter == v)
        {
            manageVisibility(mLinearLayoutTxtNewsletter);

        }
        else if (mHeadingWhatsNext == v)
        {
            manageVisibility(mLinearLayoutTxtWhatsNext);
        }
        else if (mHeadingContact == v)
        {
            manageVisibility(mLinearLayoutTxtContact);
        }

        else if (mCloseFaqs == v)
        {
            manageVisibility(mLinearLayoutTxtFaqs);
        }

        else if (mCloseHowToUse == v)
        {
            manageVisibility(mLinearLayoutTxtHowToUse);
        }

        else if (mCloseNewsletter == v)
        {
            manageVisibility(mLinearLayoutTxtNewsletter);
        }

        else if (mCloseWhatsNext == v)
        {
            manageVisibility(mLinearLayoutTxtWhatsNext);
        }

        else if (mCloseContact == v)
        {
            manageVisibility(mLinearLayoutTxtContact);
        }
    }


    private void manageVisibility(View view) {

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

