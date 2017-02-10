package com.thoersch.lingo24.representations;

public class PagingInput {
    private int size;
    private int page;
    private String sort;

    public PagingInput() { }

    public PagingInput(int size, int page, String sort) {
        this.size = size;
        this.page = page;
        this.sort = sort;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PagingInput)) return false;

        PagingInput that = (PagingInput) o;

        if (size != that.size) return false;
        if (page != that.page) return false;
        return !(sort != null ? !sort.equals(that.sort) : that.sort != null);

    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + page;
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PagingInput{" +
                "size=" + size +
                ", page=" + page +
                ", sort='" + sort + '\'' +
                '}';
    }
}
