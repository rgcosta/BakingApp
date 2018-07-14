package com.example.romul.bakingapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.romul.bakingapp.Models.Step;
import com.example.romul.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import static com.google.android.exoplayer2.util.Util.*;

public class StepDetailLayoutFragment extends Fragment {

    private static final String STEP_KEY = "step_key";
    private static final String VIDEO_CURRENT_POSITION_KEY = "video_current_position_key";
    private static final String IS_PLAYING_KEY = "is_playing_key";
    private static final String TAG = StepDetailLayoutFragment.class.getSimpleName();

    private Step mStep;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private long mVideoPosition;
    private boolean isPlaying;
    private TextView mFullDesc;
    private TextView mShortDesc;


    public StepDetailLayoutFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(STEP_KEY)){
                mStep = (Step) savedInstanceState.getSerializable(STEP_KEY);
            }
            if (savedInstanceState.containsKey(VIDEO_CURRENT_POSITION_KEY)){
                mVideoPosition = savedInstanceState.getLong(VIDEO_CURRENT_POSITION_KEY);
                isPlaying = savedInstanceState.getBoolean(IS_PLAYING_KEY);
            }
        }

        View rootView = inflater.inflate(R.layout.fragment_step_detail_layout, container, false);

        mPlayerView = rootView.findViewById(R.id.playerView);
        mFullDesc = rootView.findViewById(R.id.tv_full_description);
        mShortDesc = rootView.findViewById(R.id.tv_short_description);

        mFullDesc.setText(mStep.getFullDescription());
        mShortDesc.setText(mStep.getShortDescription());

        if (mStep.getVideoURL().length() > 0){
            initializePlayer(Uri.parse(mStep.getVideoURL()));
            Log.e(TAG, "onCreateView - playVideo " + mStep.getId() );
        } else {
            mPlayerView.setVisibility(View.GONE);
            Log.e(TAG, "onCreateView - No Video Player " + mStep.getId());
        }

        return rootView;
    }

    private void initializePlayer(Uri mediaUri) {

        if (mExoPlayer == null){
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            // Set the ExoPlayer.EventListener to this activity.
            //mExoPlayer.addListener(this);

            // Prepare the MediaSource.
            String userAgent = getUserAgent(getContext(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            if (mVideoPosition > 0){
                Log.e(TAG, "initializePlayer - keep playing: " + mVideoPosition);
                mExoPlayer.seekTo(mVideoPosition);
                mExoPlayer.setPlayWhenReady(isPlaying);
            } else {
                Log.e(TAG, "initializePlayer - first play");
                mExoPlayer.setPlayWhenReady(false);
            }
        }
    }


    public void setStep(Step mStep) {
        this.mStep = mStep;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(STEP_KEY, mStep);
        if (mExoPlayer != null) {
            outState.putLong(VIDEO_CURRENT_POSITION_KEY, mExoPlayer.getCurrentPosition());
            outState.putBoolean(IS_PLAYING_KEY, mExoPlayer.getPlayWhenReady());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
        Log.e(TAG, "onDestroyView " + mStep.getId());
    }

    private void releasePlayer() {

        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    public void pausePlayer(){
        if (mExoPlayer != null){
            Log.e(TAG, "pausePlayer");
            mExoPlayer.setPlayWhenReady(false);
        }
    }


}
