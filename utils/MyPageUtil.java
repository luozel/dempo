package com.dev.testsos.sosgroup.utils;


import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页工具
 *
 * @Author: 陈嘉欣
 * @Date: 2018/12/17 16:00
 */
@Data
public class MyPageUtil {

    @Data
    public class PageCond {

        /**
         * 页码
         */
        private Integer pageNum;
        /**
         * 页行数
         */
        private Integer pageSize;


        public Integer getPageNum() {
            this.pageNum = pageNum == null ? 1 : pageNum;
            return pageNum - 1;
        }

        public Integer getPageSize() {
            return pageSize == null ? 10 : pageSize;
        }
    }

    @Data
    public static class PageInfo {
        /**
         * 页码
         */
        private Integer pageNum;
        /**
         * 页条数
         */
        private Integer pageSize;
        /**
         * 总页数
         */
        private Integer pages;
        /**
         * 添加总条数
         */
        private Long total;
        /**
         * 数据
         */
        private List list;

        /**
         * 获取jpa分页信息
         *
         * @param page jpa分页类
         * @return
         */
        public static PageInfo of(Page page) {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setList(page.getContent());
            pageInfo.setTotal(page.getTotalElements());
            pageInfo.setPages(page.getTotalPages());
            pageInfo.setPageSize(page.getSize());
            pageInfo.setPageNum(page.getNumber());
            return pageInfo;
        }

        /**
         * 获取分页信息
         *
         * @param page 分页类
         * @return
         */
        public static <T> PageInfo of(List<T> list) {
            com.github.pagehelper.PageInfo<T> page = new com.github.pagehelper.PageInfo<>(list);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setList(page.getList());
            pageInfo.setTotal(page.getTotal());
            pageInfo.setPages(page.getPages());
            pageInfo.setPageSize(page.getPageSize());
            pageInfo.setPageNum(page.getPageNum());
            return pageInfo;
        }

        public Integer getPageNum() {
            return pageNum + 1;
        }

        public PageInfo setList(List list) {
            this.list = list;
            return this;
        }
        public static PageInfo getPageInfo(Integer total, Integer pageNum, Integer pageSize){
            PageInfo pageInfo = new PageInfo();
            pageInfo.setTotal(total.longValue());
            pageInfo.setPageNum(pageNum);
            pageInfo.setPageSize(pageSize);
            int pages = total / pageSize;
            int pagesn = (total % pageSize) > 0 ? 1 : 0;
            pages = pages + pagesn;
            pageInfo.setPages(pages);
            return pageInfo;
        }

        public static PageInfo getPageInfo(Integer total, Integer pageNum, Integer pageSize, List list){
            PageInfo pageInfo = getPageInfo(total, pageNum, pageSize);
            pageInfo.setList(list);
            return pageInfo;
        }
    }

}
