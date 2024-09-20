package com.example.flashcardapp;



import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DataEntry")
public class DataEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String question;
    @NonNull
    private String option1;
    @NonNull
    private String option2;
    @NonNull
    private String option3;
    @NonNull
    private String option4;
    @NonNull
    private String rightAnswer;

    public void setId(int id) {
        this.id = id;
    }

    public void setRightAnswer(@NonNull String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getRightAnswer() {
        return rightAnswer;
    }

    public DataEntry(@NonNull String question, @NonNull String option1, @NonNull String option2, @NonNull String option3, @NonNull String option4, @NonNull String rightAnswer) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.rightAnswer = rightAnswer;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    public void setOption1(@NonNull String option1) {
        this.option1 = option1;
    }

    public void setOption2(@NonNull String option2) {
        this.option2 = option2;
    }

    public void setOption3(@NonNull String option3) {
        this.option3 = option3;
    }

    public void setOption4(@NonNull String option4) {
        this.option4 = option4;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    @NonNull
    public String getOption1() {
        return option1;
    }

    @NonNull
    public String getOption2() {
        return option2;
    }

    @NonNull
    public String getOption3() {
        return option3;
    }

    @NonNull
    public String getOption4() {
        return option4;
    }
}
