package com.fci.colors_app.data.models;

import com.google.gson.annotations.SerializedName;

public class FAQsModel {

    /**
     * id : 4
     * question : ما هي ساعات العمل في يكفيك؟
     * answer : ٢٤/٧
     */

    @SerializedName("id")
    private int id;
    @SerializedName("question")
    private String question;
    @SerializedName("answer")
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
