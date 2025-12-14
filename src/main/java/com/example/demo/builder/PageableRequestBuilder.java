package com.example.demo.builder;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class PageableRequestBuilder {

    @Autowired
    private HttpServletRequest request;

    public Pageable build(){
        int page = 1;
        int size = 10;
        String sortBy =null;
        Sort.Direction sortDirection = Sort.Direction.ASC;
        Sort sort = null;
        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                if(page < 1) { page = 1; }
            }
            if (request.getParameter("size") != null) {
                size = Integer.parseInt(request.getParameter("size"));
            }
            if (request.getParameter("sortBy") != null) {
                sortBy = request.getParameter("sortBy");
            }
            if (request.getParameter("sortType") != null) {
                if(StringUtils.equalsIgnoreCase(request.getParameter("sortType"), "asc")){
                    sortDirection = Sort.Direction.ASC;
                }else if(StringUtils.equalsIgnoreCase(request.getParameter("sortType"), "desc")){
                    sortDirection = Sort.Direction.DESC;
                }
            }
            if(sortBy != null) sort = Sort.by(sortDirection, sortBy);
        }catch (Exception ex){  }
        if(sortBy == null){
            return PageRequest.of(page-1, size);
        }
        return PageRequest.of(page-1, size, sort);
    }
}
