package com.example.recyclerviewwithsearchview;


public class EjemploModel
{
    private final long mId;
    private final String mText;

    public EjemploModel(long id, String text) {
        mId = id;
        mText = text;
    }

    public long getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EjemploModel model = (EjemploModel) o;

        if (mId != model.mId) return false;
        return mText != null ? mText.equals(model.mText) : model.mText == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        return result;
    }
}
