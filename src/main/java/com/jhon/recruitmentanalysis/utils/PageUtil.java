package com.jhon.recruitmentanalysis.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likun on 2020/7/13.
 */
public class PageUtil {

    public final static Integer DEFAULT_PAGE_SIZE = 200;

    /**
     * 分页获取数据
     *
     * @param data     原始数据
     * @param curPage  当前页
     * @param pageSize 每页数量
     * @return
     */
    public static <T> List<T> getPageData(List<T> data, int curPage, int pageSize) {
        if (data == null || data.size() == 0) {
            return new ArrayList<>();
        } else if (curPage <= 0) {
            throw new RuntimeException("curPage不能小于等于0");
        } else if (pageSize <= 0) {
            throw new RuntimeException("pageSize不能小于等于0");
        }
        if (data.size() <= pageSize) {
            return data;
        }

        //总页数
        int totalPage = getTotalPage(data, pageSize);

        // 1 获取第一页
        if (curPage == 1) {
            return data.subList(0, pageSize);
        }
        // 2 中间页
        else if (curPage < totalPage) {
            return data.subList((curPage - 1) * pageSize, curPage * pageSize);
        }
        // 3 获取最后一页
        else if (curPage == totalPage) {
            return data.subList((curPage - 1) * pageSize, data.size());
        }
        // 4 非法页
        else {
            return new ArrayList<>();
        }
    }

    /**
     * @Description: 获取总页数
     * @Param: null
     * @Return:
     * @Author: likun
     * @Date: 2020/7/13 11:04
     */
    public static <T> int getTotalPage(List<T> data, int pageSize) {
        if (data == null || data.size() == 0) {
            return 0;
        } else if (pageSize <= 0) {
            throw new RuntimeException("pageSize不能小于等于0");
        }
        return data.size() % pageSize == 0 ? data.size() / pageSize : data.size() / pageSize + 1;
    }

    /**
     * @Description: 获取总页数
     * @Param: count
     * @Param: pageSize
     * @Return: int
     * @Author: likun
     * @Date: 2020/11/30 18:01
     */
    public static int getTotalPage(int count, int pageSize) {
        if (count <= 0) {
            throw new RuntimeException("count不能小于等于0");
        }
        if (pageSize <= 0) {
            throw new RuntimeException("pageSize不能小于等于0");
        }
        return count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
    }
}
