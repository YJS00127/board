package com.example.board.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class Pagination<T> {
    private final int pageIndex;
    private final int pageSize;
    private final int totalCount;
    private final List<T> data;

    public Pagination(int pageIndex, int pageSize, int totalCount, List<T> data){
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
    }

    public int getTotalPage() {
        return (int)Math.ceil((double)totalCount/pageSize);
    }
}
