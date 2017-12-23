package me.xdrop.jrand.generators.money;

public class IINRange {

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    private int begin;
    private int end;

    private IINRange(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    static IINRange from(int begin, int end) {
        return new IINRange(begin, end);
    }

    static IINRange from(int begin) {
        return new IINRange(begin, -1);
    }

    public String toString(){
        if (end == -1){
            return "" + begin;
        } else {
            return begin + "-" + end;
        }
    }

}
