package com.ripple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 节点记录表
 * @author Edwin
 */
@Service("NodeServiceImpl")
public class NodeServiceImpl implements INodeService {
    @Autowired
    private NodeDAO nodeDao;
}