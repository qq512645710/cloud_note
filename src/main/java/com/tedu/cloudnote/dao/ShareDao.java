package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Share;

public interface ShareDao {
    public int save(Share share);
    /**
     * 搜索分享笔记
     * @param map key1:title,key2:start,key3:pageSize
     * @return 满足笔记分享的条件
     */
    public List<Share> findByTitle(Map map);
    public Share findById(String id);
}
