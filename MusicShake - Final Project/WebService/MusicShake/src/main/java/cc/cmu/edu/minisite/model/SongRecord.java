package cc.cmu.edu.minisite.model;

public class SongRecord {
    private int sid;
    private String name;
    private String singer;
    private String cover;
    private String genre;
    private String address;
    private int length;

    public SongRecord() {
        name = "myName";
        singer = "mySinger";
        cover = "myCover";
        genre = "myGenre";
        address = "myAddr";
        length = 0;
    }

    public SongRecord(String name, String singer, String cover, String genre, String address, int length) {
        this.name = name;
        this.singer = singer;
        this.cover = cover;
        this.genre = genre;
        this.address = address;
        this.length = length;
    }

    public SongRecord(int sid, String name, String singer, String cover, String genre, String address, int length) {
        this.sid = sid;
        this.name = name;
        this.singer = singer;
        this.cover = cover;
        this.genre = genre;
        this.address = address;
        this.length = length;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
