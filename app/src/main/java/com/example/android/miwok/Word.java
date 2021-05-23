package com.example.android.miwok;

public class Word {
    //Miwok translation
    private String mMiwokTranslation;

    //default translation
    private String mDefaultTranslation;

    //default image
    private int mImageID;

    //miwok audio
    private int mAudioId;

    public Word(String defaultTranslation,String miwokTranslation,int mAudio)
    {
        mMiwokTranslation=miwokTranslation;
        mDefaultTranslation=defaultTranslation;
        this.mAudioId =mAudio;
    }

    /**
     * parametrized  Constructor
     */
    public Word( String defaultTranslation,String miwokTranslation , int imageID,int mAudioId) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageID=imageID;
        this.mAudioId = mAudioId;

    }

    /**
     * @return miwok translation
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * @return default translation
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     *
     * @return resource id for image
     */
    public int getImageID(){
        return mImageID;
    }

    /**
     *
     * @return whether the object has valid image or not
     */
    public boolean hasImage(){
        return mImageID!=0;
    }

    /**
     *
     * @return resource id of audio file
     */
    public int getAudioID(){return mAudioId;}

    /**
     *
     * @return the current starte of app
     */
    @Override
    public String toString() {
        return "Word{" +
                "mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mImageID=" + mImageID +
                ", mAudioId=" + mAudioId +
                '}';
    }
}
