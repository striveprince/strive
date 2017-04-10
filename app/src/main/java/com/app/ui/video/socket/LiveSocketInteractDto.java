package com.app.ui.video.socket;

/**
 * Created by maomao on 2017/3/15.
 */

public class LiveSocketInteractDto implements WebSocketDto {


    private String type;
    private int room;
    private String title;
    private String index_img;
    private long create_time;
    private int during;
    private String interaction_type;
    private String status;
    private int if_voted;
    private int id;

    private LiveSocketVoteDto[] vote;
    private LiveSocketNewsDto news;

    private int sum;

    public int getSum() {
        if(sum==0){
            for(LiveSocketVoteDto dto :getVote()){
                sum+=dto.getOption_count();
            }
        }
        return sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndex_img() {
        return index_img;
    }

    public void setIndex_img(String index_img) {
        this.index_img = index_img;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getDuring() {
        return during;
    }

    public void setDuring(int during) {
        this.during = during;
    }

    public String getInteraction_type() {
        return interaction_type;
    }

    public void setInteraction_type(String interaction_type) {
        this.interaction_type = interaction_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LiveSocketVoteDto[] getVote() {
        return vote;
    }

    public void setVote(LiveSocketVoteDto[] vote) {
        this.vote = vote;
    }

    public LiveSocketNewsDto getNews() {
        return news;
    }

    public void setNews(LiveSocketNewsDto news) {
        this.news = news;
    }

    public int getIf_voted() {
        return if_voted;
    }

    public void setIf_voted(int if_voted) {
        this.if_voted = if_voted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
